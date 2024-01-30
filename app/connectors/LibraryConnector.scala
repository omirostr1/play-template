package connectors

import cats.data.EitherT
import models.{APIError, Book}
import play.api.libs.json.{JsResultException, OFormat}
import play.api.libs.ws.{WSClient, WSResponse}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class LibraryConnector @Inject()(ws: WSClient) {
  def get[Response](url: String)(implicit rds: OFormat[Response], ec: ExecutionContext): EitherT[Future, APIError, List[Book]] = { // EitherT takes 3 parameters, the first one is the arbitrary type constructor and the last two the same parameters that can be passed via an Either.
    val request = ws.url(url)
    val response = request.get()
    EitherT {
      response
        .map {
          result =>
            Right(result.json("items").as[List[Book]])
        }
        .recover  { case _: WSResponse =>
          Left(APIError.BadAPIResponse(500, "Could not connect"))
        case _: JsResultException =>
          Left(APIError.BadAPIResponse(500, "Failed to parse JSON response"))
        }
    }
  }
}
