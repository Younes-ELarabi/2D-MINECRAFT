package integration.graphics

import java.awt.image.BufferedImage

class SpriteSheet {

  private var sheet :BufferedImage= _

  def this(sheet: BufferedImage) {
    this()
    this.sheet = sheet
  }

  def crop(x: Int, y: Int, width: Int, height: Int): BufferedImage = {
    sheet.getSubimage(x, y, width, height)
  }

}
