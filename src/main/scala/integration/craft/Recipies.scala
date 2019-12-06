package integration.craft

object Recipies {

  val recipes :Array[Recipe] = new Array[Recipe](1)
  recipes(0) = new Recipe(0,1,1)

  def contains(recipe: Recipe): Boolean = {
    for(i <- 0 until recipes.size){
      if(recipes(i) equals recipe){
        return true
      }
    }
    false
  }

  class Recipe(item1 :Int ,item2 :Int,itemResult:Int){
    val itm1 :Int =  item1
    val itm2 :Int = item2
    val craftedItem  :Int = itemResult
  }

}
