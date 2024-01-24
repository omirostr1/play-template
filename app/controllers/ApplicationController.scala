package controllers

import models.{Book, DataModel}
import play.api.libs.json._
import play.api.mvc._
import services.{LibraryService, RepositoryService}

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class ApplicationController @Inject()(val controllerComponents: ControllerComponents, val repositoryService: RepositoryService, val service: LibraryService)(implicit val ec: ExecutionContext) extends BaseController {
  def index(): Action[AnyContent] = Action.async { implicit request =>
    repositoryService.index().map {
      case Right(books: Seq[DataModel]) => Ok(Json.toJson(books))
      case Left(error) => Status(INTERNAL_SERVER_ERROR)(Json.toJson(s"Unable to find any books because of $error"))
    }
  }

  def create(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[DataModel] match {
      case JsSuccess(dataModel, _) =>
        repositoryService.create(dataModel).map(_ => Created)
      case JsError(_) => Future(BadRequest)
    }
  }

  def read(id: String): Action[AnyContent] = Action.async { implicit request =>
    repositoryService.read(id).map { data =>
      Ok(Json.toJson(data))
    }.recover {
      case _: NoSuchElementException => NotFound(Json.toJson("No data found"))
      case error => Status(INTERNAL_SERVER_ERROR)(Json.toJson(s"Unable to read data: $error"))
    }
  }

  def readByAnyField(field: String, term: String): Action[AnyContent] = Action.async { implicit request =>
    repositoryService.readByAnyField(field, term).map { data =>
      Ok(Json.toJson(data))
    }.recover {
      case _: NoSuchElementException => NotFound(Json.toJson("No data found"))
      case error => Status(INTERNAL_SERVER_ERROR)(Json.toJson(s"Unable to read data: $error"))
    }
  }

  def update(id: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[DataModel] match {
      case JsSuccess(dataModel, _) =>
        repositoryService.update(id, dataModel).map(_ => Accepted(Json.toJson(dataModel)))
      case JsError(_) => Future(BadRequest)
    }
  }

  def updateSpecificField(id: String, field: String, change: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    repositoryService.updateSpecificField(id, field, change).map {
            case data => Ok(Json.toJson(data))
            case error => BadRequest(Json.toJson(error))
          }
    }

  def delete(id: String): Action[AnyContent] = Action.async { implicit request =>
    repositoryService.delete(id).map { result =>
      result match {
        case Right(deleteResult) if deleteResult.wasAcknowledged() =>
          Accepted
        case Right(_) =>
          Status(INTERNAL_SERVER_ERROR)(Json.toJson("Unable to delete data"))
        case Left(error) =>
          Status(INTERNAL_SERVER_ERROR)(Json.toJson(error))
      }
    }.recover {
      case error =>
        Status(INTERNAL_SERVER_ERROR)(Json.toJson(s"Error during delete operation: $error"))
    }
  }

  def getGoogleBook(search: String, term: String): Action[AnyContent] = Action.async { implicit request =>
    service.getGoogleBook(search = search, term = term).value.map {
      case Right(book: List[Book]) =>
        println(Json.toJson(book))
        Ok(Json.toJson(book))
       //Hint: This should be the same as before
      case Left(error) => Status(INTERNAL_SERVER_ERROR)(Json.toJson(s"Unable to read data: $error"))
    }
  }
  }

