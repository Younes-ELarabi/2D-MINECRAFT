package integration.inventory

import util.control.Breaks._
import java.awt.{Color, Font, Graphics}
import java.awt.event.KeyEvent

import integration.items.Item
import java.util

import integration.craft.CraftBag
import integration.graphics.{Assets, Text}
import integration.launcher.Handler
import integration.states.State
import integration.ui.{ClickListener, UIImageButton, UIManager}


class Inventory{

  private var handler :Handler = _
  private var active :Boolean = false
  private var inventoryItems :util.ArrayList[Item] = _
  private var invX :Int = 64
  private var invY :Int= 48
  private var invWidth :Int= 868 //512
  private var invHeight :Int= 384
  private var invListCenterX :Int= invX + 171
  private var invListCenterY :Int= invY + invHeight / 2 + 5
  private var invListSpacing :Int= 30
  private var invImageX :Int= 452
  private var invImageY :Int= 82
  private var invImageWidth :Int= 64
  private var invImageHeight :Int= 64
  private var invCountX :Int= 484
  private var invCountY :Int= 172
  private var selectedItem :Int= 0

  var uiManager :UIManager = _

  private var craftTool :CraftBag = _

  def this(handler: Handler) {
    this()
    this.handler = handler
    //
    uiManager = new UIManager(handler)
    handler.getMouseManager.setUIManager(uiManager)
    uiManager.addObject(new UIImageButton(invImageX, invImageY, invImageWidth, invImageHeight, null, new ClickListener() {
      def onClick(): Unit = {
        println(Item.items(selectedItem).getName)
        println("counter :"+craftTool.counter)
        if(craftTool.counter == 0){
          println("counter :"+craftTool.counter)
          craftTool.setItem1(Item.items(selectedItem))
        }else if(craftTool.counter == 1){
          println("counter :"+craftTool.counter)
          craftTool.setItem2(Item.items(selectedItem))
        }
      }
    }))
    //
    this.craftTool = new CraftBag(handler)
    inventoryItems = new util.ArrayList[Item]
    //just for test
    inventoryItems.add(new Item(Assets.wood, "Wood", 0))
    inventoryItems.add(new Item(Assets.rock, "rock", 1))
    inventoryItems.add(new Item(Assets.axe_gold,"axe gold",2))
    inventoryItems.add(new Item(Assets.apple,"delicious apple",3))
    inventoryItems.add(new Item(Assets.shovel_gold,"shovel in gold",4))
  }

  def update(): Unit = {
    if (handler.getKeyManager.keyJustPressed(KeyEvent.VK_E)) {
      active = !active
    }
    if (!active) {
      handler.getMouseManager.setUIManager(null)
      return
    }
    if (handler.getKeyManager.keyJustPressed(KeyEvent.VK_W)) {
      this.selectedItem -= 1;
    }
    if (handler.getKeyManager.keyJustPressed(KeyEvent.VK_X)) {
      this.selectedItem += 1;
    }
    if (selectedItem < 0) selectedItem = inventoryItems.size - 1
    else if (selectedItem >= inventoryItems.size) selectedItem = 0
    uiManager.update
    handler.getMouseManager.setUIManager(uiManager)
    craftTool.craft
    if(craftTool.getResult != null){
      inventoryItems.add(craftTool.getResult)
      craftTool.clearItems
    }
  }

  def render(g: Graphics): Unit = {

    if (!active) return
    g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null)
    val len = inventoryItems.size
    if (len == 0) return
    for(i <- -5 until  6) {
      breakable{
        if(selectedItem + i.asInstanceOf[Int] < 0 || selectedItem + i.asInstanceOf[Int] >= len){
          // break
          break;
        }else{
          if(i == 0){
            Text.drawString(g, "> " + inventoryItems.get(selectedItem + i.asInstanceOf[Int]).getName + " <",
              invListCenterX, invListCenterY + i.asInstanceOf[Int] * invListSpacing, true, Color.YELLOW,
            new Font("TimesRoman", Font.PLAIN, 18));
          }else Text.drawString(g, ""+inventoryItems.get(selectedItem + i.asInstanceOf[Int]).getName,
            invListCenterX, invListCenterY + i.asInstanceOf[Int] * invListSpacing, true, Color.WHITE, new Font("TimesRoman", Font.PLAIN, 18))
        }
      }
      craftTool.render(g)
    }
    val item = inventoryItems.get(selectedItem)
    g.drawImage(item.getTexture, invImageX, invImageY, invImageWidth, invImageHeight, null)
    Text.drawString(g, Integer.toString(item.getCount), invCountX, invCountY, true, Color.WHITE, new Font("TimesRoman", Font.PLAIN, 18))
  }

  def addItem(item: Item): Unit = {
    val size: Int = inventoryItems.size()
    for (i <- 0 until size) {
      if (inventoryItems.get(i).getId == item.getId) {
        inventoryItems.get(i).setCount(inventoryItems.get(i).getCount + item.getCount)
        return
      }
    }
    inventoryItems.add(item)
  }

  // GETTERS SETTERS
  def getHandler: Handler = handler

  def setHandler(handler: Handler): Unit = {
    this.handler = handler
  }

  def isActive: Boolean = active

  def getSelectedItem :Int = this.selectedItem

}
