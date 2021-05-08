package asciirogue
import sys.process.*
import Event.*
class Game(dim: (Int, Int)):
  import Game.*
  private var quit = false
  private val map = new Map(new TileSet(Map.standardTiles), Map.standardMap)
  private val player = Player("player", map.getTile("player"))
  private var gameObjects: Array[GameObject] = Array()
  var screen = map
  /**
   * Handles the main game loop
   */ 
  private def run(): Unit = 
    while !quit do
      val t0 = System.currentTimeMillis
      if System.in.available > 0 then
        val x = scala.io.StdIn.readByte.toString
        player.handleKey(x) match
          case QuitEvent => quit = true 
          case PlayerMoveEvent => screen = screen.withOnMap(player.x, player.y, player.tileName)
          case NilEvent => 
      for o <- gameObjects do screen = screen.withOnMap(o.x, o.y, o.tileName)
      val screenString = screen.toString
      val temp = "clear".!
      print(screenString)
      Thread.sleep(0l.max(maxWaitMillis - (System.currentTimeMillis - t0)))
    println("has quit!\nGoodbye!")
    


  /**
   * Starts the game
   */
  def start: Unit = 
    run()

end Game

object Game:
  inline val maxWaitMillis = 200
