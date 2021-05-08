package asciirogue
import scala.collection.mutable.HashMap
/**
 * A representation of a map in the game
 * @param tileSet A definition for the ascii to be used for tiles, see TileSet
 */ 
class Map(tileSet: TileSet, map: Array[Array[String]]):
  import Map.*
  def this(tileSet: TileSet) = 
    this(tileSet, Map.standardMap)
  
  def this(map: Array[Array[String]]) =
    this(new TileSet(Map.standardTiles), map)
  
  def updated(x: Int, y: Int, obj: String) = map.updated(y, map(y).updated(x, obj))

  override def toString: String = 
    val sb: StringBuilder = new StringBuilder()
    map.foreach(y =>
        y.foreach(x =>
            sb.append(tileSet.getTile(x))
            )
          sb.append("\n")
          )
    sb.toString

  def getMap = map
    
  def getTile(key: String) = tileSet.getTile(key)

  def withOnMap(x: Int, y: Int, obj: String): Map =
    new Map(
      tileSet, 
      updated(x, y, obj)
    )

object Map:
  val standardTiles = HashMap(
    "ground" -> s"${Colors.white}5", 
    "wall" -> s"${Colors.blue}#",
    "player" -> s"${Colors.red}O"
  )
  val standardMap = 
    Array(
      Array.fill[String](50)("wall"),
      "wall" +: Array.fill[String](48)("ground") :+ "wall",
      "wall" +: Array.fill[String](48)("ground") :+ "wall",
      "wall" +: Array.fill[String](48)("ground") :+ "wall",
      "wall" +: Array.fill[String](48)("ground") :+ "wall",
      "wall" +: Array.fill[String](48)("ground") :+ "wall",
      "wall" +: Array.fill[String](48)("ground") :+ "wall",
      Array.fill[String](50)("wall")
    )

