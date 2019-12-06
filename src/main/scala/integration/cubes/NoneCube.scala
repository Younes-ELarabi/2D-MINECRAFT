package integration.cubes

import integration.graphics.Assets

class NoneCube(id :Int) extends Cube (Assets.none, id) {

  override def isSolid = false

}

