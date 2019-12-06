package integration.ui

import java.awt.event.MouseEvent
import java.awt.{Graphics, Rectangle}

abstract class UIObject(var x: Float, var y: Float, var width: Int, var height: Int) {

  var bounds :Rectangle = new Rectangle(x.toInt, y.toInt, width, height)
  protected var hovering :Boolean = false

  def update(): Unit

  def render(g: Graphics): Unit

  def onClick(): Unit

  def onMouseMove(e: MouseEvent): Unit = {
    if (bounds.contains(e.getX, e.getY)) hovering = true
    else hovering = false
  }

  def onMouseRelease(e: MouseEvent): Unit = {
    if (hovering) onClick()
  }

  // Getters and setters
  def getX: Float = x

  def setX(x: Float): Unit = {
    this.x = x
  }

  def getY: Float = y

  def setY(y: Float): Unit = {
    this.y = y
  }

  def getWidth: Int = width

  def setWidth(width: Int): Unit = {
    this.width = width
  }

  def getHeight: Int = height

  def setHeight(height: Int): Unit = {
    this.height = height
  }

  def isHovering: Boolean = hovering

  def setHovering(hovering: Boolean): Unit = {
    this.hovering = hovering
  }
}

