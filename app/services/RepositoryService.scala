package services

import models.{APIError, DataModel}
import org.mongodb.scala.result
import repositories.DataRepository

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class RepositoryService @Inject()(val dataRepository: DataRepository)(implicit ec: ExecutionContext) {

  def index(): Future[Either[APIError.BadAPIResponse, Seq[DataModel]]] =
    dataRepository.index().map { // this returns all items in the data repository, as no filters as parameters are passed.
      case Right(books: Seq[DataModel]) => Right(books)
      case _ => Left(APIError.BadAPIResponse(404, "Books cannot be found"))
    }

  def create(book: DataModel): Future[Either[String, DataModel]] =
    dataRepository.create(book).map {
      case Right(book: DataModel) => Right(book)
      case Left(_) => Left("Error: entry cannot be created due to false information")
    }

  def read(id: String): Future[Option[DataModel]] =
    dataRepository.read(id) flatMap { // byID is a query used to filter the collection.
      case Some(data) => Future(Some(data))
      case _ => Future(None)
    }

  def readByAnyField(field: String, term: String): Future[Option[DataModel]] =
    dataRepository.readByAnyField(field, term) flatMap {
      case Some(data) => Future(Some(data))
      case _ => Future(None)
    }
  def update(id: String, book: DataModel): Future[result.UpdateResult] =
    dataRepository.update(id, book)

  def updateSpecificField(id: String, field: String, change: String): Future[Option[DataModel]] = {
    dataRepository.updateSpecificField(id, field, change)
  }

  def delete(id: String): Future[Either[String, result.DeleteResult]] = {
    dataRepository.delete(id)
  }


}
