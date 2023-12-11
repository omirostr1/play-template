package controllers

import play.api.mvc.{BaseController, ControllerComponents}
import javax.inject.{Singleton, Inject}

@Singleton
class ApplicationController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def index() = TODO
}

class CreateController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def create() = TODO
}

class ReadController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def read(id: String) = TODO
}

class UpdateController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def update(id: String) = TODO
}

class DeleteController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def delete(id: String) = TODO
}

