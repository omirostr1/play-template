package models

import play.api.libs.json._

case class Book(
               kind: String,
               id: String,
               etag: String,
               selfLink: String,
               saleInfo: List[SaleInfo],
               volumeInfo: List[VolumeInfo]
               )

case class SaleInfo(
                   country: String,
                   saleability: String,
                   isEbook: Boolean,
                   buyLink: String
                     )

case class VolumeInfo(
                     title: String,
                     authors: String,
                     publishedDate: String,
                     industryIdentifiers: List[IndustryIdentifiers]
                     )

case class IndustryIdentifiers(
                              bookType: String,
                              identifier: String
                              )

object Book {
  implicit val formats: OFormat[Book] = Json.format[Book]
}

object SaleInfo {
  implicit val formats: OFormat[SaleInfo] = Json.format[SaleInfo]
}

object VolumeInfo {
  implicit val formats: OFormat[VolumeInfo] = Json.format[VolumeInfo]
}

object IndustryIdentifiers {
  implicit val formats: OFormat[IndustryIdentifiers] = Json.format[IndustryIdentifiers]
}