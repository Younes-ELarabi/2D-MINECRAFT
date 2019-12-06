package integration.cubes

import integration.graphics.Assets

class DirtCube(id :Int) extends Cube (Assets.dirt, id) {

  override def isSolid = true

}
