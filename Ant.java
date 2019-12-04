/* class Ant *

* CSc 127A Spring 16, Project 05
*
* Author: TODO: Chih Yun Chen
*
* SL Name: TODO: Abigail Dodd

* track mouse position, and according that, change the color while ant moves. 
* ant react different depend on what color. 
*
*
*/
import java.awt.Color;
import java.util.Random;

public class Ant {

 public static void main(String[] args) {
  StdDraw.setScale(-5, 25);

  // 2-D array store the grid states
  // 0 --- white
  // 1 --- blue
  // 2 --- green
  // 3 --- red
  int[][] grid = new int[20][20]; //initial new 2D array 20 by 20

  // make the ant in the middle of the panel
  int antX = 12;
  int antY = 12;

  while (true) {
   StdDraw.clear();

   setTileToRedIfClicked(grid);
   
   //drawBorder();
   drawBoard(grid); // draw board
   drawAnt(antX, antY); //draw ant 

   int directionX = moveAnt(grid[antX][antY], antX, getMouseX()); // next moving x - direction 
   int directionY = moveAnt(grid[antX][antY], antY, getMouseY()); // next moving y - directon 
   
   changeGridState(grid, antX, antY);// using changeGridState method to change grid color

   antX += directionX; // change ant's x position. (next step) 
   antY += directionY;// change ant's y postioion. (next step)

   // handle out of border situation
   if (!isIndexValid(antX) || !isIndexValid(antY)) {
    antX = 12;
    antY = 12;
   }
   
   StdDraw.show(100);
  }

 }

 /*************************** required method *******************************/
 public static int moveAnt(int gridState, int antCoord, int mouseCoord) {
  int direction = 0;

  if (gridState == 0) {
   direction = getDirection(antCoord, mouseCoord, true); // if color = white(0), moving forward, 
  } 
  else if (gridState == 1) {
   direction = getDirection(antCoord, mouseCoord, false);// if color = blue(1), moving away
  } 
  else if (gridState == 2) {
   direction = getRandomDirection(); // if color = green(2), get random driection 
  } 

  return direction;
 } 



/*************************** required method *******************************/
 public static void changeGridState(int[][] grid, int x, int y) {
  if (grid[x][y] == 0 || grid[x][y] == 1) {
   grid[x][y]++;
  } 
  else {
    grid[x][y] = 0; 
  }
 } // if the grid is green or red color and next should become white therefore set it to 0 (white) 

 /*************************** required method *******************************/
 public static void drawBoard(int[][] grid) {
  for (int i = 0; i < 20; i++) {
   for (int j = 0; j < 20; j++) {
    Color color = getColor(grid[i][j]);
    StdDraw.setPenColor(color);
    StdDraw.filledSquare(i, j, 0.5);
   }
  }
 }

 /*************************** required method *******************************/
 public static void drawAnt(int x, int y) {
  StdDraw.setPenColor(StdDraw.MAGENTA);
  StdDraw.filledCircle(x, y, 0.5);
 } //end void drawAnt 


 // determine the color from number 
 public static Color getColor(int gridState) { 
  Color color = StdDraw.WHITE;

  if (gridState == 1) {
   color = StdDraw.BLUE;
  } 
  else if (gridState == 2) {
   color = StdDraw.GREEN;
  } 
  else if (gridState == 3) {
   color = StdDraw.RED;
  }

  return color;
 } //end voud drawAnt


/******************************************************/
 public static int getDirection(int antCoord, int mouseCoord, boolean isMoveTowards) {
  if (antCoord == mouseCoord) {
   return 0;
  } //
  int direction = ((mouseCoord > antCoord) ? 1 : -1);//if the mouseCoord is bigger than antCoord return 1, if not return -1. ()
  if (!isMoveTowards) { // if not moving towards so that the ant will move to negative coord
   direction *= -1;
  }
  return direction;
 }//end getDirection 
    
// generate # from [-1, 1]
  // Random r = new Random(); 
  // return (r.nextInt(3)-1);
  // [0,3) = 0,1,2 
  // [0,3) -1 = -1, 0, 1
 public static int getRandomDirection() {
  return new Random().nextInt(3) - 1;
 } //end getRandomDirection

/******************************************************/
 public static void setTileToRedIfClicked(int[][] grid) {
  if (StdDraw.mousePressed()) {
   int x = getMouseX();
   int y = getMouseY();
   System.out.println("Mouse Pressed At Coord: (" + x + ", " + y + ")");
   if (isCoordInTheGrid(x, y)) {
    grid[x][y] = 3; // set grid color to red(3)
   }
  }
 }//end void setTileToRedIfClicked
 
 // check the coord is in the grid or not 
 public static boolean isCoordInTheGrid(int x, int y) {
  return isIndexValid(x) && isIndexValid(y);
 } 

// check to see if the ant is out of bounce 
 public static boolean isIndexValid(int index) {
  return index >= 0 && index < 20;
 } 

 /******************************************************/
 public static int getMouseX() {
  return (int) StdDraw.mouseX();
 } //end int getMouseX

 /******************************************************/
 public static int getMouseY() {
  return (int) StdDraw.mouseY();
 } //end int getMouseY 

}

