package integration.creatures

class MovementContext {

  var state: MovementState = _

  def this(creature: Creature) {
    this()
    this.state = new FallingState(creature)
  }

  def nextMovementState(): Unit = {
    this.state.nextMovementState(this)
  }

  def setMovementState(state: MovementState): Unit = {
    this.state = state
  }

}
