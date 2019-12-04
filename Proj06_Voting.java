import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Proj06_Voting {

 public static void main(String[] args)  throws FileNotFoundException  {
  if (args.length != 1) {
   println("input exactly 1 command");
   System.exit(0);
  } //end if

  String[] candidates = new String[5];
  Voter[] voters = new Voter[12];
  

  readFromFile(args[0], candidates, voters);
  printVoters(voters);
  printCandidates(candidates);
  startDummyElection(voters, candidates);
 } //end main

 public static void readFromFile(String fileName, String[] candidates, Voter[] voters) throws FileNotFoundException {

  File file = new File(fileName);
     
         Scanner sc = new Scanner(file);

         // store 5 candidates
         for (int i = 0; i < 5; i++) {
          candidates[i] = sc.next();
         } //end for 

         // store 12 voters
         for (int i = 0; i < 12; i++) {
           // initiate each voter
           voters[i] = new Voter(); //instance
           voters[i].name = sc.next(); //instance
           voters[i].preferences = new String[5];

          for (int j = 0; j < 5; j++) {
           voters[i].preferences[j] = sc.next();
          } // end for 
         } //end for 

         sc.close();
 } //end void readFromFile

 public static void printVoters(Voter[] voters) {
  println("THESE ARE THE VOTERS:");
  
  // class + instance name + array instance 
  // calling from Voter class 
  for (int i = 0; i < voters.length; i++) {
   voters[i].print();
  } //end for 
 } //end void printVoters 

 public static void printCandidates(String[] candidates) {
  println("THESE ARE THE CANDIDATES:");

  for (int i = 0; i < candidates.length; i++) {
   println(candidates[i]);
  } //end for 
 } //end void printCandidates 

 public static void startDummyElection(Voter[] voters, String[] candidates) {
  println("STARTING A DUMMY ELECTION:");

  for (int i = 0; i < voters.length; i++) {
    // voters[i].vote(candidates); // instance method
     System.out.println(voters[i].name + voters[i].preferences); 
  } //end for  
 } //end void startDummyElection

 public static void println(String s) {
  System.out.println(s);
 } //end void println

}


