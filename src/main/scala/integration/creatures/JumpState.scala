package integration.creatures

import integration.cubes.Cube
/*
object JumpState{
  var wantToJump :Boolean = false
}
*/
class JumpState extends MovementState {

  private var creature :Creature = _
  private var maxHeight :Float = 0

  private var jumpForce :Float = 0
  private var traveledDistance :Float = 0
  private var ty :Int = 0

  def this(creature: Creature) {
    this()
    this.creature = creature
    this.maxHeight = 160
    this.ty = 0
    jumpForce = 3.7f
    traveledDistance = 0
  }

  override def nextMovementState(context: MovementContext): Unit = {
    if (this.creature.getyMove < 0 ) { // awaits for user input JumpState.wantToJump &&
      if (canJump) jump
      else {
        //JumpState.wantToJump = false
        this.creature.setyMove(0)
        stopJumping
        context.setMovementState(new FallingState(this.creature))
      }
    }
    else context.setMovementState(new FallingState(this.creature))
  }

  def jump(): Unit = {
    creature.setY(creature.getY - jumpForce)
    traveledDistance = traveledDistance + jumpForce
  }

  def stopJumping(): Unit = {
    creature.setY(ty * Cube.CUBEHEIGHT + Cube.CUBEHEIGHT - creature.getBounds.y)
  }

  def canJump: Boolean = {
    if (!this.creature.checkEntityCollisions(0f, -(jumpForce))) {
      this.ty = (creature.getY - this.jumpForce + creature.getBounds.y).asInstanceOf[Int] / Cube.CUBEHEIGHT
      return (!creature.collisionWithCube((creature.getX + creature.getBounds.x).asInstanceOf[Int] / Cube.CUBEWIDTH, ty) && !creature.collisionWithCube((creature.getX + creature.getBounds.x + creature.getBounds.width).asInstanceOf[Int] / Cube.CUBEWIDTH, ty)) && traveledDistance < maxHeight
    }
    false
  }

  def getCreature: Creature = creature

  def setCreature(creature: Creature): Unit = {
    this.creature = creature
  }

  def getMaxHeight: Float = maxHeight

  def setMaxHeight(maxHeight: Float): Unit = {
    this.maxHeight = maxHeight
  }

}
