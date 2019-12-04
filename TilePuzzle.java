import java.util.Random;
import java.util.Arrays;

public class TilePuzzle {

 public static final int HOLE = 0;
 public static final int LENGTH = 30; // should be a even number here
 public static final int HALF_LENGTH = LENGTH / 2;
 public static final int SQUARE_NUMBER = 4;
 public static final int TOTLE_SQUARES = SQUARE_NUMBER * SQUARE_NUMBER;

 public static int[][] state = new int[SQUARE_NUMBER][SQUARE_NUMBER];

 // used for full credit
 public static int holeX = 0;
 public static int holeY = 0;

 public static void main(String[] args) {
  StdDraw.setScale(-HALF_LENGTH, SQUARE_NUMBER * LENGTH - HALF_LENGTH);
  randomInitState();
  draw();

  while (true) {
   while (StdDraw.mousePressed()) {
    // System.out.println("Clicked");
    if (isChanged()) {
     draw();
    }
   }
  }
 }

 public static boolean isChanged() {
  boolean isChanged = false;

  int mouseX = getIndex(StdDraw.mouseX());
  int mouseY = getIndex(StdDraw.mouseY());

 

if (isAbleToMove(mouseX, mouseY)) {
   move(mouseX, mouseY);
   isChanged = true;
  }

  return isChanged;
 }

 public static boolean isAbleToMove(int mouseX, int mouseY) {
  return !(mouseX == holeX && mouseY == holeY) && (mouseX == holeX || mouseY == holeY);
 }

 public static void move(int mouseX, int mouseY) {
  int dirX = getDirection(mouseX, holeX);
  int dirY = getDirection(mouseY, holeY);

  for (int i = holeX, j = holeY; i != mouseX || j != mouseY; i += dirX, j += dirY) {
   state[i][j] = state[i + dirX][j + dirY];
  }

  state[mouseX][mouseY] = HOLE;
  holeX = mouseX;
  holeY = mouseY;
 }

 public static int getDirection(int coor1, int coor2) {
  int dir = coor1 > coor2 ? 1 : -1;
  return coor1 == coor2 ? 0 : dir; 
 }

 public static void draw() {
  StdDraw.clear();

  // draw grey background
  int center = HALF_LENGTH * SQUARE_NUMBER - HALF_LENGTH;
  StdDraw.setPenColor(200, 200, 200);
  StdDraw.filledSquare(center, center, HALF_LENGTH * SQUARE_NUMBER);

  for (int i = 0; i < SQUARE_NUMBER; i++) {
   for (int j = 0; j < SQUARE_NUMBER; j++) {
    if (state[i][j] != HOLE) {
     double x = getCenterCoor(i);
     double y = getCenterCoor(j);
     
     // draw square
     StdDraw.setPenColor(255, 255, 255);
     StdDraw.filledSquare(x, y, HALF_LENGTH - 1);

     // draw text
     StdDraw.setPenColor(0, 0, 0);
     StdDraw.text(x, y, state[i][j] + "");
    }
   }
  }
 }

 public static void randomInitState() {
  for (int number = 1; number < TOTLE_SQUARES; number++) {
   assignNumber(number, getRandomIndex(), getRandomIndex());
  }

  for (int i = 0; i < SQUARE_NUMBER; i++) {
   for (int j = 0; j < SQUARE_NUMBER; j++) {
    if (state[i][j] == HOLE) {
     holeX = i;
     holeY = j;
     return;
    }
   }
  }
 }

 // generate random number [0, SQUARE_NUMBER - 1]  
 public static int getRandomIndex() {
  return new Random().nextInt(SQUARE_NUMBER);
 }

 // assign number to state[xIndex][yIndex], if assigned, try next one
 public static void assignNumber(int number, int xIndex, int yIndex) {
  for (int i = 0; i < SQUARE_NUMBER; i++) {
   for (int j = 0; j < SQUARE_NUMBER; j++) {
    if (state[xIndex][yIndex] == 0) {
     state[xIndex][yIndex] = number;
     return;
    }
    xIndex = ++xIndex % SQUARE_NUMBER;
   }
   yIndex = ++yIndex % SQUARE_NUMBER;
  }
 }

 public static int getIndex(double coor) {
  int index = (int) (coor + HALF_LENGTH) / LENGTH;
  index = Math.max(index, 0);
  index = Math.min(index, SQUARE_NUMBER - 1);
  return index;
 }

 public static double getCenterCoor(int index) {
  return index * LENGTH;
 }

}

