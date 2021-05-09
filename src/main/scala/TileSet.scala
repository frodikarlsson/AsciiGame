package asciirogue
import scala.collection.immutable.Map
class TileSet(val tileSet: Map[String, MapTile]):
  
  def getTileSet = tileSet

  def getTile(key: String): MapTile = 
    val value = tileSet.get(key)
    value match
      case Some(someTile) => someTile
      case None => throw IllegalStateException(s"Tile $key does not exist")

  def getTileFromSymbol(key: String): MapTile = 
    val charTileMap = tileSet.values.map(a=>(a.tile, a)).toMap
    val value = charTileMap.get(key)
    value match
      case Some(someCharTile: MapTile) => someCharTile
      case None => 
        throw IllegalStateException(s"Tile $key of type ${key.getClass} does not exist for tilemap ${charTileMap.toString}")
      
case class Colors(val red_part: Int, val green_part: Int, val blue_part: Int):
  import Colors.*
object Colors:
  def getAnsi(r: Int, g: Int, b: Int): String = s"\u001b[38;2;${r};${g};${b}m"
  val white = Colors(255, 255, 255)
  val blue = Colors(0, 0, 255)
  val red = Colors(255, 0 ,0)
