package asciirogue
import scala.collection.immutable.Map => sMap
case class MapTile(
  tileName: String, 
  tile: String, 
  tagMap: sMap[String, Boolean],
  color: String
)
  
object MapTile:
  def tags(  //add here as more tags are relevant, as well as more .addOnes 
    collidable: Boolean = false
  ): sMap[String, Boolean] = sMap[String, Boolean]("collidable" -> collidable)
