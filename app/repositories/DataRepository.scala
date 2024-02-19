package repositories

import com.google.inject.ImplementedBy
import models.{APIError, DataModel}
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.model.Filters.empty
import org.mongodb.scala.model._
import org.mongodb.scala.result
import uk.gov.hmrc.mongo.MongoComponent
import uk.gov.hmrc.mongo.play.json.PlayMongoRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@ImplementedBy(classOf[DataRepository])
trait DataRepositoryTrait {

  def index(): Future[Either[String, Seq[DataModel]]]
  def create(book: DataModel): Future[Option[DataModel]]
  def read(id: String): Future[Either[APIError.BadAPIResponse, DataModel]]
  def readByAnyField(field: String, term: String): Future[Either[APIError.BadAPIResponse, DataModel]]
  def update(id: String, book: DataModel): Future[Either[APIError.BadAPIResponse, result.UpdateResult]]
  def updateSpecificField(id: String, field: String, change: String): Future[Either[APIError.BadAPIResponse, DataModel]]
  def delete(id: String): Future[Either[String, result.DeleteResult]]

}

@Singleton
class DataRepository @Inject()(mongoComponent: MongoComponent)(implicit ec: ExecutionContext) extends PlayMongoRepository[DataModel](
  collectionName = "dataModels", // is the name of the collection, can set this to whatever you like
  mongoComponent = mongoComponent,
  domainFormat = DataModel.formats,
  indexes = Seq(IndexModel(
    Indexes.ascending("_id")
  )),
  replaceIndexes = false
) with DataRepositoryTrait {

  def index(): Future[Either[String, Seq[DataModel]]] =
    collection.find().toFuture().map { // this returns all items in the data repository, as no filters as parameters are passed.
      case books: Seq[DataModel] => Right(books)
      case _ => Left("None")
    }

  def create(book: DataModel): Future[Option[DataModel]] = {
    collection.find(byID(book._id)).headOption().flatMap {
      case Some(data: DataModel) => Future(None)
      case _ => collection.insertOne(book).toFuture().map(_ => Some(book))
    }
  }

  private def byID(id: String): Bson =
    Filters.and(
      Filters.equal("_id", id)
    )

  private def byField(field: String, term: String): Bson =
    Filters.and(
      Filters.equal(s"$field", term)
    )

  def read(id: String): Future[Either[APIError.BadAPIResponse, DataModel]] =
    collection.find(byID(id)).headOption().map { // byID is a query used to filter the collection.
      case Some(data: DataModel) => Right(data)
      case _ | null => Left(APIError.BadAPIResponse(404, "Book cannot be found "))
    }

  def readByAnyField(field: String, term: String): Future[Either[APIError.BadAPIResponse, DataModel]] =
    collection.find(byField(field, term)).headOption().map {
      case Some(data: DataModel) => Right(data)
      case _ | null => Left(APIError.BadAPIResponse(404, "Book cannot be found "))
    }

  def update(id: String, book: DataModel): Future[Either[APIError.BadAPIResponse, result.UpdateResult]] = {
    collection.replaceOne(
      filter = byID(id), // selection criteria for the update.
      replacement = book, // replacement document.
      options = new ReplaceOptions().upsert(true) // What happens when we set this to false? When true, replaceOne() either inserts the document from the replacement parameter if no document matches the filter, or replaces the document that matches the filter with the replacement document.
    ).toFuture().map(result => Right(result))
      .recover {
        case _ => Left(APIError.BadAPIResponse(404, "Book cannot be found "))
    }
  }

  def updateSpecificField(id: String, field: String, change: String): Future[Either[APIError.BadAPIResponse, DataModel]] = {
    collection.find(byID(id)).headOption().flatMap {
      case Some(data) =>
        val updatedBook = field match {
          case "_id" => data.copy(_id = change)
          case "name" => data.copy(name = change)
          case "description" => data.copy(description = change)
          case "pageCount" => data.copy(pageCount = change.toInt)
          case _ => data
        }
        update(id, updatedBook).map(book => Right(updatedBook))
      case _ => Future(Left(APIError.BadAPIResponse(404, "Book cannot be found ")))
    }
  }

  def delete(id: String): Future[Either[String, result.DeleteResult]] = {
    collection.find(byID(id)).headOption() flatMap {
      case Some(data) => collection.deleteOne(
        filter = byID(id) // specifies deletion criteria using query operators, in this case byID.
      ).toFuture().map { result =>
        if (result.wasAcknowledged() && result.getDeletedCount > 0) {
          Right(result)
        } else {
          Left(s"Error: Failed to delete data with id $id")
        }
      }
      case _ => Future(Left("Error: Data entry not found"))
    }
  }

  def deleteAll(): Future[Unit] = collection.deleteMany(empty()).toFuture().map(_ => ()) //Hint: needed for tests
  // can delete multiple documents or even all documents of a collection.

}

