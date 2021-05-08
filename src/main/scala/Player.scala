package asciirogue
import Event.*
case class Player(
  tileName: String = "player", 
  tile: String = "O", 
  var x: Int = 20, 
  var y: Int = 3,
  keyControl: KeyControl = KeyControl("w", "a", "s", "d")
) extends GameObject:
  def handleKey(key: String): Event = 
    key match 
      case "q" => QuitEvent
      case keyControl.up => y = y - 1; PlayerMoveEvent
      case keyControl.down => y = y + 1; PlayerMoveEvent
      case keyControl.right => x = x + 1; PlayerMoveEvent
      case keyControl.left => x = x - 1; PlayerMoveEvent
      case _ => NilEvent

