package controllers

import baseSpec.BaseSpec
import models.{APIError, DataModel}
import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json._
import play.api.mvc.AnyContentAsEmpty
import play.api.test.CSRFTokenHelper.CSRFFRequestHeader
import play.api.test.FakeRequest
import play.api.test.Helpers._
import repositories.DataRepositoryTrait
import services.RepositoryService

import scala.concurrent.{ExecutionContext, Future}

class RepositoryServiceSpec extends BaseSpec with MockFactory with ScalaFutures with GuiceOneAppPerSuite{

  val mockConnector: DataRepositoryTrait = mock[DataRepositoryTrait]
  implicit val executionContext: ExecutionContext = app.injector.instanceOf[ExecutionContext]
  val testService = new RepositoryService(mockConnector)

  def buildPost(url: String): FakeRequest[AnyContentAsEmpty.type] =
    FakeRequest(POST, url).withCSRFToken.asInstanceOf[FakeRequest[AnyContentAsEmpty.type]]

  def buildGet(url: String): FakeRequest[AnyContentAsEmpty.type] =
    FakeRequest(GET, url).withCSRFToken.asInstanceOf[FakeRequest[AnyContentAsEmpty.type]]

  private val bookNo1: JsValue = Json.obj(
    "_id" -> "1",
    "name" -> "Game of Thrones",
    "description" -> "Best book ever",
    "numSales" -> 32,
    "isbn" -> "9693706099"
  )
  private val bookNo2: JsValue = Json.obj(
    "id" -> "2",
    "name" -> "Harry Potter",
    "description" -> "Wizards and stuff",
    "numSales" -> 2424,
    "isbn" -> "9693700099"
  )

  val dataModel: DataModel = DataModel(
    _id = "1",
    name = "test name",
    description = "test description",
    numSales = 100,
    isbn = "9693706099"
  )

  val dataModel2: DataModel = DataModel(
    _id = "3",
    name = "another test name",
    description = "different test description",
    numSales = 94,
    isbn = "9693700099"
  )

  private val bookSeq: List[DataModel] = List(dataModel,dataModel2)

  "ApplicationController .index" should {

    "return all instances stored in the database" in {
      (() => mockConnector.index())
        .expects()
        .returning(Future(Right(bookSeq)))
        .once()

      whenReady(testService.index()) { result =>
        result shouldBe Right(bookSeq)
      }
    }
  }

  "ApplicationController .create" should {

    "error case: fail to create book in the database" in {
      (mockConnector.create _)
        .expects(bookNo1.as[DataModel])
        .returning(Future(Option(bookNo1.as[DataModel])))
        .once()

      whenReady(testService.create(bookNo1.as[DataModel])) {
        case Left(error) => error shouldBe Left(APIError.BadAPIResponse(500, "Error: entry cannot be created due to duplicate id"))
        case Right(datamodel) => datamodel shouldBe bookNo1.as[DataModel]
      }
    }
  }

  "ApplicationController .read" should {

    "return instance stored in the database with the id provided" in {
      (mockConnector.read _)
        .expects("1")
        .returning(Future(Right(bookNo1.as[DataModel])))
        .once()

      whenReady(testService.read("1")) {
        case Left(error) => error shouldBe Left(APIError.BadAPIResponse(500, "Error: entry cannot be created due to duplicate id"))
        case Right(datamodel) => datamodel shouldBe bookNo1.as[DataModel]
      }
    }
  }

}
