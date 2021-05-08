package asciirogue
import sys.process.*
import Event.*
import jline.console.{ ConsoleReader, KeyMap, Operation }
import scala.concurrent.{ blocking, Future, ExecutionContext }
import java.util.concurrent.ArrayBlockingQueue
import java.io.{BufferedWriter, OutputStreamWriter}

class Game(dim: (Int, Int)):
  import Game.*
  private var quit = false
  private val map = new GameMap(new TileSet(GameMap.getTileMap(GameMap.standardTiles)), GameMap.standardMap)
  private var player = Player("player", map.tileSet.tileSet("player").tile)
  private var gameObjects: Array[GameObject] = Array()
  private val reader = new ConsoleReader()
  private val keyPresses = new ArrayBlockingQueue[Either[Operation, String]](128)
  private var screen = map
  
  import ExecutionContext.Implicits._
  // inside a background thread
  val inputHandling = Future {
    val km = KeyMap.keyMaps().get("vi-insert")
    while !quit do
      blocking {
        val c = reader.readBinding(km)
        val k: Either[Operation, String] =
          if c == Operation.SELF_INSERT then Right(reader.getLastBinding)
          else Left(c match { case op: Operation => op })
        keyPresses.add(k)
      }
      
  }
  private def run(): Unit = 
    var oldScreen = map
    val out = new BufferedWriter(new OutputStreamWriter(System.out))
    //sets cursor visibility
    out.write("\u001B[?25l\u001b[0;0H" + screen.toString)
    out.flush
    val t0 = System.currentTimeMillis
    while !quit do
      while !keyPresses.isEmpty do
        Option(keyPresses.poll) foreach {key =>
          player.handleKey(key) match
            case QuitEvent => quit = true 
            case PlayerMoveEvent(dx: Int, dy: Int) => 
              if !(screen.getMap(player.y + dy)(player.x + dx).tagMap("collidable")) then  
                player = player.copy(x = player.x + dx, y = player.y + dy)
                screen = map.withOnMap(player.x, player.y, map.tileSet.tileSet(player.tileName))
            case NilEvent => 
        }
        for o <- gameObjects do screen = screen.withOnMap(o.x, o.y, map.tileSet.tileSet(o.tileName))
      end while
      
      val screenString = screen.toString
      if !(oldScreen.getMap equals screen.getMap) then 
        out.write("\u001b[0;0H" + screenString)
        out.flush
        oldScreen = screen
      Thread.sleep(0l.max(maxWaitMillis - (System.currentTimeMillis - t0)))

    end while
    println("has quit!\nGoodbye!\u001B[?25h")
    out.close
    //sets cursor visibility
    


  /**
   * Starts the game
   */
  def start: Unit = 
    run()

end Game

object Game:
  inline val maxWaitMillis = 200
