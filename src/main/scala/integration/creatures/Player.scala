package integration.creatures

import java.awt.event.KeyEvent

import util.control.Breaks._
import java.awt.{Graphics, Rectangle}
import java.awt.image.BufferedImage

import integration.craft.CraftBag
import integration.cubes.Cube
import integration.graphics.{Animation, Assets}
import integration.inventory.Inventory
import integration.launcher.Handler

class Player(handler: Handler,x: Float,y: Float) extends Creature(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT) {

  bounds.x = 22
  bounds.y = 8
  bounds.width = 22
  bounds.height = 55

  //Animatons
  var animDown  :Animation = new Animation(500, Assets.player_down)
  var animUp    :Animation = new Animation(500, Assets.player_up)
  //var animLeft  :Animation = new Animation(500, Assets.player_left)
  //var animRight :Animation = new Animation(500, Assets.player_right)
  var animLeft  :Animation = new Animation(500, Assets.male_left)
  var animRight :Animation = new Animation(500, Assets.male_right)

  // Attack timer
  private var lastAttackTimer :Long = 0L
  private val attackCooldown  :Long = 800
  private var attackTimer = attackCooldown
  // Inventory
  private var inventory :Inventory = new Inventory(this.handler)

  //
  context = new MovementContext(this)

  override def update(): Unit = {
    //Animations/
    animDown.update()
    animUp.update()
    animRight.update()
    animLeft.update()
    //Movement
    getInput()
    move()
    handler.getGameCamera.centerOnEntity(this)
    // Attack
    checkAttacks()
    destroyCube()
    // Inventory
    inventory.update()
  }

  def getInput(): Unit = {
    xMove = 0
    yMove = 0
    if (inventory.isActive) return
    if (handler.getKeyManager.up) {
      yMove = -speed
    }
    if (handler.getKeyManager.down) yMove = speed
    if (handler.getKeyManager.left) xMove = -speed
    if (handler.getKeyManager.right) xMove = speed
  }

  override def render(g: Graphics): Unit = {
    g.drawImage(getCurrentAnimationFrame, (getX - handler.getGameCamera.getxOffset).asInstanceOf[Int], (getY - handler.getGameCamera.getyOffset).asInstanceOf[Int], width, height, null)
    HealthComponent.render(g)
  }

  def postRender(g: Graphics): Unit = {
    inventory.render(g)
  }


  def getCurrentAnimationFrame: BufferedImage = {
    if (xMove < 0) animLeft.getCurrentFrame
    else if (xMove > 0) animRight.getCurrentFrame
    else if (yMove < 0) animRight.getCurrentFrame
    else animRight.getCurrentFrame
  }

  override def die(): Unit = {
    System.out.println("You lose")
  }

  def checkAttacks(): Unit = {
    attackTimer += System.currentTimeMillis - lastAttackTimer
    lastAttackTimer = System.currentTimeMillis
    if (attackTimer < attackCooldown) return

    if (inventory.isActive) return

    val cb: Rectangle = getCollisionBounds(0, 0)
    val ar: Rectangle = new Rectangle()
    val arSize: Int = 20
    ar.width = arSize
    ar.height = arSize

    if (handler.getKeyManager.aUp) {
      ar.x = cb.x + cb.width / 2 - arSize / 2
      ar.y = cb.y - arSize
    }
    else if (handler.getKeyManager.aDown) {
      ar.x = cb.x + cb.width / 2 - arSize / 2
      ar.y = cb.y + cb.height
    }
    else if (handler.getKeyManager.aLeft) {
      ar.x = cb.x - arSize
      ar.y = cb.y + cb.height / 2 - arSize / 2
    }
    else if (handler.getKeyManager.aRight) {
      ar.x = cb.x + cb.width
      ar.y = cb.y + cb.height / 2 - arSize / 2
    }
    else return
    attackTimer = 0
    val entitiesSize :Int = handler.getWorld.getEntityManager.getEntities.size()
    for (i <- 0 until entitiesSize) {
      breakable{
        if( handler.getWorld.getEntityManager.getEntities.get(i) equals this){
          break;
        }
        if( handler.getWorld.getEntityManager.getEntities.get(i).getCollisionBounds(0,0).intersects(ar)){
          handler.getWorld.getEntityManager.getEntities.get(i).hurt(3);
          return
        }
      }
    }
  }

  def destroyCube() :Unit = {
    if (handler.getKeyManager.aDown) {
      val cubeToDestroy_PosX = (getX / Cube.CUBEWIDTH).asInstanceOf[Int];
      val cubeToDestroy_PosY = ( (getY / Cube.CUBEHEIGHT) + 1).asInstanceOf[Int];
      if(cubeToDestroy_PosX < handler.getWorld.getWidth && cubeToDestroy_PosY < handler.getWorld.getHeight) {
        if(handler.getWorld.getValue(cubeToDestroy_PosX,cubeToDestroy_PosY) != 3){ // sorry you can't destroy waterddqq
          handler.getWorld.setInMap(cubeToDestroy_PosX, cubeToDestroy_PosY, 7);
        }
      }
    }
    if (handler.getKeyManager.aUp) {
      val cubeToDestroy_PosX = (getX / Cube.CUBEWIDTH).asInstanceOf[Int];
      val cubeToDestroy_PosY = ( (getY / Cube.CUBEHEIGHT) - 1).asInstanceOf[Int];
      if(cubeToDestroy_PosX < handler.getWorld.getWidth && cubeToDestroy_PosY < handler.getWorld.getHeight) {
        if(handler.getWorld.getValue(cubeToDestroy_PosX,cubeToDestroy_PosY) != 3){
          handler.getWorld.setInMap(cubeToDestroy_PosX, cubeToDestroy_PosY, 7);
        }
      }
    }
    if (handler.getKeyManager.aRight) {
      val cubeToDestroy_PosX = ( (getX / Cube.CUBEWIDTH) + 1).asInstanceOf[Int];
      val cubeToDestroy_PosY = (getY / Cube.CUBEHEIGHT).asInstanceOf[Int];
      if(cubeToDestroy_PosX < handler.getWorld.getWidth && cubeToDestroy_PosY < handler.getWorld.getHeight) {
        if(handler.getWorld.getValue(cubeToDestroy_PosX,cubeToDestroy_PosY) != 3){
          handler.getWorld.setInMap(cubeToDestroy_PosX, cubeToDestroy_PosY, 7);
        }
      }
    }
    if (handler.getKeyManager.aLeft) {
      val cubeToDestroy_PosX = ( (getX /Cube.CUBEWIDTH) -1).asInstanceOf[Int];
      val cubeToDestroy_PosY = (getY / Cube.CUBEHEIGHT).asInstanceOf[Int];
      if(cubeToDestroy_PosX < handler.getWorld.getWidth && cubeToDestroy_PosY < handler.getWorld.getHeight) {
        if(handler.getWorld.getValue(cubeToDestroy_PosX,cubeToDestroy_PosY) != 3){
          handler.getWorld.setInMap(cubeToDestroy_PosX, cubeToDestroy_PosY, 7);
        }
      }
    }
  }

  def getInventory = this.inventory

  def setInventory(inventory: Inventory): Unit = {
    this.inventory = inventory
  }

  def setAnimationLeft(animation :Animation):Unit = {
    this.animLeft = animation;
  }

  def setAnimationRight(animation :Animation):Unit = {
    this.animRight = animation;
  }

  private object HealthComponent {

    private var isActive :Boolean = false;

    def render(g :Graphics) :Unit = {
      update()
      if (isActive) {
        for(h<- 0 until getHealth){
          g.drawImage(Assets.heart,10 + (getX - handler.getGameCamera.getxOffset).asInstanceOf[Int] + h*13,(getY - handler.getGameCamera.getyOffset).asInstanceOf[Int] - 20, 12, 12, null)
        }
      }
    }

    def update() : Unit = {
      if (handler.getKeyManager.keyJustPressed(KeyEvent.VK_A)) {
        this.isActive = !isActive
      }
    }

  }

}