package integration.states

import java.awt.Graphics

import integration.launcher.Handler

object State {
  private var currentState :State = _

  def setState(state: State): Unit = {
    currentState = state
  }

  def getState: State = currentState
}

abstract class State(var handler: Handler) {
  def update(): Unit

  def render(g: Graphics): Unit
}
