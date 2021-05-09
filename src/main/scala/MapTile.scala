package asciirogue
import scala.collection.immutable.Map => sMap
case class MapTile(
  tileName: String, 
  tile: String, 
  tagMap: sMap[String, Boolean],
  color: Colors
):
  def copy(
    tileName : String = this.tileName, 
    tile : String = this.tile, 
    tagMap : sMap[String, Boolean] = this.tagMap, 
    color : Colors = this.color
  ) =
    MapTile(tileName, tile, tagMap, color)

  
object MapTile:
  def tags(  //add here as more tags are relevant, as well as more .addOnes 
    collidable: Boolean = false
  ): sMap[String, Boolean] = sMap[String, Boolean]("collidable" -> collidable)
