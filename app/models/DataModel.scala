package models

import play.api.data._
import play.api.libs.json._
import play.api.data.Forms._

case class DataModel(
                    _id: String,
                    name: String,
                    description: String,
                    pageCount: Int,
                    isbn: String
                    )

object DataModel {

  implicit val formats: OFormat[DataModel] = Json.format[DataModel]

  val dataModelForm: Form[DataModel] = Form(
    mapping(
      "_id" -> text,
      "name" -> text,
      "description" -> text,
      "pageCount" -> number,
      "isbn" -> text
    )(DataModel.apply)(DataModel.unapply)
  )

}



