import java.util.Random;

public class Proj10GameState {

 private final int EMPTY;
 private final int ROBOT;
 private final int RUBBLE;

 private final int BOARD_SIZE;

 private final int LENGTH; 
 private final int HALF_LENGTH;
 private final int HALF_TILE_LENGTH;
 private final int TOTLE_SQUARES;
 private final int STATUS_TEXT_HEIGHT;

 private int[][] state;
 private int playerX;
 private int playerY;
 private boolean isGameOver;
 private int robotsRemaining;

 public Proj10GameState(int boardSize) throws IllegalArgumentException {
  if (boardSize < 5) {
   throw new IllegalArgumentException();
  }

  BOARD_SIZE = boardSize;

  EMPTY = 0;
  ROBOT = 1;
  RUBBLE = 2;

  LENGTH = 30; // should be a even number here

  HALF_LENGTH = LENGTH / 2;
  HALF_TILE_LENGTH = HALF_LENGTH - 1;
  TOTLE_SQUARES = BOARD_SIZE * BOARD_SIZE;
  STATUS_TEXT_HEIGHT = BOARD_SIZE * LENGTH / 20;

  state = new int[BOARD_SIZE][BOARD_SIZE];
  playerX = BOARD_SIZE / 2;
  playerY = BOARD_SIZE / 2;
  isGameOver = false;
  robotsRemaining = 0;

  StdDraw.setYscale(-HALF_LENGTH, BOARD_SIZE * LENGTH - HALF_LENGTH + STATUS_TEXT_HEIGHT);
  StdDraw.setXscale(-HALF_LENGTH, BOARD_SIZE * LENGTH - HALF_LENGTH);
  StdDraw.show(0);
 }

 /****************************************************************************
  ***************************** required methods *****************************
  ****************************************************************************/

 // The addRobots(int) method adds robots (the parameter tells you how many)
 // to the board. Make sure that you don't add two robots in the same location;
 // also don't add them at the same location as the player. (It's OK to have robots
 // right next to the player, though.)
 // Make sure that your method adds as many robots as the parameter calls for;
 // although you know that main() will only ask for 10 robots, use the parameter
 // to control your loop. That way, if we change main(), your method will still do
 // the right thing.
 // This method should not do any drawing.
 public void addRobots(int num) {
  for (int i = 0; i < num; i++) {
   addRobot();
  }

  robotsRemaining = num;
 }

 // The logic of draw() is mostly unchanged, but it now has two parameters, both
 // ints: the current level, and the current number of safe teleports.
 // In addition to drawing the board, draw() must also print the status message
 // across the top: it must tell the player the current level, the number of robots
 // left on the board, and the number of remaining safe teleports.
 public void draw(int level, int safeTeleports) {
  StdDraw.clear();

  for (int i = 0; i < BOARD_SIZE; i++) {
   for (int j = 0; j < BOARD_SIZE; j++) {
    double centerX = getCenterCoor(i);
    double centerY = getCenterCoor(j);

    drawTile(centerX, centerY);

    if (state[i][j] == ROBOT) {
     drawRobot(centerX, centerY);
    } else if (state[i][j] == RUBBLE) {
     drawRubble(centerX, centerY);
    }
   }
  }

  drawPlayer(getCenterCoor(playerX), getCenterCoor(playerY));
  drawStatusText(level, safeTeleports);
 }

 // This method returns true if the game is over, false if not.
 public boolean isGameOver() {
  return isGameOver;
 }

 // This method, like isGameOver(), returns a boolean; it indicates whether all of
 // the robots have been destroyed.
 public boolean allRobotsDestroyed() {
  return robotsRemaining == 0;
 }

 // This method should teleport the player to a new, randomly selected location.
 // This method should not do any checking to see if the location is safe; it's even
 // OK if the player lands directly on top of rubble or a robot (either of which
 // would kill them). main() will do all of the work necessary to Find a safe teleport
 // location - by duplicating the game, and then randomly teleporting.
 public void doTeleport() {
  playerX = getRandomIndex();
  playerY = getRandomIndex();
 }

 // This method must return a new Proj10GameState object. It allocates the new
 // object, copies over all of the variables - including creating a duplicate of the
 // board.
 public Proj10GameState dup() {
  Proj10GameState dup = new Proj10GameState(BOARD_SIZE);

  int[][] dupState = new int[BOARD_SIZE][BOARD_SIZE];
  for (int i = 0; i < BOARD_SIZE; i++) {
   for (int j = 0; j < BOARD_SIZE; j++) {
    dupState[i][j] = state[i][j];
   }
  }

  dup.setState(dupState);
  dup.setPlayerX(playerX);
  dup.setPlayerY(playerY);
  dup.setGameOver(isGameOver);
  dup.setRobotsRemaining(robotsRemaining);

  return dup;
 }

 // This method will move all of the robots toward the player. If two robots crash,
 // they will be replaced with rubble; if a robot crashes into rubble, it will also be
 // removed (but the rubble will stay).
 // This method should update all of the robots, not just some of them (even
 // if one of them catches the player).
 // This method should not do any drawing.
 public void moveRobots() {
  int[][] stateDup = new int[BOARD_SIZE][BOARD_SIZE];

  // copy rubbles in state to stateDup
  for (int i = 0; i < BOARD_SIZE; i++) {
   for (int j = 0; j < BOARD_SIZE; j++) {
    if (state[i][j] == RUBBLE) {
     stateDup[i][j] = RUBBLE;
    }
   }
  }

  // update robots and rubbles
  for (int i = 0; i < BOARD_SIZE; i++) {
   for (int j = 0; j < BOARD_SIZE; j++) {
    if (state[i][j] == ROBOT) {
     int x = getRobotDirection(i, playerX) + i;
     int y = getRobotDirection(j, playerY) + j;

     if (stateDup[x][y] == EMPTY) {
      stateDup[x][y] = ROBOT;
     } else if (stateDup[x][y] == ROBOT) {
      robotsRemaining -= 2;
      stateDup[x][y] = RUBBLE;
     } else {
      robotsRemaining--;
     }

     if (isPlayer(x, y)) {
      isGameOver = true;
     }
    }
   }
  }

  state = stateDup;
 }

 // This method takes a single character which has just been typed on the keyboard
 // and checks to see if it is a valid move. If the key is recognized (and if the move
 // was possible), then update the player position and then return true. If the key
 // is not recognized, or if the move is impossible (because the player is against the
 // edge of the board), then return false.
 // You must support at least the following keys:
 //  w - Move up
 //  a - Move left
 //  s - Don't move, but let the robots move one step closer
 //  d - Move right
 //  x - Move down
 // Additional keys will also need to be supported if you want to earn all of the
 // points for this assignment.
 // The player may never move out of the board. So if the player is on the left
 // edge of the board and tries to move left, the move is ignored. Return false
 // (which means that the robots won't move, either).
 // I encourage you to add (limited) debug messages to the System.out. How-
 // ever, they are not required.
 // This method should not do any drawing.
 //
 // This method must be updated to handle the rubble-shoving logic.
 public boolean handleKeyTyped(char key) {
  boolean isValid = false;

  if (isKeyValid(key)) {
   int dirX = 0;
   int dirY = 0;

   if (key == 'q' || key == 'Q' || key == '7') {
    dirX = -1;
    dirY = 1;
   } else if (key == 'w' || key == 'W' || key == '8') {
    dirY = 1;
   } else if (key == 'e' || key == 'E' || key == '9') {
    dirX = 1;
    dirY = 1;
   } else if (key == 'a' || key == 'A' || key == '4') {
    dirX = -1;
   } else if (key == 'd' || key == 'D' || key == '6') {
    dirX = 1;
   } else if (key == 'z' || key == 'Z' || key == '1') {
    dirX = -1;
    dirY = -1;
   } else if (key == 'x' || key == 'X' || key == '2') {
    dirY = -1;
   } else if (key == 'c' || key == 'C' || key == '3') {
    dirX = 1;
    dirY = -1;
   }

   int x = playerX + dirX;
   int y = playerY + dirY;

   if (isIndexValid(x) && isIndexValid(y)) {
    if (state[x][y] == RUBBLE) {
     if (isIndexValid(x + dirX) && isIndexValid(y + dirY) && state[x + dirX][y + dirY] != RUBBLE) {
      isValid = true;
      playerX = x;
      playerY = y;
      state[x][y] = EMPTY;
      state[x + dirX][y + dirY] = RUBBLE;
     } else {
      System.out.println("Wrong Key Typed! Cannot shove rubble.");
     }
    } else {
     isValid = true;
     playerX = x;
     playerY = y;
    }
   } else {
    System.out.println("Wrong Key Typed! Player is at (" + playerX + ", " + playerY + "), cannot move toward (" + x + ", " + y + ").");
   }
  } else {
   System.out.println("Wrong Key Typed! Key '" + key + "' is not valid!");
  }

  return isValid;
 }

 // -------------------------------------- setters -----------------------------------------
 public void setPlayerX(int playerX) {
  this.playerX = playerX;
 }

 public void setPlayerY(int playerY) {
  this.playerY = playerY;
 }

 public void setGameOver(boolean isGameOver) {
  this.isGameOver = isGameOver;
 }

 public void setRobotsRemaining(int robotsRemaining) {
  this.robotsRemaining = robotsRemaining;
 }

 public void setState(int[][] state) {
  this.state = state;
 }
 // -------------------------------------- setters -----------------------------------------

 /****************************************************************************
  ***************************** private methods ******************************
  ****************************************************************************/
 private boolean isKeyValid (char key) {
  return  key == 'q' || 
    key == 'Q' ||
    key == '7' ||
    key == 'w' ||
    key == 'W' ||
    key == '8' ||
    key == 'e' ||
    key == 'E' ||
    key == '9' ||
    key == 'a' ||
    key == 'A' ||
    key == '4' ||
    key == 's' ||
    key == 'S' ||
    key == '5' ||
    key == 'd' ||
    key == 'D' ||
    key == '6' ||
    key == 'z' ||
    key == 'Z' ||
    key == '1' ||
    key == 'x' ||
    key == 'X' ||
    key == '2' ||
    key == 'c' ||
    key == 'C' ||
    key == '3';
 }


 private void addRobot() {
  boolean isValid = false;

  while (!isValid) {
   int randomX = getRandomIndex();
   int randomY = getRandomIndex();

   if (state[randomX][randomY] == EMPTY && !isPlayer(randomX, randomY)) {
    state[randomX][randomY] = ROBOT;
    isValid = true;
   }
  }
 }

 private boolean isPlayer(int x, int y) {
  return x == playerX && y == playerY;
 }

 // generate random number [0, BOARD_SIZE - 1]  
 private int getRandomIndex() {
  return new Random().nextInt(BOARD_SIZE);
 }

 private double getCenterCoor(int index) {
  return index * LENGTH;
 }

 private void drawTile(double centerX, double centerY) {
  StdDraw.setPenColor(200, 200, 200);
  StdDraw.filledSquare(centerX, centerY, HALF_TILE_LENGTH);
 }

 private void drawRobot(double centerX, double centerY) {
  // square
  StdDraw.setPenColor(0, 0, 255);
  StdDraw.filledSquare(centerX, centerY, HALF_TILE_LENGTH / 1.5);

  // rectangle
  StdDraw.setPenColor(0, 255, 255);
  StdDraw.filledRectangle(centerX, centerY, HALF_TILE_LENGTH / 3, HALF_TILE_LENGTH);

  // line
  double lineX1 = centerX + HALF_TILE_LENGTH;
  double lineY1 = centerY;
  double lineX2 = centerX - HALF_TILE_LENGTH;
  double lineY2 = centerY;
  StdDraw.setPenColor(0, 0, 0);
  StdDraw.line(lineX1, lineY1, lineX2, lineY2);
 }

 private void drawRubble(double centerX, double centerY) {
  // square
  StdDraw.setPenColor(255, 0, 0);
  StdDraw.filledSquare(centerX, centerY, HALF_TILE_LENGTH);

  // rectangle
  StdDraw.setPenColor(255, 255, 0);
  StdDraw.filledRectangle(centerX, centerY, HALF_TILE_LENGTH / 3, HALF_TILE_LENGTH / 1.5);

  // line 1
  double line1X1 = centerX + HALF_TILE_LENGTH;
  double line1Y1 = centerY - HALF_TILE_LENGTH;
  double line1X2 = centerX - HALF_TILE_LENGTH;
  double line1Y2 = centerY + HALF_TILE_LENGTH;
  StdDraw.setPenColor(0, 0, 0);
  StdDraw.line(line1X1, line1Y1, line1X2, line1Y2);

  // line 2
  double line2X1 = centerX + HALF_TILE_LENGTH;
  double line2Y1 = centerY + HALF_TILE_LENGTH;
  double line2X2 = centerX - HALF_TILE_LENGTH;
  double line2Y2 = centerY - HALF_TILE_LENGTH;
  StdDraw.setPenColor(0, 0, 0);
  StdDraw.line(line2X1, line2Y1, line2X2, line2Y2);
 }

 private void drawPlayer(double centerX, double centerY) {
  // head - square
  double headWidth = HALF_TILE_LENGTH;
  double headCenterX = centerX;
  double headCenterY = centerY + headWidth / 2.0;
  StdDraw.setPenColor(0, 255, 0);
  StdDraw.filledSquare(headCenterX, headCenterY, headWidth / 2);

  // body - rectangle
  double bodyWidth = headWidth / 2;
  double bodyHeight = HALF_TILE_LENGTH;
  double bodyCenterX = centerX;
  double bodyCenterY = centerY - headWidth / 2;
  StdDraw.setPenColor(0, 128, 128);
  StdDraw.filledRectangle(bodyCenterX, bodyCenterY, bodyWidth / 2, bodyHeight / 2);

  // left hand - line
  double handLeftX1 = centerX - HALF_TILE_LENGTH / 2;
  double handLeftY1 = bodyCenterY;
  double handLeftX2 = bodyCenterX - bodyWidth / 2;
  double handLeftY2 = bodyCenterY;
  StdDraw.setPenColor(0, 0, 0);
  StdDraw.line(handLeftX1, handLeftY1, handLeftX2, handLeftY2);

  // right hand - line
  double handRightX1 = centerX + HALF_TILE_LENGTH / 2;
  double handRightY1 = bodyCenterY;
  double handRightX2 = bodyCenterX + bodyWidth / 2;
  double handRightY2 = bodyCenterY;
  StdDraw.setPenColor(0, 0, 0);
  StdDraw.line(handRightX1, handRightY1, handRightX2, handRightY2);
 }

 private void drawStatusText(int level, int safeTeleports) {
  String text = "Level: " + level + "  Robots: " + robotsRemaining + "  Safe Teleports: " + safeTeleports;
  double xCenter = getCenterCoor(BOARD_SIZE / 2);
  double yCenter = BOARD_SIZE * LENGTH - HALF_LENGTH + STATUS_TEXT_HEIGHT / 2 - 2;
  StdDraw.setPenColor(0, 0, 255);
  StdDraw.text(xCenter, yCenter, text);
 }

 private boolean isIndexValid(int index) {
  return index >= 0 && index < BOARD_SIZE;
 }

 private int getRobotDirection(int robot, int player) {
  if (player > robot) {
   return 1;
  } else if (player == robot) {
   return 0;
  } else {
   return -1;
  }
 }

}

