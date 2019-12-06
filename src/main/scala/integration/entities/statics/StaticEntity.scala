package integration.entities.statics
import integration.entities.Entity
import integration.launcher.Handler

// class for entities that don't move

abstract class StaticEntity(handler: Handler,x: Float,y: Float,width: Int,height: Int) extends Entity(handler, x, y, width, height) {}
