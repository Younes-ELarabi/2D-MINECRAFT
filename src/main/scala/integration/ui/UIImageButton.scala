package integration.ui

import java.awt.Graphics
import java.awt.image.BufferedImage

class UIImageButton(x: Float,  y: Float,  width: Int,  height: Int,images: Array[BufferedImage],clicker: ClickListener) extends UIObject(x, y, width, height) {

  override def update(): Unit = {}

  def render(g: Graphics): Unit = {
    if (hovering) g.drawImage(images(1), x.asInstanceOf[Int], y.asInstanceOf[Int], width, height, null)
    else g.drawImage(images(0), x.asInstanceOf[Int], y.asInstanceOf[Int], width, height, null)
  }

  override def onClick(): Unit = {
    clicker.onClick
  }

}
