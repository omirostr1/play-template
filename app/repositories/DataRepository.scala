package repositories

import models.{APIError, DataModel}
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.model.Filters.empty
import org.mongodb.scala.model._
import org.mongodb.scala.result
import uk.gov.hmrc.mongo.MongoComponent
import uk.gov.hmrc.mongo.play.json.PlayMongoRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class DataRepository @Inject()(
                                mongoComponent: MongoComponent
                              )(implicit ec: ExecutionContext) extends PlayMongoRepository[DataModel](
  collectionName = "dataModels", // is the name of the collection, can set this to whatever you like
  mongoComponent = mongoComponent,
  domainFormat = DataModel.formats,
  indexes = Seq(IndexModel(
    Indexes.ascending("_id")
  )),
  replaceIndexes = false
) {

  def index(): Future[Either[APIError.BadAPIResponse, Seq[DataModel]]]  =
    collection.find().toFuture().map{ // this returns all items in the data repository, as no filters as parameters are passed.
      case books: Seq[DataModel] => Right(books)
      case _ => Left(APIError.BadAPIResponse(404, "Books cannot be found"))
    }

  def create(book: DataModel): Future[Either[String, DataModel]] =
    collection
      .insertOne(book) // Parameter book is the document to be inserted into the collection.
      .toFuture()
      .map{
        case book: DataModel => Right(book)
        case _ => Left("Error: entry cannot be created due to false information")
      }

  private def byID(id: String): Bson =
    Filters.and(
      Filters.equal("_id", id)
    )

  def read(id: String): Future[Option[DataModel]] =
    collection.find(byID(id)).headOption flatMap { // byID is a query used to filter the collection.
      case Some(data) => Future(Some(data))
      case _ => Future(None)
    }

  def update(id: String, book: DataModel): Future[result.UpdateResult] =
    collection.replaceOne(
      filter = byID(id), // selection criteria for the update.
      replacement = book, // replacement document.
      options = new ReplaceOptions().upsert(true) //What happens when we set this to false? When true, replaceOne() either inserts the document from the replacement parameter if no document matches the filter, or replaces the document that matches the filter with the replacement document.
    ).toFuture()

  def delete(id: String): Future[Either[String, result.DeleteResult]] = {
    collection.find(byID(id)).headOption flatMap {
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

