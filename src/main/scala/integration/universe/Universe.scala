package integration.universe

import java.awt.Graphics

import integration.ProceduralMapGeneration.SimplexNoise
import integration.creatures.{Player, Robot, Zombie}
import integration.cubes.Cube
import integration.entities.EntityManager
import integration.entities.statics.{Rock, Tree}
import integration.items.ItemManager
import integration.launcher.Handler
import integration.utils.Utils

class Universe {

  private var handler :Handler= _
  private var width :Int = 0
  private var height :Int= 0
  private var spawnX :Int = 0
  private var spawnY :Int = 0
  private var map  :Array[Array[Int]] = Array.ofDim[Int](width,height)

  //Entities
  private var entityManager :EntityManager= _
  //Managers
  private var itemManager :ItemManager= _

  def this(handler: Handler, path: String) {
    this()
    this.handler = handler
    entityManager = new EntityManager(this.handler, new Player(handler, 390, 120))
    itemManager = new ItemManager(this.handler)
    //populate the world with entities
    //entityManager.addEntity(new Tree(handler, 132, 250))
    entityManager.addEntity(new Zombie(handler,1270,120))
    entityManager.addEntity(new Robot(handler,1300,120))
    entityManager.addEntity(new Rock(handler, 132, 64*3))
    entityManager.addEntity(new Tree(handler, 132+64, 64*2))
    entityManager.addEntity(new Tree(handler, 132+64*3, 64*2))
    entityManager.addEntity(new Tree(handler, 132+64*4, 64*2))
    //then load the world
    loadWorld(path)
    entityManager.getPlayer.setX(spawnX)
    entityManager.getPlayer.setY(spawnY)
  }

  def update: Unit = {
    itemManager.update
    entityManager.update
  }

  def render(g: Graphics): Unit = {
    val xStart: Int = Math.max(0, handler.getGameCamera.getxOffset / Cube.CUBEWIDTH).asInstanceOf[Int]
    val xEnd: Int = Math.min(width, (handler.getGameCamera.getxOffset + handler.getWidth) / Cube.CUBEWIDTH + 1).asInstanceOf[Int]
    val yStart: Int = Math.max(0, handler.getGameCamera.getyOffset / Cube.CUBEHEIGHT).asInstanceOf[Int]
    val yEnd: Int = Math.min(height, (handler.getGameCamera.getyOffset + handler.getHeight) / Cube.CUBEHEIGHT + 1).asInstanceOf[Int]
    //
   for(y <- yStart to yEnd){
     for(x <- xStart to xEnd){
       getCube(x, y).render(g, (x * Cube.CUBEWIDTH - handler.getGameCamera.getxOffset).
         asInstanceOf[Int], (y * Cube.CUBEHEIGHT - handler.getGameCamera.getyOffset).asInstanceOf[Int])
     }
   }
    // Items
    itemManager.render(g)
    //Entities
    entityManager.render(g)
    //entityManager.getPlayer.render(g)
  }

  def getCube(x: Int, y: Int): Cube = {
    if (x < 0 || y < 0 || x >= width || y >= height) return Cube.grassCube
    val t = Cube.cubes(this.map(x)(y))
    if (t == null) return Cube.dirtCube
    t
  }

  private def loadWorld(path: String): Unit = {
    val file :String = Utils.loadFileAsString(path)
    val tokens :Array[String] = file.split("\\s+")
    this.width = Utils.parseInt(tokens(0))
    this.height= Utils.parseInt(tokens(1))
    this.spawnX= Utils.parseInt(tokens(2))
    this.spawnY= Utils.parseInt(tokens(3))
    map = Array.ofDim[Int](width,height)

    for(y <- 0 until 4) {
      for (x <- 0 until width) {
        map(x)(y) = 5;
      }
    }

    for (x <- 0 until width) {
      map(x)(4) = 6;
    }

    for(y <- 5 until height){
      for(x <- 0 until width){
        var noise :Double = SimplexNoise.noise(x,y)
        var res :Int = 0
        if(noise < 0.1) res = 1;
        else if (noise < 0.2 ) res = 1 // Dirt
        else if (noise < 0.3 ) res = 4 // rock
        else if (noise < 0.5 ) res = 0 // grass
        else if (noise < 0.7 ) res = 4 // brick
        else if (noise < 0.9 ) res = 3 // water
        // map(x)(y) = Utils.parseInt(tokens( (x + y * width) + 4) )
        map(x)(y) = res;
      }
    }

  }

  def getWidth: Int = width

  def getHeight: Int = height

  def getEntityManager: EntityManager = entityManager

  def getHandler: Handler = handler

  def setHandler(handler: Handler): Unit = {
    this.handler = handler
  }

  def getItemManager: ItemManager = itemManager

  def setItemManager(itemManager: ItemManager): Unit = {
    this.itemManager = itemManager
  }

  def getValue(x :Int,y :Int) :Int = {
    map(x)(y);
  }
  def setInMap(x: Int, y: Int ,newVal :Int) :Unit = {
    this.map(x)(y) = newVal;
  }

}