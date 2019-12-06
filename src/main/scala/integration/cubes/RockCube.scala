package integration.cubes

import integration.graphics.Assets

class RockCube(id :Int) extends Cube (Assets.stone, id) {

  override def isSolid = true

}
