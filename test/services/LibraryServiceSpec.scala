package services

import baseSpec.BaseSpec
import connectors.LibraryConnector
import models.{APIError, Book}
import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json._

import scala.concurrent.{ExecutionContext, Future}

class LibraryServiceSpec extends BaseSpec with MockFactory with ScalaFutures with GuiceOneAppPerSuite{

  val mockConnector = mock[LibraryConnector]
  implicit val executionContext: ExecutionContext = app.injector.instanceOf[ExecutionContext]
  val testService = new LibraryService(mockConnector)

  val gameOfThrones: JsValue = Json.obj(
    "_id" -> "someId",
    "name" -> "A Game of Thrones",
    "description" -> "The best book!!!",
    "numSales" -> 100
  )

  "getGoogleBook" should {
    val url: String = "testUrl"

    "return a book" in {
      (mockConnector.get[Book](_: String)(_: OFormat[Book], _: ExecutionContext))
        .expects(url, *, *)
        .returning(Future(gameOfThrones.as[Book]))
        .once()

      whenReady(testService.getGoogleBook(urlOverride = Some(url), search = "", term = "")) { result =>
        assert(result == Right(List(gameOfThrones.as[Book])))
      }
    }
  }

  "return an error" in {
    val url: String = "testUrl"

    (mockConnector.get[Book](_: ???)(_: OFormat[???], _: ???))
      .expects(url, *, *)
      .returning(APIError) // How do we return an error?
      .once()

    whenReady(testService.getGoogleBook(urlOverride = Some(url), search = "", term = "")) { result =>
      result shouldBe APIError
    }
  }

}
