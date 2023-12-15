package controllers

import baseSpec.BaseSpecWithApplication
import play.api.test.FakeRequest
import play.api.http.Status
import play.api.test.Helpers._
import org.scalatestplus.play.guice.GuiceOneAppPerSuite

class ApplicationControllerSpec extends BaseSpecWithApplication{

  val TestApplicationController = new ApplicationController(
    repository,
    component
  )

  private val dataModel: DataModel = DataModel(
    "abcd",
    "test name",
    "test description",
    100
  )

  "ApplicationController .index()" should {
    beforeEach()
    val result = TestApplicationController.index()(FakeRequest())

    "return TODO" in {
      status(result) shouldBe Status.OK
    }
    afterEach()
  }

  "ApplicationController .create" should {

    "create a book in the database" in {
      beforeEach()
      val request: FakeRequest[JsValue] = buildPost("/api").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)

      status(createdResult) shouldBe Status.???
      afterEach()
    }
  }

  "ApplicationController .read" should {

    "find a book in the database by id" in {
      beforeEach()
      val request: FakeRequest[JsValue] = buildGet("/api/${dataModel._id}").withBody[JsValue](Json.toJson(dataModel))
      val createdResult: Future[Result] = TestApplicationController.create()(request)

      //Hint: You could use status(createdResult) shouldBe Status.CREATED to check this has worked again

      val readResult: Future[Result] = TestApplicationController.read("abcd")(FakeRequest())

      status(readResult) shouldBe ???
      contentAsJson(readResult).as[???] shouldBe ???
      afterEach()
    }
  }

  "ApplicationController .update()" should {
    beforeEach()
    val result = TestApplicationController.update("")(FakeRequest())

    "return TODO" in {
      status(result) shouldBe Status.NOT_IMPLEMENTED
      afterEach()
    }
  }

  "ApplicationController .delete()" should {
    beforeEach()
    val result = TestApplicationController.delete("")(FakeRequest())

    "return TODO" in {
      status(result) shouldBe Status.NOT_IMPLEMENTED
      afterEach()
    }
  }

  override def beforeEach(): Unit = repository.deleteAll()

  override def afterEach(): Unit = repository.deleteAll()

}

