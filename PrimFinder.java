/* class PrimFinder *

* CSc 127A Spring 16, Project 04
*
* Author: TODO: Chih Yun Chen
*
* SL Name: TODO: Abigail Dodd
* 
* This project foucs on the a structural thinking and the use of how to you set up a 
* proper method. 
* 
*/

public class PrimeFinder {
  public static void main(String[] args) {
    
    int value = Integer.parseInt (args[0]);
    int count = Integer.parseInt (args[1]);
    int [] prime = new int [count];
    int previous = value;
    
    if (args.length !=2) {
       System.out.println("ERROR");
       System.exit(0);
    } //end if 
    
    if (count<0 || count >= 1000000 || value<2 || value>= 1000000000) {
       System.out.println("ERROR");
       System.exit(0);
    }
      
    if (isPrime (value)) {
      count = count -1; 
      System.out.println(value);
    } //end if 
      
    for (int i=0; i<count; i++) {
      previous =findNextPrime(previous);
      prime[i]=previous; 
    } //end for 1
    
    for (int i=0; i<count; i++) {
      System.out.println(prime[i]);
    } //end for 2
  }//end main 
  
    public static int findNextPrime(int value) {
      int i =value;
      while (true) {
        i++;
      if ( isPrime(i) == true)
        return i; 
      } //end while
       
    } //end findNextPrime5
    
    public static boolean isPrime (int number) {
      if (number !=2 && number !=3 && number <=1 || number%2 ==0 || number%3 ==0){
         return false; 
      } //end for 
        
      for (int i=2; i<number; i++) {
        if (number%i == 0) {
          return false;
        } //end if 
      } //end for 
      return true; 
      
    } //end boolean isPrime

} //end PrimeFinder  


