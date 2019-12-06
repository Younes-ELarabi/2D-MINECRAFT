package integration.items

import java.awt.{Graphics, Rectangle}

import integration.graphics.Assets
import java.awt.image.BufferedImage

import integration.launcher.Handler

object Item { // Handler
  var items = new Array[Item](256)
  var woodItem = new Item(Assets.wood, "Wood", 0)
  var rockItem = new Item(Assets.rock, "Rock", 1)
  // Class
  val ITEMWIDTH = 32
  val ITEMHEIGHT = 32
}

class Item(var texture: BufferedImage, var name: String, var id: Int) {
  var handler: Handler = _
  var x :Int = 0
  var y :Int = 0
  var count :Int  = 1
  var bounds :Rectangle = new Rectangle(x, y, Item.ITEMWIDTH, Item.ITEMHEIGHT)
  Item.items(id) = this
  var pickedUp : Boolean = false

  def update(): Unit = {
    if (handler.getWorld.getEntityManager.getPlayer.getCollisionBounds(0f, 0f).intersects(bounds)) {
      pickedUp = true
      handler.getWorld.getEntityManager.getPlayer.getInventory.addItem(this)
    }
  }

  def render(g: Graphics): Unit = {
    if (handler == null) return
    render(g, (x - handler.getGameCamera.getxOffset).asInstanceOf[Int], (y - handler.getGameCamera.getyOffset).asInstanceOf[Int])
  }

  def render(g: Graphics, x: Int, y: Int): Unit = {
    g.drawImage(texture, x, y, Item.ITEMWIDTH, Item.ITEMHEIGHT, null)
  }

  def createNew(count: Int): Item = {
    val i = new Item(texture, name, id)
    i.setPickedUp(true)
    i.setCount(count)
    i
  }

  def createNew(x: Int, y: Int): Item = {
    val i = new Item(texture, name, id)
    i.setPosition(x, y)
    i
  }

  def setPosition(x: Int, y: Int): Unit = {
    this.x = x
    this.y = y
    bounds.x = x
    bounds.y = y
  }

  // Getters and Setters
  def getHandler: Handler = handler

  def setPickedUp(pickedUp: Boolean): Unit = {
    this.pickedUp = pickedUp
  }

  def setHandler(handler: Handler): Unit = {
    this.handler = handler
  }

  def getTexture: BufferedImage = texture

  def setTexture(texture: BufferedImage): Unit = {
    this.texture = texture
  }

  def getName: String = name

  def setName(name: String): Unit = {
    this.name = name
  }

  def getX: Int = x

  def setX(x: Int): Unit = {
    this.x = x
  }

  def getY: Int = y

  def setY(y: Int): Unit = {
    this.y = y
  }

  def getCount: Int = count

  def setCount(count: Int): Unit = {
    this.count = count
  }

  def getId: Int = id

  def isPickedUp: Boolean = pickedUp
}