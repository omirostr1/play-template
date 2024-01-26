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
      case Left(_) => Status(INTERNAL_SERVER_ERROR)(Json.toJson(s"Unable to find any books because no books are stored in the database"))
    }
  }

  def create(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[DataModel] match {
      case JsSuccess(dataModel, _) =>
        repositoryService.create(dataModel).map {
          case Right(book: DataModel) => Created(Json.toJson(book))
          case Left(_) => Status(INTERNAL_SERVER_ERROR)(Json.toJson("Error: entry cannot be created due to duplicate id entered"))
        }
      case JsError(_) => Future(BadRequest)
    }
  }

  def read(id: String): Action[AnyContent] = Action.async { implicit request =>
    repositoryService.read(id).map {
      case Right(data) => Ok(Json.toJson(data))
      case Left(_) => NotFound(Json.toJson("No data found"))
    }
  }

  def readByAnyField(field: String, term: String): Action[AnyContent] = Action.async { implicit request =>
    repositoryService.readByAnyField(field, term).map {
      case Right(data) => Ok(Json.toJson(data))
      case Left(_) => NotFound(Json.toJson("No data found"))
    }
  }

  def update(id: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[DataModel] match {
      case JsSuccess(dataModel, _) =>
        repositoryService.update(id, dataModel).map {
          case Right(book) => Accepted(Json.toJson(dataModel))
          case Left(_) => NotFound(Json.toJson("No data found"))
        }
      case JsError(_) => Future(BadRequest)
    }
  }

  def updateSpecificField(id: String, field: String, change: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    repositoryService.updateSpecificField(id, field, change).map {
            case Right(data) => Ok(Json.toJson(data))
            case Left(_) => BadRequest(Json.toJson("No data found"))
          }
    }

  def delete(id: String): Action[AnyContent] = Action.async { implicit request =>
    repositoryService.delete(id).map {
      case Right(deleteResult) if deleteResult.wasAcknowledged() =>
        Accepted
      case Right(_) =>
        Status(INTERNAL_SERVER_ERROR)(Json.toJson("Unable to delete data"))
      case Left(error) =>
        NotFound(Json.toJson("No data found"))
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

