//Project_06 Class
public class Voter {

 public String name;
 public String[] preferences;

 // print out the voter information
 public void print() {
  String message = "Voter: name=" + name + " preferences:";

//(String preference : preferences)
  for (int i = 0; i < preferences; i++) {
   message += " " + preference;
  } //end for


  System.out.println(message);
 } //end void print 

 //
 public int vote(String[] candidates) {
  int candidateIndex = -1;

  boolean isFound = false;
  for (String preferredCandidate : preferences) {
   if (!isFound) {
    for (int i = 0; i < candidates.length; i++) {
     if (preferredCandidate.equals(candidates[i])) {
      candidateIndex = i;
      isFound = true;
      break;
     } //end if 
    } //end for 
   } else {
    break;
   }
  } //end for 

  

// print out a message, indicating the name of the voter, 
  // and the name of the candidate they chose.
  if (candidateIndex >= 0 && candidateIndex < candidates.length) {
   System.out.println(name + " votes for " + candidates[candidateIndex]);
  } //end if 
  else {
   System.out.println(name + " votes for NONE of them.");
  }//end else 

  return candidateIndex;
 }//end int vote 

}


