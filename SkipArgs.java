/* class SkipArgs *

* CSc 127A Spring 16, Project 04
*
* Author: TODO: Chih Yun Chen
*
* SL Name: TODO: Abigail Dodd

* This program require knowledge of how to understand how to call the method inside 
* the main method. 
* 
*/
public class SkipArgs {
  public static void main(String[] args) {
    
    for (int i=0; i<args.length;i++) {
      printExceptOne (args, i);
    } //end for 

    String[] reverse = new String [args.length];
    int a =0;
    
    for (int i=args.length-1; i>=0; i--) {
      
      reverse [a]= args[i];
      a++; 
    } //end for 
    
    for (int i=0; i<reverse.length; i++) {
        printExceptOne(reverse,i); 
        
    }//end for 
    
  }//end void main
  
public static void  printExceptOne(String[] args, int index) {
  
  String str = "";
  
  for (int i=0; i<args.length; i++) {
    
    if (i==index) {
      str = str + "-" + " ";
    }//end if 
    
    else {
      str = str + args [i] + " ";
    }//end else 
    
  } //end for 
      System.out.println(str);
    
  
} //end void printExceptOne
} //end class SkipArgs 
  

