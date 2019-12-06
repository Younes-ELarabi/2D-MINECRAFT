package integration.creatures

import integration.ProceduralMapGeneration.SimplexNoise
import integration.craft.Craft
import integration.items.Item

object Test {

  def main(args: Array[String]): Unit = {
    var craft :Craft = new Craft();
    craft.init
    craft.insertItem(0)
    craft.insertItem(1)

    val item :Item = craft.craftItem
    println(item.getName)
  }
}
