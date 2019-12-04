/* class Wanderer
*
* CSc 127A Spring 16, Project 03
*
* Author: TODO: Chih Yun Chen
*
* SL Name: TODO: Abigail Dodd

*  This program is design to test student weather they understand StdDraw and Scanner or not.
* Also, let student use what they had learn in the lecture to apply on this project. 
* 
*/

import java.util.Scanner;
import java.awt.*;
public class Test01
{
  public static void main (String[] args) 
  {
    StdDraw.setScale(-10, 10);
  
    int x = 0;
    int y = 0;
    StdDraw.setPenColor(StdDraw.BLUE); 
    StdDraw.filledCircle(x, y, 1); //player at (0,0)
    StdDraw.filledCircle(x+1,y+1,0.5);
    StdDraw.filledCircle(x-1,y+1,0.5);
    
    StdDraw.setPenColor(StdDraw.RED);// target 
    StdDraw.filledSquare(8,7,1);
    StdDraw.filledSquare(7,8,0.5);
    StdDraw.filledSquare(9,8,0.5);
    
    
    Scanner in = new Scanner(System.in); 
    while(in.hasNext())
    {
        
      String dir = in.next();
      
      if (dir.equals("north")|| dir.equals("n")) {
        y++;
        
      }//end if
     
      if (dir.equals("south")||dir.equals("s")) {
        y--;
        
      }//end if
      
      if (dir.equals("e")||dir.equals("east")) {
        x++;
        
      }//end if
     
      if (dir.equals("west")||dir.equals("w")) {
        x--;
        
      }//end if
      
      if (x == 8 && y == 7){
        System.exit(0);
      }// end if 
      
      for (int i=-10; i<=10;i++) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(x,y,1);
        StdDraw.filledCircle(x+1,y+1,0.5);
        StdDraw.filledCircle(x-1,y+1,0.5);
        StdDraw.setPenColor(StdDraw.RED);// target 
        StdDraw.filledSquare(8,7,1);
        StdDraw.filledSquare(7,8,0.5);
        StdDraw.filledSquare(9,8,0.5);
      } //end for
      
    }//end while 
    
    System.out.println("Game Over");
    in.close(); 
  }
}
      

