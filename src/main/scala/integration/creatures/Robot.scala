package integration.creatures

import java.awt.Graphics
import java.awt.image.BufferedImage

import integration.graphics.{Animation, Assets}
import integration.launcher.Handler

import scala.util.Random

class Robot(handler: Handler,x: Float,y: Float) extends Creature (handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT) {

  // Todo
  bounds.x = 22
  bounds.y = 8
  bounds.width = 22
  bounds.height = 55

  var animLeft  = new Animation(500, Assets.robot_left)
  var animRight = new Animation(500, Assets.robot_right)

  context = new MovementContext(this)

  var now = System.nanoTime
  var timeElapsed = System.nanoTime - now

  def computeTime :Unit = {
    timeElapsed = System.nanoTime - now
  }

  def getInput(): Unit = {
    if( timeElapsed > 1000000000 ){
      val r = new Random()
      r.setSeed(System.nanoTime())
      xMove = r.nextInt(3) -1;
      yMove = r.nextInt(2) -1;
      now = System.nanoTime
    }
  }

  override def render(g: Graphics): Unit = {
    g.drawImage(getCurrentAnimationFrame, (getX - handler.getGameCamera.getxOffset).asInstanceOf[Int], (getY - handler.getGameCamera.getyOffset).asInstanceOf[Int], width, height, null)
  }

  def getCurrentAnimationFrame: BufferedImage = {
    if (xMove < 0) animLeft.getCurrentFrame
    else if (xMove > 0) animRight.getCurrentFrame
    else if (yMove < 0) animRight.getCurrentFrame
    else animRight.getCurrentFrame
  }

  override def update(): Unit = {
    //Animations/
    animRight.update()
    animLeft.update()
    //Movement
    getInput()
    move()
    computeTime
  }

  override def die(): Unit = {

  }

}

