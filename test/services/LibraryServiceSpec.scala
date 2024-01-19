package services

import baseSpec.BaseSpec
import cats.data.EitherT
import connectors.LibraryConnector
import models.{APIError, Book}
import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json._

import scala.concurrent.ExecutionContext

class LibraryServiceSpec extends BaseSpec with MockFactory with ScalaFutures with GuiceOneAppPerSuite{

  val mockConnector = mock[LibraryConnector]
  implicit val executionContext: ExecutionContext = app.injector.instanceOf[ExecutionContext]
  val testService = new LibraryService(mockConnector)

  val gameOfThrones: JsValue = Json.obj(
    "id" -> "someId",
    "volumeInfo" -> Json.obj("title" -> "A Game of Thrones",
      "description" -> "The best book!!!"),
    "etag" -> "ciulyRzCGrc"
  )

  "getGoogleBook" should {
    val url: String = "testUrl"

    "return a book" in {

      (mockConnector.get[Book](_: String)(_: OFormat[Book], _: ExecutionContext))
        .expects(url, *, *)
        .returning(EitherT.rightT(List(gameOfThrones.as[Book])))
        .once()

      whenReady(testService.getGoogleBook(urlOverride = Some(url), search = "", term = "").value) { result =>
        result shouldBe Right(List(gameOfThrones.as[Book]))
      }
    }
  }

  "return an error" in {
        val url: String = "testUrl"

        (mockConnector.get[Book](_: String)(_: OFormat[Book], _: ExecutionContext))
          .expects(url, *, *)
          .returning(EitherT.leftT(APIError.BadAPIResponse(500,"Could not connect"))) // How do we return an error?
          .once()

        whenReady(testService.getGoogleBook(urlOverride = Some(url), search = "", term = "").value) { result =>
          result shouldBe Left(APIError.BadAPIResponse(500, "Could not connect"))
        }
      }


}
