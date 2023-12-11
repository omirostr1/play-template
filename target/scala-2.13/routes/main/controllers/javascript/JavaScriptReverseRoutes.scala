// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:2
package controllers.javascript {

  // @LINE:5
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:20
  class ReverseDeleteController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:20
    def index4: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.DeleteController.index4",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "api/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
  }

  // @LINE:11
  class ReverseCreateController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def index1: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CreateController.index1",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api2"})
        }
      """
    )
  
  }

  // @LINE:14
  class ReverseReadController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:14
    def index2: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ReadController.index2",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "get/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
  }

  // @LINE:8
  class ReverseApplicationController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ApplicationController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api"})
        }
      """
    )
  
  }

  // @LINE:2
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:2
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:17
  class ReverseUpdateController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def index3: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UpdateController.index3",
      """
        function(id0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "put/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
  }


}
