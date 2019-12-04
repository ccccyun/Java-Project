public class LightGrid {

 public static final int LENGTH = 10; // should be an even number here
 public static final int HALF_LENGTH = LENGTH / 2;
 public static final int SQUARE_NUMBER = 4;

 public static double[][] grid = new double[SQUARE_NUMBER][SQUARE_NUMBER];

 public static void main(String[] args) {
  StdDraw.setScale(-HALF_LENGTH, (SQUARE_NUMBER * LENGTH - HALF_LENGTH));

  while (true) {
   int xIndex = getIndex(StdDraw.mouseX());
   int yIndex = getIndex(StdDraw.mouseY());

   decrease(xIndex, yIndex);
   StdDraw.clear();
   draw();
   StdDraw.show(10);
  }
 }

 public static void draw() {
  for (int i = 0; i < SQUARE_NUMBER; i++) {
   for (int j = 0; j < SQUARE_NUMBER; j++) {
    int blue = roundedDown(grid[i][j]);
    double x = getCenterCoor(i);
    double y = getCenterCoor(j);

    // draw square
    StdDraw.setPenColor(0, 0, blue);
    StdDraw.filledSquare(x, y, HALF_LENGTH);

    // draw text
    StdDraw.setPenColor(255, 255, 255);
    StdDraw.text(x, y, blue + "");
   }
  }
 }

 public static void decrease(int x, int y) {
  for (int i = 0; i < SQUARE_NUMBER; i++) {
   for (int j = 0; j < SQUARE_NUMBER; j++) {
    grid[i][j] = (x == i && y == j) ? 255 : decrease(grid[i][j]);
   }
  }
 }

 public static int roundedDown(double value) {
  return (int) value;
 }

 public static double decrease(double value) {
  return value * 0.99;
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

