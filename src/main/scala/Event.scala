package asciirogue
object Event:
  sealed trait Event
  case object NilEvent extends Event
  case class PlayerMoveEvent(x: Int, y: Int) extends Event
  case object QuitEvent extends Event
