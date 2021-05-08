package asciirogue
import scala.collection.immutable.Map
class TileSet(val tileSet: Map[String, MapTile]):
  
  def getTileSet = tileSet

  def getTile(key: String): MapTile = 
    val value = tileSet.get(key)
    value match
      case Some(someChar) => someChar
      case None => throw IllegalStateException(s"Tile $key does not exist")

object Colors:
  def getAnsi(r: Int, g: Int, b: Int): String = s"\u001b[38;2;${r};${g};${b}m"
  val white = getAnsi(255, 255, 255)
  val blue = getAnsi(0, 0, 255)
  val red = getAnsi(255, 0 ,0)
