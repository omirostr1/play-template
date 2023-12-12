// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:2
  HomeController_6: controllers.HomeController,
  // @LINE:5
  Assets_4: controllers.Assets,
  // @LINE:8
  ApplicationController_2: controllers.ApplicationController,
  // @LINE:11
  CreateController_3: controllers.CreateController,
  // @LINE:14
  ReadController_5: controllers.ReadController,
  // @LINE:17
  UpdateController_1: controllers.UpdateController,
  // @LINE:20
  DeleteController_0: controllers.DeleteController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:2
    HomeController_6: controllers.HomeController,
    // @LINE:5
    Assets_4: controllers.Assets,
    // @LINE:8
    ApplicationController_2: controllers.ApplicationController,
    // @LINE:11
    CreateController_3: controllers.CreateController,
    // @LINE:14
    ReadController_5: controllers.ReadController,
    // @LINE:17
    UpdateController_1: controllers.UpdateController,
    // @LINE:20
    DeleteController_0: controllers.DeleteController
  ) = this(errorHandler, HomeController_6, Assets_4, ApplicationController_2, CreateController_3, ReadController_5, UpdateController_1, DeleteController_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_6, Assets_4, ApplicationController_2, CreateController_3, ReadController_5, UpdateController_1, DeleteController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api""", """controllers.ApplicationController.index"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api2""", """controllers.CreateController.create"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """get/""" + "$" + """id<[^/]+>""", """controllers.ReadController.read(id:String)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """put/""" + "$" + """id<[^/]+>""", """controllers.UpdateController.update(id:String)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/""" + "$" + """id<[^/]+>""", """controllers.DeleteController.delete(id:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:2
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_6.index(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:5
  private[this] lazy val controllers_Assets_versioned1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned1_invoker = createInvoker(
    Assets_4.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_ApplicationController_index2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api")))
  )
  private[this] lazy val controllers_ApplicationController_index2_invoker = createInvoker(
    ApplicationController_2.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "index",
      Nil,
      "GET",
      this.prefix + """api""",
      """ App route that references the new controller and method""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_CreateController_create3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api2")))
  )
  private[this] lazy val controllers_CreateController_create3_invoker = createInvoker(
    CreateController_3.create,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CreateController",
      "create",
      Nil,
      "POST",
      this.prefix + """api2""",
      """ App route that references the new controller and method""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_ReadController_read4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("get/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ReadController_read4_invoker = createInvoker(
    ReadController_5.read(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ReadController",
      "read",
      Seq(classOf[String]),
      "GET",
      this.prefix + """get/""" + "$" + """id<[^/]+>""",
      """ App route that references the new controller and method""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_UpdateController_update5_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("put/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_UpdateController_update5_invoker = createInvoker(
    UpdateController_1.update(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UpdateController",
      "update",
      Seq(classOf[String]),
      "PUT",
      this.prefix + """put/""" + "$" + """id<[^/]+>""",
      """ App route that references the new controller and method""",
      Seq()
    )
  )

  // @LINE:20
  private[this] lazy val controllers_DeleteController_delete6_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_DeleteController_delete6_invoker = createInvoker(
    DeleteController_0.delete(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.DeleteController",
      "delete",
      Seq(classOf[String]),
      "DELETE",
      this.prefix + """api/""" + "$" + """id<[^/]+>""",
      """ App route that references the new controller and method""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:2
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_6.index())
      }
  
    // @LINE:5
    case controllers_Assets_versioned1_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned1_invoker.call(Assets_4.versioned(path, file))
      }
  
    // @LINE:8
    case controllers_ApplicationController_index2_route(params@_) =>
      call { 
        controllers_ApplicationController_index2_invoker.call(ApplicationController_2.index)
      }
  
    // @LINE:11
    case controllers_CreateController_create3_route(params@_) =>
      call { 
        controllers_CreateController_create3_invoker.call(CreateController_3.create)
      }
  
    // @LINE:14
    case controllers_ReadController_read4_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_ReadController_read4_invoker.call(ReadController_5.read(id))
      }
  
    // @LINE:17
    case controllers_UpdateController_update5_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_UpdateController_update5_invoker.call(UpdateController_1.update(id))
      }
  
    // @LINE:20
    case controllers_DeleteController_delete6_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_DeleteController_delete6_invoker.call(DeleteController_0.delete(id))
      }
  }
}
