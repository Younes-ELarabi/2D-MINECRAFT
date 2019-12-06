package integration.states

import java.awt.Graphics
import integration.launcher.Handler
import integration.universe.Universe

class GameState(handler: Handler) extends State(handler) {

  var world :Universe = new Universe(handler, "./resources/worlds/world1.txt")
  handler.setWorld(world)

  def update(): Unit = {
    world.update
  }

  def render(g: Graphics): Unit = {
    world.render(g)
  }

}
