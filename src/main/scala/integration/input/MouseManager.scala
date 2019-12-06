package integration.input

import java.awt.event.{MouseEvent, MouseListener, MouseMotionListener}
import integration.ui.UIManager

class MouseManager extends MouseListener with MouseMotionListener {

  private var leftPressed :Boolean = false
  private var rightPressed :Boolean= false
  private var mouseX = 0
  private var mouseY = 0
  private var uiManager :UIManager = _

  def setUIManager(uiManager: UIManager): Unit = {
    this.uiManager = uiManager
  }

  // Getters
  def isLeftPressed: Boolean = leftPressed

  def isRightPressed: Boolean = rightPressed

  def getMouseX: Int = mouseX

  def getMouseY: Int = mouseY

  override def mouseClicked(e: MouseEvent): Unit = {
    mouseX = e.getX / 64
    mouseY = e.getY / 64
    if (uiManager != null) uiManager.onMouseMove(e)
  }

  override def mousePressed(e: MouseEvent): Unit = {
    if (e.getButton == MouseEvent.BUTTON1) leftPressed = true
    else if (e.getButton == MouseEvent.BUTTON3) rightPressed = true
  }

  override def mouseReleased(e: MouseEvent): Unit = {
    if (e.getButton == MouseEvent.BUTTON1) leftPressed = false
    else if (e.getButton == MouseEvent.BUTTON3) rightPressed = false

    if (uiManager != null) uiManager.onMouseRelease(e)
  }

  override def mouseEntered(e: MouseEvent): Unit = {

  }

  override def mouseExited(e: MouseEvent): Unit = {

  }

  override def mouseDragged(e: MouseEvent): Unit = {

  }

  override def mouseMoved(e: MouseEvent): Unit = {

  }
  
}