package integration.graphics

import integration.cubes.Cube
import integration.entities.Entity
import integration.launcher.Handler

class GameCamera {

  private var handler :Handler = null
  private var xOffset :Float = 0
  private var yOffset :Float = 0

  def this(handler: Handler, xOffset: Float, yOffset: Float) {
    this()
    this.handler = handler
    this.xOffset = xOffset
    this.yOffset = yOffset
  }

  def checkBlankSpace(): Unit = {
    if (xOffset < 0) xOffset = 0
    else if (xOffset > handler.getWorld.getWidth * Cube.CUBEWIDTH - handler.getWidth) xOffset = handler.getWorld.getWidth * Cube.CUBEWIDTH - handler.getWidth
    if (yOffset < 0) yOffset = 0
    else if (yOffset > handler.getWorld.getHeight * Cube.CUBEHEIGHT - handler.getHeight) yOffset = handler.getWorld.getHeight * Cube.CUBEHEIGHT - handler.getHeight
  }

  def centerOnEntity(e: Entity): Unit = {
    xOffset = e.getX - handler.getWidth / 2 + e.getWidth / 2
    yOffset = e.getY - handler.getHeight / 2 + e.getHeight / 2
    checkBlankSpace()
  }

  def move(xAmt: Float, yAmt: Float): Unit = {
    xOffset += xAmt
    yOffset += yAmt
    checkBlankSpace()
  }

  def getxOffset: Float = xOffset

  def setxOffset(xOffset: Float): Unit = {
    this.xOffset = xOffset
  }

  def getyOffset: Float = yOffset

  def setyOffset(yOffset: Float): Unit = {
    this.yOffset = yOffset
  }

}
