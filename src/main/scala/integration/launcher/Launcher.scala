package integration.launcher

object Launcher {

  def main(args: Array[String]): Unit = {
    new Game("2D-MINECRAFT-CLONE",600*2,480*2).start()
  }

}