package integration.creatures

import integration.cubes.Cube
import integration.entities.Entity
import integration.launcher.Handler

object Creature {
  val DEFAULT_SPEED = 3.0f
  val DEFAULT_CREATURE_WIDTH = 64
  val DEFAULT_CREATURE_HEIGHT = 64
}

abstract class Creature(handler: Handler,x: Float,y: Float,width: Int,height: Int) extends Entity(handler, x, y, width, height) {

  var speed :Float = Creature.DEFAULT_SPEED
  var xMove :Float = 0
  var yMove :Float = 0
  var context :MovementContext = _

  def move(): Unit = {
    if (!checkEntityCollisions(xMove, 0f)) {
      moveX()
    }
    context.nextMovementState()
  }

  def moveX(): Unit = {
    if (xMove > 0) { //Moving right
      val tx = (getX + xMove + bounds.x + bounds.width).asInstanceOf[Int] / Cube.CUBEWIDTH
      if (!collisionWithCube(tx, (getY + bounds.y).asInstanceOf[Int] / Cube.CUBEHEIGHT)
        && !collisionWithCube(tx, (getY + bounds.y + bounds.height).asInstanceOf[Int] / Cube.CUBEHEIGHT)) { //no collision
        setX(getX + xMove)
      }
      else { //collision
        // move as far as possible and stop
        setX(tx * Cube.CUBEWIDTH - bounds.x - bounds.width - 1)
      }
    }
    else if (xMove < 0) { //Moving left
      val tx = (getX + xMove + bounds.x).asInstanceOf[Int] / Cube.CUBEWIDTH
      if (!collisionWithCube(tx, (getY + bounds.y).asInstanceOf[Int] / Cube.CUBEHEIGHT)
        && !collisionWithCube(tx, (getY + bounds.y + bounds.height).asInstanceOf[Int] / Cube.CUBEHEIGHT)) {
        setX(getX + xMove)
      } else {
        setX(tx * Cube.CUBEWIDTH + Cube.CUBEWIDTH - bounds.x)
      }
    }
  }

  def collisionWithCube(x: Int, y: Int): Boolean = handler.getWorld.getCube(x, y).isSolid
  //GETTERS SETTERS
  def getxMove: Float = this.xMove

  def setxMove(xMove: Float): Unit = {
    this.xMove = xMove
  }

  def getyMove: Float = this.yMove

  def setyMove(yMove: Float): Unit = {
    this.yMove = yMove
  }

  def getSpeed: Float = this.speed

  def setSpeed(speed: Float): Unit = {
    this.speed = speed
  }

}

