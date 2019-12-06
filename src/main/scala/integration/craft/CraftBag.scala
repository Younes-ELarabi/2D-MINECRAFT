package integration.craft

import java.awt.Graphics

import integration.items.Item
import integration.launcher.Handler


class CraftBag {

  private var item1 :Item  =  _
  private var item2 :Item  =  _
  private var result :Item =  _
  private var handler: Handler = _
  var counter :Int = 0

  def this(handler :Handler){
    this()
    this.handler = handler
  }

  def setItem1(item :Item) :Unit = {
    this.item1 = item
    counter = counter + 1
  }

  def setItem2(item :Item) :Unit = {
    this.item2 = item
    counter = counter + 1
  }

  def getResult :Item = this.result

  def clearItems :Unit = {
    this.item1  =  null
    this.item2  =  null
    this.result  =  null
    counter = 0
  }

  def craft : Unit = {
    if(item1 != null && item2 != null) {
      this.result = Item.items(0) // for test
      // clearItems
    }
  }

  def render(g :Graphics): Unit ={
    if(item1 != null){
      g.drawImage(item1.getTexture,64  + 500, 48 + 175,64,64,null)
    }
    if(item2 != null){
      g.drawImage(item2.getTexture,64 + 620, 48 + 175,64,64,null)
    }
    if(result != null){
      g.drawImage(result.getTexture,64 + 780, 48 + 175,64,64,null)
    }
  }

}
