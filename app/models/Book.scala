package models

import play.api.libs.json._

case class Book(
               //kind: String,
               id: String,
               etag: Option[String],
               //selfLink: String,
               //saleInfo: SaleInfo,
               volumeInfo: VolumeInfo
               )

//case class SaleInfo(
//                   country: String,
//                   saleability: String,
//                   isEbook: Boolean,
//                   buyLink: Option[String]
//                     )

case class VolumeInfo(
                     title: String,
                     subtitle: Option[String],
                     //authors: List[String],
                     //publishedDate: String,
                     description: Option[String],
                     //industryIdentifiers: List[IndustryIdentifiers]
                     )

//case class IndustryIdentifiers(
//                              `type`: String,
//                              identifier: String
//                              )

object Book {
  implicit val formats: OFormat[Book] = Json.format[Book]
}

//object SaleInfo {
//  implicit val formats: OFormat[SaleInfo] = Json.format[SaleInfo]
//}

object VolumeInfo {
  implicit val formats: OFormat[VolumeInfo] = Json.format[VolumeInfo]
}

//object IndustryIdentifiers {
//  implicit val formats: OFormat[IndustryIdentifiers] = Json.format[IndustryIdentifiers]
//}