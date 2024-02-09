package models

import play.api.libs.json._

case class Book(
               id: String,
               volumeInfo: VolumeInfo
               )


case class VolumeInfo(
                     title: String,
                     description: Option[String],
                     industryIdentifiers: List[IndustryIdentifiers]
                     )

case class IndustryIdentifiers(
                              `type`: String,
                              identifier: String
                              )

object Book {
  implicit val formats: OFormat[Book] = Json.format[Book]
}

object VolumeInfo {
  implicit val formats: OFormat[VolumeInfo] = Json.format[VolumeInfo]
}

object IndustryIdentifiers {
  implicit val formats: OFormat[IndustryIdentifiers] = Json.format[IndustryIdentifiers]
}