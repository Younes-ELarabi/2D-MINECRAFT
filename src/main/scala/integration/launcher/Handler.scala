package integration.launcher

import integration.graphics.GameCamera
import integration.input.{KeyManager, MouseManager}
import integration.universe.Universe

class Handler {

  private var game :Game = _
  private var universe :Universe = _

  def this(game: Game) {
    this()
    this.game = game
  }

  def getGameCamera: GameCamera = game.getGameCamera

  def getKeyManager: KeyManager = game.getKeyManager

  def getMouseManager: MouseManager = game.getMouseManager

  def getWidth: Int = game.getWidth

  def getHeight: Int = game.getHeight

  def getGame: Game = game

  def setGame(game: Game): Unit = {
    this.game = game
  }

  def getWorld: Universe = universe

  def setWorld(universe: Universe): Unit = {
    this.universe = universe
  }

}
