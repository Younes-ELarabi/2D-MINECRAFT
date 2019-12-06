package integration.cubes

import integration.graphics.Assets

class GrassDirtCube (id :Int) extends Cube (Assets.grass_dirt, id) {
  override def isSolid = true
}
