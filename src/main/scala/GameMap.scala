package asciirogue
import scala.collection.immutable.Map
/**
 * A representation of a map in the game
 * @param tileSet A definition for the ascii to be used for tiles, see TileSet
 */ 
class GameMap(val tileSet: TileSet, map: Array[Array[MapTile]]):
  import GameMap.*
  def this(tileSet: TileSet) = 
    this(tileSet, GameMap.standardMap)
  
  def this(map: Array[Array[MapTile]]) =
    this(new TileSet(GameMap.getTileMap(GameMap.standardTiles)),  map)
  
  def updated(x: Int, y: Int, obj: MapTile) = map.updated(y, map(y).updated(x, obj))

  override def toString: String = 
    val sb: StringBuilder = new StringBuilder()
    map.foreach(y =>
        y.foreach(x =>
            sb.append(x.tile)
            )
          sb.append("\n")
          )
    sb.toString

  def getMap = map
    
  def getTile(key: String) = tileSet.getTile(key)

  def withOnMap(x: Int, y: Int, obj: MapTile): GameMap =
    new GameMap(
      tileSet, 
      updated(x, y, obj)
    )

object GameMap:
  val standardTiles: Vector[MapTile] = Vector(
    MapTile("ground", s"${Colors.white}5", MapTile.tags(collidable = false)), 
    MapTile("wall", s"${Colors.blue}#", MapTile.tags(collidable = true)),
    MapTile("player", s"${Colors.red}O", MapTile.tags(collidable = true))
  )
  def getTileMap(tiles: Vector[MapTile] = standardTiles): Map[String, MapTile] = tiles.map(t => (t.tileName -> t)).toMap
  val standardMap = 
    Array(
      Array.fill[MapTile](50)(getTileMap()("wall")),
      getTileMap()("wall") +: Array.fill[MapTile](48)(getTileMap()("ground")) :+ getTileMap()("wall"),
      getTileMap()("wall") +: Array.fill[MapTile](48)(getTileMap()("ground")) :+ getTileMap()("wall"),
      getTileMap()("wall") +: Array.fill[MapTile](48)(getTileMap()("ground")) :+ getTileMap()("wall"),
      getTileMap()("wall") +: Array.fill[MapTile](48)(getTileMap()("ground")) :+ getTileMap()("wall"),
      getTileMap()("wall") +: Array.fill[MapTile](48)(getTileMap()("ground")) :+ getTileMap()("wall"),
      getTileMap()("wall") +: Array.fill[MapTile](48)(getTileMap()("ground")) :+ getTileMap()("wall"),
      Array.fill[MapTile](50)(getTileMap()("wall")),
    )

