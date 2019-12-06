package integration.utils
import scala.io.Source

object Utils {
  def loadFileAsString(path: String): String = {
    var builder :StringBuilder = new StringBuilder
    var bufferedSource = Source.fromFile(path)
    for (line <- bufferedSource.getLines) {
      builder.append(line + "\n")
      //println(line)
    }
    bufferedSource.close
    builder.toString
  }

  def parseInt(number: String): Int = {
    //println(number)
    try number.toInt
    catch {
      case e: NumberFormatException =>
        e.printStackTrace()
        0
    }
  }
}