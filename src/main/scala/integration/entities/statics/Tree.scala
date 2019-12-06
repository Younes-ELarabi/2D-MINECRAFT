package integration.entities.statics

import java.awt.{Color, Graphics}

import integration.cubes.Cube
import integration.graphics.Assets
import integration.items.Item
import integration.launcher.Handler

class Tree(handler: Handler, x: Float, y: Float) extends StaticEntity(handler, x, y, Cube.CUBEWIDTH, Cube.CUBEHEIGHT * 2) {
  bounds.x = 3
  bounds.y = 7
  bounds.width = width - 6

  override def update(): Unit = {
  }

  override def die(): Unit = {
    handler.getWorld.getItemManager.addItem(Item.woodItem.createNew(x.asInstanceOf[Int], y.asInstanceOf[Int]))
  }

  def render(g: Graphics): Unit = {
    g.drawImage(Assets.tree, (x - handler.getGameCamera.getxOffset).asInstanceOf[Int], (y - handler.getGameCamera.getyOffset).asInstanceOf[Int], width, height, null)
  }
}
