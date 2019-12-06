package integration.graphics

import java.awt.image.BufferedImage
import java.io.{File, IOException}

import javax.imageio.ImageIO

class ImageLoader {

  def loadImage(path: String): BufferedImage = {
    try return ImageIO.read(new File(path))
    catch {
      case e: IOException =>
        e.printStackTrace()
        System.exit(1)
    }
    null
  }

}
