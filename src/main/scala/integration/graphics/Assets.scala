package integration.graphics

import java.awt.image.BufferedImage

class Assets {}

object Assets {
  private val width = 32
  private val height = 32
  // background
  var background :BufferedImage = _
  // Natural elements
  var dirt: BufferedImage = _
  var grass: BufferedImage = _
  var grass_dirt :BufferedImage = _
  var stone: BufferedImage = _
  var tree: BufferedImage = _
  var rock: BufferedImage = _
  var wood: BufferedImage = _
  var water:BufferedImage = _
  var brick:BufferedImage = _
  var sky  :BufferedImage = _
  var none :BufferedImage = _
  // Player
  var player_down: Array[BufferedImage] = _
  var player_up: Array[BufferedImage] = _
  var player_left: Array[BufferedImage] = _
  var player_right: Array[BufferedImage] = _
  // Male player
  var male_left  :Array[BufferedImage] = _
  var male_right :Array[BufferedImage] = _
  // Zombie
  var zombie_down: Array[BufferedImage] = _
  var zombie_up: Array[BufferedImage] = _
  var zombie_left: Array[BufferedImage] = _
  var zombie_right: Array[BufferedImage] = _
  // Robot
  var robot_left  :Array[BufferedImage] = _
  var robot_right :Array[BufferedImage] = _
  // Different elements
  var btn_start: Array[BufferedImage] = _
  var inventoryScreen: BufferedImage = _
  // Items
  var apple :BufferedImage = _
  var axe_gold :BufferedImage = _
  var shovel_gold :BufferedImage = _
  // Status
  var heart :BufferedImage = _
  // Craft
  var craft :BufferedImage = _

  def init(): Unit = {
    val sheet :SpriteSheet = new SpriteSheet(new ImageLoader().loadImage("./resources/textures/sheet.png"))
    Assets.background = new ImageLoader().loadImage("./img/Other/background.jpg")
    Assets.inventoryScreen = new ImageLoader().loadImage("./resources/textures/inventoryScreen2.png")
    Assets.wood = sheet.crop(Assets.width, Assets.height, Assets.width, Assets.height)
    Assets.btn_start = new Array[BufferedImage](2)
    Assets.btn_start(0) = sheet.crop(Assets.width * 6, Assets.height * 4, Assets.width * 2, Assets.height)
    Assets.btn_start(1) = sheet.crop(Assets.width * 6, Assets.height * 5, Assets.width * 2, Assets.height)
    Assets.player_down = new Array[BufferedImage](2)
    Assets.player_up = new Array[BufferedImage](2)
    Assets.player_left = new Array[BufferedImage](2)
    Assets.player_right = new Array[BufferedImage](2)
    Assets.player_down(0) = sheet.crop(Assets.width * 4, 0, Assets.width, Assets.height)
    Assets.player_down(1) = sheet.crop(Assets.width * 5, 0, Assets.width, Assets.height)
    Assets.player_up(0) = sheet.crop(Assets.width * 6, 0, Assets.width, Assets.height)
    Assets.player_up(1) = sheet.crop(Assets.width * 7, 0, Assets.width, Assets.height)
    Assets.player_right(0) = sheet.crop(Assets.width * 4, Assets.height, Assets.width, Assets.height)
    Assets.player_right(1) = sheet.crop(Assets.width * 5, Assets.height, Assets.width, Assets.height)
    Assets.player_left(0) = sheet.crop(Assets.width * 6, Assets.height, Assets.width, Assets.height)
    Assets.player_left(1) = sheet.crop(Assets.width * 7, Assets.height, Assets.width, Assets.height)
    //
    Assets.male_right = new Array[BufferedImage](2)
    Assets.male_right(0) = new ImageLoader().loadImage("./img/Characters/male-stand-right.png")
    Assets.male_right(1) = new ImageLoader().loadImage("./img/Characters/male-walk-right-01.png")
    //
    Assets.male_left = new Array[BufferedImage](2)
    Assets.male_left(0) = new ImageLoader().loadImage("./img/Characters/male-stand-left.png")
    Assets.male_left(1) = new ImageLoader().loadImage("./img/Characters/male-walk-left-01.png")
    //
    Assets.zombie_down = new Array[BufferedImage](2)
    Assets.zombie_up = new Array[BufferedImage](2)
    Assets.zombie_left = new Array[BufferedImage](2)
    Assets.zombie_right = new Array[BufferedImage](2)
    Assets.zombie_down(0) = sheet.crop(Assets.width * 4, Assets.height * 2, Assets.width, Assets.height)
    Assets.zombie_down(1) = sheet.crop(Assets.width * 5, Assets.height * 2, Assets.width, Assets.height)
    Assets.zombie_up(0) = sheet.crop(Assets.width * 6, Assets.height * 2, Assets.width, Assets.height)
    Assets.zombie_up(1) = sheet.crop(Assets.width * 7, Assets.height * 2, Assets.width, Assets.height)
    //Assets.zombie_right(0) = sheet.crop(Assets.width * 4, Assets.height * 3, Assets.width, Assets.height)
    //Assets.zombie_right(1) = sheet.crop(Assets.width * 5, Assets.height * 3, Assets.width, Assets.height)
    //Assets.zombie_left(0) = sheet.crop(Assets.width * 6, Assets.height * 3, Assets.width, Assets.height)
    //Assets.zombie_left(1) = sheet.crop(Assets.width * 7, Assets.height * 3, Assets.width, Assets.height)
    Assets.zombie_right(0) = new ImageLoader().loadImage("./img/Characters/zombie-stand-right.png")
    Assets.zombie_right(1) = new ImageLoader().loadImage("./img/Characters/zombie-walk-right-02.png")
    Assets.zombie_left(0) = new ImageLoader().loadImage("./img/Characters/zombie-stand-left.png")
    Assets.zombie_left(1) = new ImageLoader().loadImage("./img/Characters/zombie-walk-left-01.png")
    // Robot
    Assets.robot_left =  new Array[BufferedImage](2)
    Assets.robot_right =  new Array[BufferedImage](2)
    Assets.robot_left(0) = new ImageLoader().loadImage("./img/Characters/robot-walk-left-01.png")
    Assets.robot_left(1) = new ImageLoader().loadImage("./img/Characters/robot-walk-left-02.png")
    Assets.robot_right(0) = new ImageLoader().loadImage("./img/Characters/robot-walk-right-01.png")
    Assets.robot_right(1) = new ImageLoader().loadImage("./img/Characters/robot-walk-right-02.png")
    //
    // Assets.dirt = sheet.crop(Assets.width, 0, Assets.width, Assets.height)
    // Assets.grass = sheet.crop(Assets.width * 2, 0, Assets.width, Assets.height)
    Assets.dirt = new ImageLoader().loadImage("./img/Tiles/dirt.png")
    Assets.grass =  new ImageLoader().loadImage("./img/Tiles/grass_top.png")
    Assets.grass_dirt = new ImageLoader().loadImage("./img/Tiles/dirt_grass.png")
    Assets.stone = sheet.crop(Assets.width * 3, 0, Assets.width, Assets.height)
    Assets.tree = sheet.crop(0, 0, Assets.width, Assets.height * 2)
    Assets.rock = sheet.crop(0, Assets.height * 2, Assets.width, Assets.height)
    Assets.water = new ImageLoader().loadImage("./img/water.jpg")
    Assets.brick = new ImageLoader().loadImage("./img/brick.jpg")
    Assets.sky   = new ImageLoader().loadImage("./img/Other/skybox_bottom.png")
    Assets.none  =  Assets.sky
    //
    Assets.apple =  new ImageLoader().loadImage("./img/Items/apple.png")
    Assets.shovel_gold = new ImageLoader().loadImage("./img/Items/shovel_gold.png")
    Assets.axe_gold = new ImageLoader().loadImage("./img/Items/axe_gold.png")
    Assets.heart = new ImageLoader().loadImage("./img/Status/heart.png")
    //
    Assets.craft = new ImageLoader().loadImage("img/craft/craft.jpg")
  }
}