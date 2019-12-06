package integration.cubes

import java.awt.Graphics
import java.awt.image.BufferedImage

class Cube {

  protected var texture: BufferedImage = _
  protected var id: Int = _

  def this(texture: BufferedImage, id: Int) {
    this()
    this.texture = texture
    this.id = id
    Cube.cubes(id) = this
  }

  def update(): Unit = {}

  def render(g: Graphics, x: Int, y: Int): Unit = {
    g.drawImage(texture, x, y, Cube.CUBEWIDTH, Cube.CUBEHEIGHT, null)
  }

  def isSolid: Boolean = false

  def getId: Int = id
}

object Cube {

  var cubes :Array[Cube] = new Array[Cube](256)
  var grassCube :Cube = new GrassCube(0)
  var dirtCube :Cube= new DirtCube(1)
  var rockCube :Cube= new RockCube(2)
  var waterCube :Cube = new WaterCube(3)
  var brickCube :Cube = new BrickCube(4)
  var skyCube :Cube = new SkyCube(5)
  var grassDirtCube :Cube = new GrassDirtCube(6)
  var noneCube :Cube = new NoneCube((7))

  val CUBEWIDTH :Int = 64
  val CUBEHEIGHT:Int = 64
}
