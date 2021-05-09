package asciirogue
object LevelMaps:
  object TestMap:
    val tiles: Vector[MapTile] = Vector(
      MapTile("ground", ".", MapTile.tags(collidable = false), Colors.white), 
      MapTile("wall", "#", MapTile.tags(collidable = true), Colors.blue),
      MapTile("player", "O", MapTile.tags(collidable = true), Colors.red)
    )
     
    val levelMap = 
       Array(
         "##################################".toArray,
         "#...................##..........##".toArray,
         "#...................##..........##".toArray,
         "#...................##..........##".toArray,
         "#........##.........##..........##".toArray,
         "#........##.........##..........##".toArray,
         "#...................##...#########".toArray,
         "#............#..................##".toArray,
         "#............#..................##".toArray,
         "##################################".toArray
       )
