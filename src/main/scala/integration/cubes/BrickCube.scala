package integration.cubes

import integration.graphics.Assets

class BrickCube(id :Int) extends Cube (Assets.brick, id) {

  override def isSolid: Boolean = true

}