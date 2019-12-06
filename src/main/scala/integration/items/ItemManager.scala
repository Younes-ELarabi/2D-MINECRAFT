package integration.items

import java.awt.Graphics
import java.util
import integration.launcher.Handler

class ItemManager{

  private var handler :Handler = _
  private var items :util.ArrayList[Item]= _

  def this(handler: Handler) {
    this()
    this.handler = handler
    items = new util.ArrayList[Item]
  }

  def update(): Unit = {
    val it = items.iterator
    while ( {it.hasNext}) {
      val i = it.next
      i.update
      if (i.isPickedUp) it.remove
    }
  }

  def render(g: Graphics): Unit = {
    var size :Int = items.size()
    for (i <- 0 until size) {
      items.get(i).render(g);
    }
  }

  def addItem(i: Item): Unit = {
    i.setHandler(handler)
    items.add(i)
  }

  // Getters and Setters

  def getHandler: Handler = handler

  def setHandler(handler: Handler): Unit = {
    this.handler = handler
  }



}


