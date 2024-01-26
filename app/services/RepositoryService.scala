package services

import models.{APIError, DataModel}
import org.mongodb.scala.result
import repositories.{DataRepository, DataRepositoryTrait}

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class RepositoryService @Inject()(val dataRepositoryTrait: DataRepositoryTrait)(implicit ec: ExecutionContext) {

  def index(): Future[Either[APIError.BadAPIResponse, Seq[DataModel]]] =
    dataRepositoryTrait.index().map { // this returns all items in the data repository, as no filters as parameters are passed.
      case Right(books: Seq[DataModel]) => Right(books)
      case _ => Left(APIError.BadAPIResponse(404, "Books cannot be found"))
    }

  def create(book: DataModel): Future[Either[APIError.BadAPIResponse, DataModel]] =
    dataRepositoryTrait.create(book).map {
      case None => Left(APIError.BadAPIResponse(500, "Error: entry cannot be created due to duplicate id"))
      case _ => Right(book)
    }

  def read(id: String): Future[Either[APIError.BadAPIResponse, DataModel]] =
    dataRepositoryTrait.read(id).map {
      case Right(data: DataModel) => Right(data)
      case Left(_) => Left(APIError.BadAPIResponse(404, "Error: entry cannot be created, id doesn't exist"))
    }

  def readByAnyField(field: String, term: String): Future[Either[APIError.BadAPIResponse, DataModel]] =
    dataRepositoryTrait.readByAnyField(field, term).map {
      case Right(data: DataModel) => Right(data)
      case Left(_) => Left(APIError.BadAPIResponse(404, "Error: entry cannot be created, id doesn't exist"))
    }

  def update(id: String, book: DataModel): Future[Either[APIError.BadAPIResponse, result.UpdateResult]] =
    dataRepositoryTrait.update(id, book).map {
      case Right(result) => Right(result)
      case Left(_) => Left(APIError.BadAPIResponse(404, "Error: entry cannot be created, id doesn't exist"))
    }

  def updateSpecificField(id: String, field: String, change: String): Future[Either[APIError.BadAPIResponse, DataModel]] = {
    dataRepositoryTrait.updateSpecificField(id, field, change).map {
      case Right(result) => Right(result)
      case Left(_) => Left(APIError.BadAPIResponse(404, "Error: entry cannot be created, id doesn't exist"))
    }
  }

  def delete(id: String): Future[Either[APIError.BadAPIResponse, result.DeleteResult]] = {
    dataRepositoryTrait.delete(id).map {
      case Right(result) => Right(result)
      case Left(_) => Left(APIError.BadAPIResponse(404, "Error: entry cannot be created, id doesn't exist"))
    }
  }


}
