package integration.launcher

import java.awt.{Color, Graphics}
import java.awt.image.BufferStrategy
import java.net.URL

import integration.display.Display
import integration.graphics.{Assets, GameCamera}
import integration.input.{KeyManager, MouseManager}
import integration.states.{GameState, MenuState, State}
import javax.sound.sampled.AudioSystem

class Game extends Runnable{

  private var display: Display = _
  private var width  :Int = 0
  private var height :Int= 0
  var title: String = _

  private var running: Boolean = false
  private var thread: Thread = _

  private var bs : BufferStrategy = _
  private var g  : Graphics = _

  //camera
  private var gameCamera :GameCamera = _

  //States
  var gameState :State = _
  var menuState :State = _

  //Input
  private var keyManager :KeyManager = _
  private var mouseManager :MouseManager = _

  //handler
  private var handler :Handler = _

  // audio
  val url = new URL("File:./audio/100.wav")
  val audioIn = AudioSystem.getAudioInputStream(url)
  val clip = AudioSystem.getClip

  def this(title: String, width: Int, height: Int) {
    this()
    this.width = width
    this.height = height
    this.title = title
    this.keyManager = new KeyManager()
    this.mouseManager = new MouseManager()
  }

  private def init(): Unit = {
    // audio
    // clip.open(audioIn)
    // clip.start
    // init assets
    Assets.init();
    //set display
    this.display = new Display(title,width,height)
    display.getFrame.addKeyListener(keyManager)
    display.getFrame.addMouseListener(mouseManager)
    display.getFrame.addMouseMotionListener(mouseManager)
    display.getCanvas.addMouseListener(mouseManager)
    display.getCanvas.addMouseMotionListener(mouseManager)
    //handler
    handler = new Handler(this)
    gameCamera = new GameCamera(handler, 0, 0)
    //states
    gameState = new GameState(handler)
    menuState = new MenuState(handler)
    State.setState(menuState)
  }

  private def update(): Unit = {
    keyManager.update();
    //temporary code
    if(State.getState != null)
      State.getState.update()
    if(!clip.isRunning){
      clip.start()
    }
  }

  private def render(): Unit = {
    bs = display.getCanvas.getBufferStrategy() // to prevent lag
    if (bs == null) {
      display.getCanvas.createBufferStrategy(3)
      return
    }
    g = bs.getDrawGraphics()
    //Clear before drawing again
    g.clearRect(0, 0, this.width, this.height)
    //Draw something
    if(State.getState != null)
      State.getState.render(g);
    //End Drawing !
    bs.show
    g.dispose
  }

  override def run(): Unit = {
    init

    val fps :Int = 60
    val timePerTick :Double = 1000000000 / fps
    var delta :Double = 0
    var now :Long = 0L
    var lastTime :Long= System.nanoTime
    var timer :Long= 0
    var ticks :Int= 0

    while ( {running}) {
      now = System.nanoTime
      delta += (now - lastTime) / timePerTick
      timer += now - lastTime
      lastTime = now
      if (delta >= 1) {
        update
        render
        ticks += 1
        delta -= 1
      }
      if (timer >= 1000000000) {
        System.out.println("FPS :" + ticks);
        ticks = 0
        timer = 0
      }
    }
    stop()
  }

  def start(): Unit = synchronized {
    if (running) {
      return
    }
    running = true
    thread = new Thread(this)
    thread.start()
  }

  def stop(): Unit = synchronized {
    if (!running) return
    running = false
    try thread.join
    catch {
      case e: InterruptedException =>
        e.printStackTrace()
    }
  }

  def getKeyManager: KeyManager = keyManager

  def getMouseManager: MouseManager = mouseManager

  def getGameCamera: GameCamera = gameCamera

  def getWidth: Int = width

  def getHeight: Int = height

}
