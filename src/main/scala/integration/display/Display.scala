package integration.display

import java.awt.{Canvas, Dimension}

import javax.swing.{JButton, JFrame, JPanel}

class Display {
  private var frame :JFrame = _
  private var canvas:Canvas = _

  private var title :String = _
  private var width :Int = 0
  private var height:Int= 0

  def this(title: String, width: Int, height: Int) {
    this()
    this.title = title
    this.width = width
    this.height = height
    createDisplay()
  }

  private def createDisplay(): Unit = {
    frame = new JFrame(title)
    frame.setSize(width, height)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setResizable(false)
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)
    canvas = new Canvas()
    canvas.setPreferredSize(new Dimension(width, height))
    canvas.setMaximumSize(new Dimension(width, height))
    canvas.setMinimumSize(new Dimension(width, height))
    canvas.setFocusable(false)
    frame.add(canvas)
    frame.pack()
  }

  def getCanvas: Canvas = canvas

  def getFrame: JFrame = frame

}