package integration.entities

import java.awt.Graphics
import java.util
import java.util.Comparator
import integration.creatures.Player
import integration.launcher.Handler

class EntityManager {

  private var handler :Handler = _
  private var player :Player= _
  private var entities :java.util.ArrayList[Entity] = _
  private val renderSorter = new Comparator[Entity]() {
    override def compare(a: Entity, b: Entity): Int = {
      if (a.getY + a.getHeight < b.getY + b.getHeight){
        return -1
      }
      1
    }
  }

  def this(handler: Handler, player: Player) {
    this()
    this.handler = handler
    this.player = player
    this.entities = new util.ArrayList[Entity]()
    addEntity(player)
  }

  def update(): Unit = {
    val it = entities.iterator
    while ( {it.hasNext}) {
      val e = it.next
      e.update
      if (!e.isActive) it.remove
    }
    entities.sort(renderSorter)
  }

  def render(g: Graphics): Unit = {
    var size :Int = entities.size()
    for (i <- 0 until size ) {
      entities.get(i).render(g)
    }
    player.postRender(g) // to render the inventory
  }

  def addEntity(e: Entity): Unit = {
    entities.add(e)
  }
  //GETTERS SETTERS
  def getHandler: Handler = handler

  def setHandler(handler: Handler): Unit = {
    this.handler = handler
  }

  def getPlayer: Player = player

  def setPlayer(player: Player): Unit = {
    this.player = player
  }

  def getEntities: util.ArrayList[Entity] = entities

  def setEntities(entities: util.ArrayList[Entity]): Unit = {
    this.entities = entities
  }

}
