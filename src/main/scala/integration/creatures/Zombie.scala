package integration.creatures

import java.awt.{Graphics, Rectangle}
import java.awt.image.BufferedImage

import integration.graphics.{Animation, Assets}
import integration.launcher.Handler

import scala.util.Random
import scala.util.control.Breaks.{break, breakable}

class Zombie(handler: Handler,x: Float,y: Float) extends Creature (handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT) {

  private var lastAttackTimer :Long = 0L
  private val attackCooldown  :Long = 800
  private var attackTimer = attackCooldown

  bounds.x = 22
  bounds.y = 8
  bounds.width = 22
  bounds.height = 55

  var animLeft  = new Animation(500, Assets.zombie_left)
  var animRight = new Animation(500, Assets.zombie_right)

  context = new MovementContext(this)

  var now = System.nanoTime
  var timeElapsed = System.nanoTime - now

  def computeTime :Unit = {
    timeElapsed = System.nanoTime - now
  }

  def getInput(): Unit = {
    if( timeElapsed > 1000000000 ){
      val r = new Random()
      r.setSeed(System.nanoTime())
      xMove = r.nextInt(3) -1;
      yMove = r.nextInt(2) -1;
      now = System.nanoTime
    }
  }

  override def render(g: Graphics): Unit = {
    g.drawImage(getCurrentAnimationFrame, (getX - handler.getGameCamera.getxOffset).asInstanceOf[Int], (getY - handler.getGameCamera.getyOffset).asInstanceOf[Int], width, height, null)
  }

  def getCurrentAnimationFrame: BufferedImage = {
    if (xMove < 0) animLeft.getCurrentFrame
    else if (xMove > 0) animRight.getCurrentFrame
    else if (yMove < 0) animRight.getCurrentFrame
    else animRight.getCurrentFrame
  }

  override def update(): Unit = {
    //Animations/
    animRight.update()
    animLeft.update()
    //Movement
    getInput()
    move()
    checkAttacks()
    computeTime
  }

  override def die(): Unit = {

  }

  def checkAttacks(): Unit = {
    attackTimer += System.currentTimeMillis - lastAttackTimer
    lastAttackTimer = System.currentTimeMillis
    if (attackTimer < attackCooldown) return

    val cb: Rectangle = getCollisionBounds(0, 0)
    val ar: Rectangle = new Rectangle()
    val arSize: Int = 20
    ar.width = arSize
    ar.height = arSize
    val r :Random = new Random(2)
    r.setSeed(System.nanoTime())
    if(r.nextInt() == 0){
      // attack left
      ar.x = cb.x - arSize
      ar.y = cb.y + cb.height / 2 - arSize / 2
    }else{
      // attack right
      ar.x = cb.x - arSize
      ar.y = cb.y + cb.height / 2 - arSize / 2
    }

    attackTimer = 0
    val entitiesSize :Int = handler.getWorld.getEntityManager.getEntities.size()
    for (i <- 0 until entitiesSize) {
      breakable{
        if( handler.getWorld.getEntityManager.getEntities.get(i) equals this){
          break;
        }
        if( handler.getWorld.getEntityManager.getEntities.get(i).getCollisionBounds(0,0).intersects(ar)){
          handler.getWorld.getEntityManager.getEntities.get(i).hurt(1);
          return
        }
      }
    }
  }

}
