package controllers

import baseSpec.BaseSpecWithApplication
import models.DataModel
import play.api.http.Status
import play.api.libs.json._
import play.api.mvc.{AnyContentAsFormUrlEncoded, Result}
import play.api.test.FakeRequest
import play.api.test.Helpers._

import scala.concurrent.Future

class ApplicationControllerSpec extends BaseSpecWithApplication{

  val TestApplicationController = new ApplicationController(
    component,
    repoService,
    service
  )

  private val dataModel: DataModel = DataModel(
    _id =  "1",
    name = "test name",
    description = "test description",
    pageCount = 100,
    isbn = "9693706099"
  )

  private val dataModelWithSameId: DataModel = DataModel(
    _id = "1",
    name ="test name again",
    description = "test description too",
    pageCount = 150,
    isbn = "9693706039"
  )

  private val dataModelWithDifferentId: DataModel = DataModel(
    _id = "3",
    name = "another test name",
    description = "different test description",
    pageCount = 94,
    isbn = "9693700099"
  )

  "ApplicationController .index" should {

    "return all instances stored in the database" in {
      beforeEach()
      val result = TestApplicationController.index()(FakeRequest())
      status(result) shouldBe Status.OK
      afterEach()
    }
  }

  "ApplicationController .create" should {

    "create a book in the database" in {
      beforeEach()
      val request: FakeRequest[JsValue] = buildPost("/create").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)

      status(createdResult) shouldBe Status.CREATED
      afterEach()
    }
  }

  "ApplicationController .create error case" should {

    "return a BadRequest due to false data entered" in {
      beforeEach()
      val request: FakeRequest[JsValue] = buildPost("/create").withBody[JsValue](Json.toJson("abc"))
      val createdResult: Future[Result] = TestApplicationController.create()(request)

      status(createdResult) shouldBe Status.BAD_REQUEST
      afterEach()
    }
  }

  "ApplicationController .create error case" should {

    "return an Internal Server Error due to duplicate id found" in {
      beforeEach()
      val request: FakeRequest[JsValue] = buildPost("/create").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)
      status(createdResult) shouldBe Status.CREATED

      val newResult: Future[Result] = TestApplicationController.create()(request)
      status(newResult) shouldBe Status.INTERNAL_SERVER_ERROR
      afterEach()
    }
  }

  "ApplicationController .read" should {

    "find a book in the database by id" in {
      beforeEach()
      val request: FakeRequest[JsValue] = buildPost("/create").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)
      status(createdResult) shouldBe Status.CREATED
      //Hint: You could use status(createdResult) shouldBe Status.CREATED to check this has worked again

      val readResult: Future[Result] = TestApplicationController.read("1")(FakeRequest())

      status(readResult) shouldBe Status.OK
      contentAsJson(readResult).as[JsValue] shouldBe Json.toJson(dataModel)
      afterEach()
    }
  }

  "ApplicationController .read error case" should {

    "return a Not Found Error" in {
      beforeEach()
      val request: FakeRequest[JsValue] = buildPost("/create").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)
      status(createdResult) shouldBe Status.CREATED
      //Hint: You could use status(createdResult) shouldBe Status.CREATED to check this has worked again

      val readResult: Future[Result] = TestApplicationController.read("abd")(FakeRequest())
      status(readResult) shouldBe Status.NOT_FOUND
      //contentAsJson(readResult).as[JsValue] shouldBe Json.toJson(dataModel)
      afterEach()
    }
  }

  "ApplicationController .readByAnyField" should {

    "return successfully an entry" in {
      beforeEach()
      val request: FakeRequest[JsValue] = buildPost("/create").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)
      status(createdResult) shouldBe Status.CREATED
      //Hint: You could use status(createdResult) shouldBe Status.CREATED to check this has worked again

      val readResult: Future[Result] = TestApplicationController.readByAnyField("name", "test name")(FakeRequest())
      status(readResult) shouldBe Status.OK
      //contentAsJson(readResult).as[JsValue] shouldBe Json.toJson(dataModel)
      afterEach()
    }
  }

  "ApplicationController .readByAnyField error case" should {

    "return a Not Found Error" in {
      beforeEach()
      val request: FakeRequest[JsValue] = buildPost("/create").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)
      status(createdResult) shouldBe Status.CREATED
      //Hint: You could use status(createdResult) shouldBe Status.CREATED to check this has worked again

      val readResult: Future[Result] = TestApplicationController.readByAnyField("_id", "3")(FakeRequest())

      status(readResult) shouldBe Status.NOT_FOUND
      //contentAsJson(readResult).as[JsValue] shouldBe Json.toJson(dataModel)
      afterEach()
    }
  }

  "ApplicationController .update" should {

    "return a successfully updated entry" in {
      beforeEach()

      val request: FakeRequest[JsValue] = buildPost("/create").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)
      status(createdResult) shouldBe Status.CREATED

      val updateRequest: FakeRequest[JsValue] = buildPost(s"/update/${dataModel._id}").withBody[JsValue](Json.toJson(dataModelWithSameId))
      val result = TestApplicationController.update(dataModel._id)(updateRequest)

      status(result) shouldBe Status.ACCEPTED
      afterEach()
    }
  }

  "ApplicationController .update error case" should {

    "return a BadRequest due to missing data inserted" in {
      beforeEach()

      val request: FakeRequest[JsValue] = buildPost("/create").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)
      status(createdResult) shouldBe Status.CREATED

      val updateRequest: FakeRequest[JsValue] = buildPost(s"/update/${dataModel._id}").withBody[JsValue](Json.toJson("abc"))
      val result = TestApplicationController.update(id = "abd")(updateRequest)

      status(result) shouldBe Status.BAD_REQUEST

      afterEach()
    }
  }

  "ApplicationController .update error case" should {

    "return a Not Found error due to wrong data" in {
      beforeEach()

      val request: FakeRequest[JsValue] = buildPost("/create").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)
      status(createdResult) shouldBe Status.CREATED

      val updateRequest: FakeRequest[JsValue] = buildPost(s"/update/${dataModel._id}").withBody[JsValue](Json.toJson(dataModelWithDifferentId))
      val result = TestApplicationController.update(id = "abd")(updateRequest)

      status(result) shouldBe Status.NOT_FOUND

      afterEach()
    }
  }

  "ApplicationController .delete()" should {

    "return successfully deleted data entry" in {
      beforeEach()

      val request: FakeRequest[JsValue] = buildPost("/create").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)
      status(createdResult) shouldBe Status.CREATED

      val result = TestApplicationController.delete("1")(FakeRequest())

      status(result) shouldBe Status.ACCEPTED

      afterEach()
    }
  }

  "ApplicationController .delete() error case" should {

    "return a Not Found Error due to absence of such id from database" in {
      beforeEach()

      val request: FakeRequest[JsValue] = buildPost("/create").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)
      status(createdResult) shouldBe Status.CREATED

      val result = TestApplicationController.delete("12")(FakeRequest())

      status(result) shouldBe Status.NOT_FOUND

      afterEach()
    }
  }

  "ApplicationController .addBookForm() error case" should {

    "store book entered through a form in database" in {
      beforeEach()

      val request: FakeRequest[AnyContentAsFormUrlEncoded] = buildPost("/addnewbook/form").withFormUrlEncodedBody("_id" -> "3", "name" -> "Omiros", "description" -> "Trypatsas", "pageCount" -> "1", "isbn" -> "3598585")
      val createdResult: Future[Result] = TestApplicationController.addBookForm()(request)

      status(createdResult) shouldBe Status.OK

      afterEach()
    }
  }

  "ApplicationController .addBookForm() error case" should {

    "fail to store book entered through a form in database due to duplication" in {
      beforeEach()

      val request: FakeRequest[AnyContentAsFormUrlEncoded] = buildPost("/addnewbook/form").withFormUrlEncodedBody("_id" -> "3", "name" -> "Omiros", "description" -> "Trypatsas", "pageCount" -> "1", "isbn" -> "3598585")
      val createdResult: Future[Result] = TestApplicationController.addBookForm()(request)

      val newResult: Future[Result] = TestApplicationController.addBookForm()(request)

      status(newResult) shouldBe Status.INTERNAL_SERVER_ERROR

      afterEach()
    }
  }

  "ApplicationController .addBookForm() error case" should {

    "fail to store book entered through a form in database due to empty form" in {
      beforeEach()

      val request: FakeRequest[AnyContentAsFormUrlEncoded] = buildPost("/addnewbook/form").withFormUrlEncodedBody("_id" -> "", "name" -> "", "description" -> "", "pageCount" -> "", "isbn" -> "")
      val createdResult: Future[Result] = TestApplicationController.addBookForm()(request)

      status(createdResult) shouldBe Status.INTERNAL_SERVER_ERROR

      afterEach()
    }
  }

  "ApplicationController .addBookForm() error case" should {

    "fail to store book entered through a form in database due to text inserted instead of number" in {
      beforeEach()

      val request: FakeRequest[AnyContentAsFormUrlEncoded] = buildPost("/addnewbook/form").withFormUrlEncodedBody("_id" -> "3", "name" -> "Omiros", "description" -> "Trypatsas", "pageCount" -> "wrong", "isbn" -> "3598585")
      val createdResult: Future[Result] = TestApplicationController.addBookForm()(request)

      status(createdResult) shouldBe Status.INTERNAL_SERVER_ERROR

      afterEach()
    }
  }

  override def beforeEach(): Unit = await(repository.deleteAll())
  override def afterEach(): Unit = await(repository.deleteAll())

}

