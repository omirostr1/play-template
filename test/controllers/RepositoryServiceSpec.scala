package controllers

import baseSpec.BaseSpec
import models.{APIError, DataModel}
import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json._
import play.api.test.Helpers._
import repositories.{DataRepository, DataRepositoryTrait}
import services.RepositoryService

import scala.concurrent.{ExecutionContext, Future}

class RepositoryServiceSpec extends BaseSpec with MockFactory with ScalaFutures with GuiceOneAppPerSuite{

  val mockRepo = mock[DataRepositoryTrait]
  implicit val executionContext: ExecutionContext = app.injector.instanceOf[ExecutionContext]
  val testService = new RepositoryService(mockRepo)

  val bookNo1: JsValue = Json.obj(
    "id" -> 1,
    "name" -> "Game of Thrones",
    "description" -> "Best book ever",
    "numSales" -> 32
  )
  val bookNo2: JsValue = Json.obj(
    "id" -> 2,
    "name" -> "Harry Potter",
    "description" -> "Wizards and stuff",
    "numSales" -> 2424
  )

//  "index" should {
//    val url: String = "testUrl"
//
//    "return all available books" in {
//
//      (mockRepo.index())
//        .expects()
//        .returning(Future[bookNo1.as[DataModel]])
//        .once()
//
//      whenReady(testService.index() { result =>
//        result shouldBe bookNo1
//      }
//    }
//  }

}
