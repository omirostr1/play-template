package controllers

import baseSpec.BaseSpecWithApplication
import models.DataModel
import play.api.test.FakeRequest
import play.api.http.Status
import play.api.test.Helpers._
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.mvc.{Result, ControllerComponents}
import play.api.libs.json._
import scala.concurrent.Future

class ApplicationControllerSpec extends BaseSpecWithApplication{

  val TestApplicationController = new ApplicationController(
    component,
    repository
  )

  private val dataModel: DataModel = DataModel(
    "abcd",
    "test name",
    "test description",
    100
  )

  private val dataModel1: DataModel = DataModel(
    "abcd",
    "test name1",
    "test description",
    100
  )

  "ApplicationController .index()" should {

    "return TODO" in {
      beforeEach()
      val result = TestApplicationController.index()(FakeRequest())
      status(result) shouldBe Status.OK
      afterEach()
    }
  }

  "ApplicationController .create" should {

    "create a book in the database" in {
      beforeEach()
      val request: FakeRequest[JsValue] = buildPost("/api").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)

      status(createdResult) shouldBe Status.CREATED
      afterEach()
    }
  }

  "ApplicationController .read()" should {

    "find a book in the database by id" in {
      beforeEach()
      val request: FakeRequest[JsValue] = buildPost(s"/api/${dataModel._id}").withBody[JsValue](Json.toJson(dataModel))
      //val createdResult: Future[Result] = TestApplicationController.create()(request)
      val createdResult: Result = await(TestApplicationController.create()(request))
      assert(createdResult.header.status == Status.CREATED)
      //Hint: You could use status(createdResult) shouldBe Status.CREATED to check this has worked again

      val readResult: Future[Result] = TestApplicationController.read("abcd")(FakeRequest())

      assert(status(readResult) == Status.OK)
      contentAsJson(readResult).as[JsValue] shouldBe Json.toJson(dataModel)
      afterEach()
    }
  }

  "ApplicationController .update()" should {

    "return TODO" in {
      beforeEach()

      val request: FakeRequest[JsValue] = buildGet("/api/${dataModel._id}").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)
      status(createdResult) shouldBe Status.CREATED

      val updateRequest: FakeRequest[JsValue] = buildPost(s"/api/${dataModel._id}").withBody[JsValue](Json.toJson(dataModel1))
      val result = TestApplicationController.update(id = "abcd")(FakeRequest())

      status(result) shouldBe Status.ACCEPTED

      afterEach()
    }
  }

  "ApplicationController .delete()" should {

    "return TODO" in {
      beforeEach()

      val request: FakeRequest[JsValue] = buildPost("/api/${dataModel._id}").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)
      status(createdResult) shouldBe Status.CREATED

      val result = TestApplicationController.delete("abcd")(FakeRequest())

      status(result) shouldBe Status.ACCEPTED

      afterEach()
    }
  }

  override def beforeEach(): Unit = repository.deleteAll()
  override def afterEach(): Unit = repository.deleteAll()

}

