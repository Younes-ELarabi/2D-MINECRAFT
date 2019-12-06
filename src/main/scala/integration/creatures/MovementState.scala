package integration.creatures

trait MovementState {

  def nextMovementState(context: MovementContext): Unit

}
