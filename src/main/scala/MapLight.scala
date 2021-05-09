package asciirogue
import scala.math
class MapLight(var x: Int, var y: Int, map: GameMap, intensity: Int): //intensity between 0 and 255
  private val factor = 10
  def getLitMap = 
    var litMap = map.getMap
    for outer <- map.getMap.indices do 
        for tile <- map.getMap(outer).indices do 
          litMap = 
            litMap.updated(outer, litMap(outer).updated(tile, map.getMap(outer)(tile).copy(
            color = 
              Colors(
                (map.getMap(outer)(tile).color.red_part 
                  + (intensity - factor*distanceTo(tile,outer))).min(255).max(0),
                (map.getMap(outer)(tile).color.green_part 
                  + (intensity - factor*distanceTo(tile,outer))).min(255).max(0),  
                (map.getMap(outer)(tile).color.blue_part 
                  + (intensity - factor*distanceTo(tile,outer))).min(255).max(0)
              )
            )
          )
        )
    GameMap(map.tileSet, litMap)
  
  def copy(
    x: Int = this.x,
    y: Int = this.y,
    map: GameMap = this.map,
    intensity: Int = this.intensity
    ): MapLight = MapLight(x, y, map, intensity)
  private def distanceTo(otherX: Int, otherY: Int): Int = Math.hypot(Math.abs(x-otherX), Math.abs(y-otherY)).toInt
