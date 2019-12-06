package integration.graphics
import java.awt.image.BufferedImage

class Animation {
  private var speed :Int= 0
  private var index :Int = 0
  private var lastTime :Long = 0L
  private var timer :Long = 0L
  private var frames :Array[BufferedImage] = _

  def this(speed: Int, frames: Array[BufferedImage]) {
    this()
      this.speed = speed
      this.frames = frames
      index = 0
      timer = 0
      lastTime = System.currentTimeMillis
  }

  def update(): Unit = {
    timer += System.currentTimeMillis - lastTime
    lastTime = System.currentTimeMillis
    if (timer > speed) {
      index += 1
      timer = 0
      if (index >= frames.length) index = 0
    }
  }

  def getCurrentFrame: BufferedImage = frames(index)

}
