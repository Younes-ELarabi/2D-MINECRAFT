package integration.states

import java.awt.Graphics

import integration.graphics.{Assets, ImageLoader}
import integration.launcher.Handler
import integration.ui.{ClickListener, UIImageButton, UIManager}

class MenuState(handler: Handler) extends State(handler) {

  var uiManager :UIManager = new UIManager(handler)
  handler.getMouseManager.setUIManager(uiManager)

  uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.btn_start, new ClickListener() {
    def onClick(): Unit = {
      println("start")
      handler.getMouseManager.setUIManager(null)
      State.setState(handler.getGame.gameState)
    }
  }))

  override def update(): Unit = {
    uiManager.update
    handler.getMouseManager.setUIManager(uiManager)
    State.setState(handler.getGame.menuState)
    // State.setState(handler.getGame.gameState);
  }

  override def render(g: Graphics): Unit = {
    g.drawImage(new ImageLoader().loadImage("./img/Other/background.jpg"),0,0,handler.getGame.getWidth,handler.getGame.getHeight,null)
    uiManager.render(g)
  }
}
