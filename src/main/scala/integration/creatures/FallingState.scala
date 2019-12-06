package integration.creatures
import integration.cubes.Cube

class FallingState extends MovementState {

  private var creature :Creature = _
  private var ty :Int = 0
  private val gravity :Int = 5

  def this(creature: Creature) {
    this()
    this.creature = creature
  }

  override def nextMovementState(context: MovementContext): Unit = {
    if (canFall) fall
    else {
      setMaxPossiblePosition
      context.setMovementState(new JumpState(this.creature))
    }

    def canFall :Boolean =
    {
      if (!this.creature.checkEntityCollisions(0f, 1)) {
        ty = (creature.getY + gravity + creature.getBounds.y + creature.getBounds.height).asInstanceOf[Int] / Cube.CUBEHEIGHT
        if (!creature.collisionWithCube((creature.getX + creature.getBounds.x).asInstanceOf[Int] / Cube.CUBEWIDTH, ty) && !creature.collisionWithCube((creature.getX + creature.getBounds.x + creature.getBounds.width).asInstanceOf[Int] / Cube.CUBEWIDTH, ty)) return true
      }
      false
    }
  }

  def fall(): Unit = {
    creature.setY(creature.getY + gravity)
  }

  def setMaxPossiblePosition(): Unit = {
    creature.setY(ty * Cube.CUBEHEIGHT - creature.getBounds.y - creature.getBounds.height - 1)
  }

}
