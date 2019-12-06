package integration.craft

import java.awt.Graphics
import java.awt.event.KeyEvent

import integration.graphics.Assets
import integration.items.Item
import integration.launcher.Handler

class Craft {

  private val row :Int = 1;
  private val col :Int = 2;
  private var active :Boolean = false
  private var handler :Handler = _
  var materials :Array[Array[Int]] = Array.ofDim[Int](row,col)
  // Display
  private val craftX :Int = 192
  private val craftY :Int = 48

  def this(handler :Handler){
    this()
    this.handler = handler
    init
    // for test
    insertItem(0)
    insertItem(1)
  }

  def init : Unit = {
    for (i <- 0 until row) {
      for (j <- 0 until col) {
        materials(i)(j) = -1
      }
    }
  }

  def insertItem(id :Int): Unit ={
    val (i,j) = returnEmptyPlace
    if( i != -1 && j != -1 ) materials(i)(j) = id
  }

  def returnEmptyPlace :(Int,Int) = {
    for (i <- 0 until row) {
      for (j <- 0 until col) {
        if (materials(i)(j) == -1) {
          return (i, j)
        }
      }
    }
    (-1,-1)
  }

  def isReady :Boolean = {
    for (i <- 0 until row) {
      for (j <- 0 until col) {
        if (materials(i)(j) == -1) {
          return false
        }
      }
    }
    true
  }

  def craftItem :Item = {
    if(isReady){
      var res :Int = 0
      for (i <- 0 until row) {
        for (j <- 0 until col) {
          res += materials(i)(j)
        }
      }
      return Item.items(res)
    }
    null
  }

  def render(g :Graphics): Unit = {
    update
    if(active){
      g.drawImage(Assets.craft,craftX,craftY,526,360,null)
      g.drawImage(Assets.axe_gold,craftX + 21 ,craftY + 24,95,95,null) // material 1
      g.drawImage(Assets.apple,craftX + 21 ,craftY + 240,95,95,null) // material 2
      if(craftItem != null){
        g.drawImage(craftItem.getTexture,craftX + 351 ,craftY + 104,150,148,null)
      }
    }
  }

  def update :Unit = {
    if (handler.getKeyManager.keyJustPressed(KeyEvent.VK_C)) {
      active = !active
    }
  }

}
