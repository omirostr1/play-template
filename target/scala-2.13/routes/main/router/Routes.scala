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
  HomeController_0: controllers.HomeController,
  // @LINE:4
  ApplicationController_1: controllers.ApplicationController,
  // @LINE:7
  Assets_2: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:2
    HomeController_0: controllers.HomeController,
    // @LINE:4
    ApplicationController_1: controllers.ApplicationController,
    // @LINE:7
    Assets_2: controllers.Assets
  ) = this(errorHandler, HomeController_0, ApplicationController_1, Assets_2, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, ApplicationController_1, Assets_2, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """example/""" + "$" + """id<[^/]+>""", """controllers.ApplicationController.example(id:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api""", """controllers.ApplicationController.index"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """create""", """controllers.ApplicationController.create()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """read/""" + "$" + """id<[^/]+>""", """controllers.ApplicationController.read(id:String)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """update/""" + "$" + """id<[^/]+>""", """controllers.ApplicationController.update(id:String)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """delete/""" + "$" + """id<[^/]+>""", """controllers.ApplicationController.delete(id:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """library/google/""" + "$" + """search<[^/]+>/""" + "$" + """term<[^/]+>""", """controllers.ApplicationController.getGoogleBook(search:String, term:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """readByAnyField/""" + "$" + """field<[^/]+>/""" + "$" + """term<[^/]+>""", """controllers.ApplicationController.readByAnyField(field:String, term:String)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """updateSpecificField/""" + "$" + """id<[^/]+>/""" + "$" + """field<[^/]+>/""" + "$" + """change<[^/]+>""", """controllers.ApplicationController.updateSpecificField(id:String, field:String, change:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """library/store/google/book/""" + "$" + """search<[^/]+>/""" + "$" + """term<[^/]+>""", """controllers.ApplicationController.storeGoogleBook(search:String, term:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """addnewbook/form""", """controllers.ApplicationController.addBook()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """addnewbook/form""", """controllers.ApplicationController.addBookForm()"""),
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
    HomeController_0.index,
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

  // @LINE:4
  private[this] lazy val controllers_ApplicationController_example1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("example/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_example1_invoker = createInvoker(
    ApplicationController_1.example(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "example",
      Seq(classOf[String]),
      "GET",
      this.prefix + """example/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_Assets_versioned2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned2_invoker = createInvoker(
    Assets_2.versioned(fakeValue[String], fakeValue[Asset]),
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

  // @LINE:10
  private[this] lazy val controllers_ApplicationController_index3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api")))
  )
  private[this] lazy val controllers_ApplicationController_index3_invoker = createInvoker(
    ApplicationController_1.index,
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

  // @LINE:13
  private[this] lazy val controllers_ApplicationController_create4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("create")))
  )
  private[this] lazy val controllers_ApplicationController_create4_invoker = createInvoker(
    ApplicationController_1.create(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "create",
      Nil,
      "POST",
      this.prefix + """create""",
      """ App route that references the new controller and method""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_ApplicationController_read5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("read/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_read5_invoker = createInvoker(
    ApplicationController_1.read(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "read",
      Seq(classOf[String]),
      "GET",
      this.prefix + """read/""" + "$" + """id<[^/]+>""",
      """ App route that references the new controller and method""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_ApplicationController_update6_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("update/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_update6_invoker = createInvoker(
    ApplicationController_1.update(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "update",
      Seq(classOf[String]),
      "PUT",
      this.prefix + """update/""" + "$" + """id<[^/]+>""",
      """ App route that references the new controller and method""",
      Seq()
    )
  )

  // @LINE:22
  private[this] lazy val controllers_ApplicationController_delete7_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("delete/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_delete7_invoker = createInvoker(
    ApplicationController_1.delete(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "delete",
      Seq(classOf[String]),
      "DELETE",
      this.prefix + """delete/""" + "$" + """id<[^/]+>""",
      """ App route that references the new controller and method""",
      Seq()
    )
  )

  // @LINE:24
  private[this] lazy val controllers_ApplicationController_getGoogleBook8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("library/google/"), DynamicPart("search", """[^/]+""",true), StaticPart("/"), DynamicPart("term", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_getGoogleBook8_invoker = createInvoker(
    ApplicationController_1.getGoogleBook(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "getGoogleBook",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """library/google/""" + "$" + """search<[^/]+>/""" + "$" + """term<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:27
  private[this] lazy val controllers_ApplicationController_readByAnyField9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("readByAnyField/"), DynamicPart("field", """[^/]+""",true), StaticPart("/"), DynamicPart("term", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_readByAnyField9_invoker = createInvoker(
    ApplicationController_1.readByAnyField(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "readByAnyField",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """readByAnyField/""" + "$" + """field<[^/]+>/""" + "$" + """term<[^/]+>""",
      """ App route that references the new controller and method""",
      Seq()
    )
  )

  // @LINE:30
  private[this] lazy val controllers_ApplicationController_updateSpecificField10_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("updateSpecificField/"), DynamicPart("id", """[^/]+""",true), StaticPart("/"), DynamicPart("field", """[^/]+""",true), StaticPart("/"), DynamicPart("change", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_updateSpecificField10_invoker = createInvoker(
    ApplicationController_1.updateSpecificField(fakeValue[String], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "updateSpecificField",
      Seq(classOf[String], classOf[String], classOf[String]),
      "PUT",
      this.prefix + """updateSpecificField/""" + "$" + """id<[^/]+>/""" + "$" + """field<[^/]+>/""" + "$" + """change<[^/]+>""",
      """ App route that references the new controller and method""",
      Seq()
    )
  )

  // @LINE:32
  private[this] lazy val controllers_ApplicationController_storeGoogleBook11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("library/store/google/book/"), DynamicPart("search", """[^/]+""",true), StaticPart("/"), DynamicPart("term", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_storeGoogleBook11_invoker = createInvoker(
    ApplicationController_1.storeGoogleBook(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "storeGoogleBook",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """library/store/google/book/""" + "$" + """search<[^/]+>/""" + "$" + """term<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:36
  private[this] lazy val controllers_ApplicationController_addBook12_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("addnewbook/form")))
  )
  private[this] lazy val controllers_ApplicationController_addBook12_invoker = createInvoker(
    ApplicationController_1.addBook(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "addBook",
      Nil,
      "GET",
      this.prefix + """addnewbook/form""",
      """""",
      Seq()
    )
  )

  // @LINE:38
  private[this] lazy val controllers_ApplicationController_addBookForm13_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("addnewbook/form")))
  )
  private[this] lazy val controllers_ApplicationController_addBookForm13_invoker = createInvoker(
    ApplicationController_1.addBookForm(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "addBookForm",
      Nil,
      "POST",
      this.prefix + """addnewbook/form""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:2
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_0.index)
      }
  
    // @LINE:4
    case controllers_ApplicationController_example1_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_ApplicationController_example1_invoker.call(ApplicationController_1.example(id))
      }
  
    // @LINE:7
    case controllers_Assets_versioned2_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned2_invoker.call(Assets_2.versioned(path, file))
      }
  
    // @LINE:10
    case controllers_ApplicationController_index3_route(params@_) =>
      call { 
        controllers_ApplicationController_index3_invoker.call(ApplicationController_1.index)
      }
  
    // @LINE:13
    case controllers_ApplicationController_create4_route(params@_) =>
      call { 
        controllers_ApplicationController_create4_invoker.call(ApplicationController_1.create())
      }
  
    // @LINE:16
    case controllers_ApplicationController_read5_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_ApplicationController_read5_invoker.call(ApplicationController_1.read(id))
      }
  
    // @LINE:19
    case controllers_ApplicationController_update6_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_ApplicationController_update6_invoker.call(ApplicationController_1.update(id))
      }
  
    // @LINE:22
    case controllers_ApplicationController_delete7_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_ApplicationController_delete7_invoker.call(ApplicationController_1.delete(id))
      }
  
    // @LINE:24
    case controllers_ApplicationController_getGoogleBook8_route(params@_) =>
      call(params.fromPath[String]("search", None), params.fromPath[String]("term", None)) { (search, term) =>
        controllers_ApplicationController_getGoogleBook8_invoker.call(ApplicationController_1.getGoogleBook(search, term))
      }
  
    // @LINE:27
    case controllers_ApplicationController_readByAnyField9_route(params@_) =>
      call(params.fromPath[String]("field", None), params.fromPath[String]("term", None)) { (field, term) =>
        controllers_ApplicationController_readByAnyField9_invoker.call(ApplicationController_1.readByAnyField(field, term))
      }
  
    // @LINE:30
    case controllers_ApplicationController_updateSpecificField10_route(params@_) =>
      call(params.fromPath[String]("id", None), params.fromPath[String]("field", None), params.fromPath[String]("change", None)) { (id, field, change) =>
        controllers_ApplicationController_updateSpecificField10_invoker.call(ApplicationController_1.updateSpecificField(id, field, change))
      }
  
    // @LINE:32
    case controllers_ApplicationController_storeGoogleBook11_route(params@_) =>
      call(params.fromPath[String]("search", None), params.fromPath[String]("term", None)) { (search, term) =>
        controllers_ApplicationController_storeGoogleBook11_invoker.call(ApplicationController_1.storeGoogleBook(search, term))
      }
  
    // @LINE:36
    case controllers_ApplicationController_addBook12_route(params@_) =>
      call { 
        controllers_ApplicationController_addBook12_invoker.call(ApplicationController_1.addBook())
      }
  
    // @LINE:38
    case controllers_ApplicationController_addBookForm13_route(params@_) =>
      call { 
        controllers_ApplicationController_addBookForm13_invoker.call(ApplicationController_1.addBookForm())
      }
  }
}
