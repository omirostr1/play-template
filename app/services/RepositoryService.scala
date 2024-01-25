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

  def create(book: DataModel): Future[Either[APIError.BadAPIResponse, DataModel]] =
    dataRepository.create(book).map {
      case None => Left(APIError.BadAPIResponse(500, "Error: entry cannot be created due to duplicate id"))
      case _ => Right(book)
    }

  def read(id: String): Future[Either[APIError.BadAPIResponse, DataModel]] =
    dataRepository.read(id).map {
      case Right(data: DataModel) => Right(data)
      case Left(_) => Left(APIError.BadAPIResponse(404, "Error: entry cannot be created, id doesn't exist"))
    }

  def readByAnyField(field: String, term: String): Future[Either[APIError.BadAPIResponse, DataModel]] =
    dataRepository.readByAnyField(field, term).map {
      case Right(data: DataModel) => Right(data)
      case Left(_) => Left(APIError.BadAPIResponse(404, "Error: entry cannot be created, id doesn't exist"))
    }

  def update(id: String, book: DataModel): Future[Either[APIError.BadAPIResponse, result.UpdateResult]] =
    dataRepository.update(id, book).map {
      case Right(result) => Right(result)
      case Left(_) => Left(APIError.BadAPIResponse(404, "Error: entry cannot be created, id doesn't exist"))
    }

  def updateSpecificField(id: String, field: String, change: String): Future[Either[APIError.BadAPIResponse, DataModel]] = {
    dataRepository.updateSpecificField(id, field, change).map {
      case Right(result) => Right(result)
      case Left(_) => Left(APIError.BadAPIResponse(404, "Error: entry cannot be created, id doesn't exist"))
    }
  }

  def delete(id: String): Future[Either[APIError.BadAPIResponse, result.DeleteResult]] = {
    dataRepository.delete(id).map {
      case Right(result) => Right(result)
      case Left(_) => Left(APIError.BadAPIResponse(404, "Error: entry cannot be created, id doesn't exist"))
    }
  }


}
