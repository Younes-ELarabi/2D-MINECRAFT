package integration.input

import java.awt.event.{KeyEvent, KeyListener}

class KeyManager extends KeyListener{

  private var keys :Array[Boolean]= new Array[Boolean](256)
  private var justPressed :Array[Boolean]= new Array[Boolean](keys.length)
  private var cantPress :Array[Boolean]= new Array[Boolean](keys.length)

  var up :Boolean    = false
  var down :Boolean  = false
  var left :Boolean  = false
  var right :Boolean = false
  var aUp :Boolean   = false
  var aDown :Boolean = false
  var aLeft :Boolean = false
  var aRight:Boolean = false

  def update(): Unit = {
    val size: Int = keys.length;
    for(i <- 0 until size){
      if (cantPress(i) && !keys(i)) cantPress(i) = false
      else if (justPressed(i)) {
        cantPress(i) = true
        justPressed(i) = false
      }
      if (!cantPress(i) && keys(i)) justPressed(i) = true
    }
    up = keys(KeyEvent.VK_Z)
    down = keys(KeyEvent.VK_S)
    left = keys(KeyEvent.VK_Q)
    right = keys(KeyEvent.VK_D)
    aUp = keys(KeyEvent.VK_UP)
    aDown = keys(KeyEvent.VK_DOWN)
    aLeft = keys(KeyEvent.VK_LEFT)
    aRight = keys(KeyEvent.VK_RIGHT)
  }

  def keyJustPressed(keyCode: Int): Boolean = {
    if (keyCode < 0 || keyCode >= keys.length) return false
    justPressed(keyCode)
  }

  def keyPressed(e: KeyEvent): Unit = {
    if (e.getKeyCode < 0 || e.getKeyCode >= keys.length) return
    keys(e.getKeyCode) = true
  }

  def keyReleased(e: KeyEvent): Unit = {
    if (e.getKeyCode < 0 || e.getKeyCode >= keys.length) return
    keys(e.getKeyCode) = false
  }

  def keyTyped(e: KeyEvent): Unit = {}
}