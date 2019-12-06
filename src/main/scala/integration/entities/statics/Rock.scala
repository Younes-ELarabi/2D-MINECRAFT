package integration.entities.statics

import java.awt.Graphics
import integration.cubes.Cube
import integration.graphics.Assets
import integration.items.Item
import integration.launcher.Handler

class Rock(handler: Handler,x: Float,y: Float) extends StaticEntity(handler, x, y, Cube.CUBEWIDTH, Cube.CUBEHEIGHT) {

  bounds.x = 3
  bounds.width = width - 6

  override def update(): Unit = {
  }

  override def die(): Unit = {
    handler.getWorld.getItemManager.addItem(Item.rockItem.createNew(x.asInstanceOf[Int], y.asInstanceOf[Int]))
  }

  def render(g: Graphics): Unit = {
    g.drawImage(Assets.rock, (x - handler.getGameCamera.getxOffset).asInstanceOf[Int], (y - handler.getGameCamera.getyOffset).asInstanceOf[Int], width, height, null)
  }
}
