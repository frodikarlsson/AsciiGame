package asciirogue
import jline.console.Operation
import Event.*
case class Player(
  tileName: String = "player", 
  tile: String = "O", 
  var x: Int = 5, 
  var y: Int = 3,
  keyControl: KeyControl = KeyControl("w", "a", "s", "d")
) extends GameObject:
  def handleKey(key: Either[jline.console.Operation, String]): Event =
    key match 
      case Right("q") => QuitEvent
      case Right(keyControl.up) => PlayerMoveEvent(0, -1) //todo implement datatype that can be solid, literal wall will do for now
      case Right(keyControl.down) => PlayerMoveEvent(0, 1)
      case Right(keyControl.right) => PlayerMoveEvent(1, 0)
      case Right(keyControl.left) => PlayerMoveEvent(-1, 0)
      case Left(Operation.VI_EOF_MAYBE) => throw Exception("Program was interrupted by input")
      case _ => NilEvent

