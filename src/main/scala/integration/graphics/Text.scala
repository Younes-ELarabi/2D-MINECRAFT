package integration.graphics

import java.awt.{Color, Font, FontMetrics, Graphics}

object Text {
  def drawString(g: Graphics, text: String, xPos: Int, yPos: Int, center: Boolean, c: Color, font: Font): Unit = {
    g.setColor(c)
    g.setFont(font)
    var x :Int = xPos
    var y :Int = yPos
    if (center) {
      val fm :FontMetrics = g.getFontMetrics(font)
      x = xPos - fm.stringWidth(text) / 2
      y = (yPos - fm.getHeight / 2) + fm.getAscent
    }
    g.drawString(text, x, y)
  }
}