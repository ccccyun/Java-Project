/* class ScanString

*

* CSc 127A Spring 16, Project 02

*

* Author: TODO: Chih Yun Chen

* SL Name: TODO: Abigail Dodd

* ---


* This program is design to test student weather they understand String and CharAt or not.
* Also, let student use what their had learn in the lecture to apply on this progject. 
* 

*/

public class FindFactors
{
  public static void main (String[] args)
  {
    
    int t;
    t = Integer.parseInt(args[0]);
    
    int sum = 0;
    int factorCount = 0;
    
    
    for (int i = 1; i < t+1; i++)
    {  
      
      if (t <= 0)
      {
        System.out.println("ERROR: The input must be a positive integer");
        return; 
        
      }//end if
        
      if (t % i == 0)
      {
        System.out.println(i + " is a factor of " + t);

        sum = i + sum;
        factorCount = factorCount +1;
        
      }//end if 
      
    }// end for
    
    System.out.println(t + " has " + factorCount + " factors.");
    System.out.println("The sum of all of the factors of " + t + " is " + sum + ".");
    
    if (sum == 2*t)
    {
      System.out.println( t + " is a PERFECT NUMBER!");
      
    }//end if 
    
    
  }
}

