// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseDeleteController DeleteController = new controllers.ReverseDeleteController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseCreateController CreateController = new controllers.ReverseCreateController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseReadController ReadController = new controllers.ReverseReadController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseApplicationController ApplicationController = new controllers.ReverseApplicationController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseUpdateController UpdateController = new controllers.ReverseUpdateController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseDeleteController DeleteController = new controllers.javascript.ReverseDeleteController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseCreateController CreateController = new controllers.javascript.ReverseCreateController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseReadController ReadController = new controllers.javascript.ReverseReadController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseApplicationController ApplicationController = new controllers.javascript.ReverseApplicationController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseUpdateController UpdateController = new controllers.javascript.ReverseUpdateController(RoutesPrefix.byNamePrefix());
  }

}
