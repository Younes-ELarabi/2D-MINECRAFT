package integration.ui

import java.awt.Graphics
import java.awt.event.MouseEvent
import java.util

import integration.launcher.Handler

class UIManager
{
  private var handler :Handler = _
  private var objects :util.ArrayList[UIObject] = _

  def this(handler: Handler) {
    this()
    this.handler = handler
    this.objects = new util.ArrayList[UIObject]
  }

  def update(): Unit = {
    val size :Int = objects.size()
    for (i <- 0 until size) {
      objects.get(i).update()
    }
  }

  def render(g: Graphics): Unit = {
    val size :Int = objects.size()
    for (i <- 0 until size) {
      objects.get(i).render(g)
    }
  }

  def onMouseMove(e: MouseEvent): Unit = {
    val size :Int = objects.size()
    for (i <- 0 until size) {
      objects.get(i).onMouseMove(e)
    }
  }

  def onMouseRelease(e: MouseEvent): Unit = {
    val size :Int = objects.size()
    for (i <- 0 until size) {
      objects.get(i).onMouseRelease(e)
    }
  }

  def addObject(o: UIObject): Unit = {
    this.objects.add(o)
  }

  def removeObject(o: UIObject): Unit = {
    this.objects.remove(o)
  }

  def getHandler: Handler = handler

  def setHandler(handler: Handler): Unit = {
    this.handler = handler
  }

  def getObjects: util.ArrayList[UIObject] = objects

  def setObjects(objects: util.ArrayList[UIObject]): Unit = {
    this.objects = objects
  }


}
