package controllers

import com.mongodb.client.result.DeleteResult
import play.api.mvc._
import repositories.DataRepository

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}
import play.api.libs.json._
import org.mongodb.scala.result.DeleteResult
import services.LibraryService

import models.{Book, DataModel}

@Singleton
class ApplicationController @Inject()(val controllerComponents: ControllerComponents, val dataRepository: DataRepository, val service: LibraryService)(implicit val ec: ExecutionContext) extends BaseController {
  def index(): Action[AnyContent] = Action.async { implicit request =>
    dataRepository.index().map {
      case Right(item: Seq[DataModel]) => Ok { // 200 response.
        Json.toJson(item)
      }
      case Left(error) => Status(INTERNAL_SERVER_ERROR)(Json.toJson("Unable to find any books"))
    }
  }

  def create(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[DataModel] match {
      case JsSuccess(dataModel, _) =>
        dataRepository.create(dataModel).map(_ => Created)
      case JsError(_) => Future(BadRequest)
    }
  }

  def read(id: String): Action[AnyContent] = Action.async { implicit request =>
    dataRepository.read(id).map { data =>
      Ok(Json.toJson(data))
    }.recover {
      case _: NoSuchElementException => NotFound(Json.toJson("No data found"))
      case error => Status(INTERNAL_SERVER_ERROR)(Json.toJson(s"Unable to read data: $error"))
    }
  }
  def update(id: String) = Action.async(parse.json) { implicit request =>
    request.body.validate[DataModel] match {
      case JsSuccess(dataModel, _) =>
        dataRepository.update(id, dataModel).map(_ => Accepted(Json.toJson(dataModel)))
      case JsError(_) => Future(BadRequest)
    }
  }

  def delete(id: String): Action[AnyContent] = Action.async { implicit request =>
    dataRepository.delete(id).map { result =>
      if (result.wasAcknowledged()) {
        Accepted
      } else {
        Status(INTERNAL_SERVER_ERROR)(Json.toJson("Unable to delete data"))
      }
    }.recover {
      case error =>
        // Handle other types of errors (e.g., network issues, MongoDB server errors).
        Status(INTERNAL_SERVER_ERROR)(Json.toJson(s"Error during delete operation: $error"))
    }
  }

  def getGoogleBook(search: String, term: String): Action[AnyContent] = Action.async { implicit request =>
    service.getGoogleBook(search = search, term = term).value.map {
      case Right(book: List[Book]) => Ok(Json.toJson(book.asInstanceOf[List[Book]])) //Hint: This should be the same as before
      case Left(error) => Status(INTERNAL_SERVER_ERROR)(Json.toJson(s"Unable to read data: $error"))
    }
  }
  }

