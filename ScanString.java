public class ScanString
{
  public static void main (String[] args)
  {
    String t = args [0];
    System.out.println("Original String: "+ t);
    int tLen = t.length();
    
    int xCount = 0;
    int yCount = 0;
    int zCount = 0;
    
    for (int i = 0; i < tLen; i++)
    {
      if (t.charAt(i) == 'x')
      {  
        System.out.println ("Found " + t.charAt(i) + " at index " + i);
        xCount = xCount +1; 
      }    
      
      if (t.charAt(i) == 'y')
      {  
        System.out.println ("Found " + t.charAt(i) + " at index " + i);
        yCount = yCount +1;
      }
      
      if (t.charAt(i) == 'z')
      {  
        System.out.println ("Found " + t.charAt(i) + " at index " + i);
        zCount = zCount +1;
      }
    }
    
    System.out.println("x count: " +xCount);
    System.out.println("y count: " +yCount);
    System.out.println("z count: " +zCount);
  } 
}   
  

    
    
      
