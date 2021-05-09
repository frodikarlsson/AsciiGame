package asciirogue
import scala.collection.immutable.Map
/**
 * A representation of a map in the game
 * @param tileSet A definition for the ascii to be used for tiles, see TileSet
 */ 
class GameMap(val tileSet: TileSet, map: Array[Array[MapTile]]):
  import GameMap.*
  def this(tileSet: TileSet) = 
    this(tileSet, GameMap.getTilesFromSymbolArray(LevelMaps.TestMap.levelMap, tileSet))
  
  def this(map: Array[Array[MapTile]]) =
    this(new TileSet(GameMap.getTileMap(LevelMaps.TestMap.tiles)),  map)
  
  def updated(x: Int, y: Int, obj: MapTile) = map.updated(y, map(y).updated(x, obj))

  override def toString: String = 
    val sb: StringBuilder = new StringBuilder()
    map.foreach(y =>
        y.foreach(x =>
            sb.append(s"${x.color}${x.tile}")
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
  
  def getTileFromSymbol(key: String, tileSet: TileSet) = tileSet.getTileFromSymbol(key)

  def getTilesFromSymbolArray(symArr: Array[Array[Char]], tileSet: TileSet): Array[Array[MapTile]] = 
    symArr.map(a=> a.map(b=>getTileFromSymbol(b.toString, tileSet)))

  def getTileMap(tiles: Vector[MapTile] = LevelMaps.TestMap.tiles): Map[String, MapTile] = tiles.map(t => (t.tileName -> t)).toMap
