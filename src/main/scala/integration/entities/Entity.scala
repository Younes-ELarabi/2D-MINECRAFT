package integration.entities

import java.awt.Graphics
import java.awt.Rectangle
import util.control.Breaks._
import integration.launcher.Handler

object Entity {
  val DEFAULT_HEALTH :Int = 3

  def getDefaultHealth: Int = DEFAULT_HEALTH
}

abstract class Entity(var handler :Handler, var x:Float ,var y: Float,var width :Int , var height :Int) {

  var bounds :Rectangle = new Rectangle(0,0,width,height)
  var active :Boolean = true
  var health :Int = Entity.DEFAULT_HEALTH

  def update(): Unit

  def render(g: Graphics): Unit

  def die(): Unit

  def hurt(amt: Int): Unit = {
    health -= amt
    if (health <= 0) {
      active = false
        die()
      }
  }

    def checkEntityCollisions(xOffset: Float, yOffset: Float): Boolean = {
      val size: Int = handler.getWorld.getEntityManager.getEntities.size()
      for (i <- 0 until size) {
        breakable{
          if(handler.getWorld.getEntityManager.getEntities.get(i) equals this){
            break;
          }else if((handler.getWorld.getEntityManager.getEntities.get(i).getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))){
            return true
          }
        }
      }
      false;
    }

    def getCollisionBounds(xOffset: Float, yOffset: Float) = new Rectangle((x + bounds.x + xOffset).asInstanceOf[Int], (y + bounds.y + yOffset).asInstanceOf[Int], bounds.width, bounds.height)

    def getX: Float = x

    def setX(x: Float): Unit = {
      this.x = x
    }

    def getY: Float = this.y

    def setY(y: Float): Unit = {
      this.y = y
    }

    def getWidth = this.width

    def setWidth(width: Int): Unit = {
      this.width = width
    }

    def getHeight = this.height

    def setHeight(height: Int): Unit = {
      this.height = height
    }

    def getHealth: Int = health

    def setHealth(health: Int): Unit = {
      this.health = health
    }

    def isActive: Boolean = active

    def setActive(active: Boolean): Unit = {
      this.active = active
    }

    def getHandler: Handler = handler

    def setHandler(handler: Handler): Unit = {
      this.handler = handler
    }

    def getBounds: Rectangle = bounds

    def setBounds(bounds: Rectangle): Unit = {
      this.bounds = bounds
    }

}
