package integration.cubes

import integration.graphics.Assets

class GrassCube (id :Int) extends Cube (Assets.grass, id) {
  override def isSolid = true
}
