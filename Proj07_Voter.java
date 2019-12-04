//Project_07 Class
public class Proj07_Voter {

 public String name;
 public String[] preferences;

 // print out the voter information.
 public void print() {
  String message = "Voter: name=" + name + " preferences:";

  for (String preference : preferences) {
   message += " " + preference;
  }

  System.out.println(message);
 }


 public int vote(String[] candidates) {
  int candidateIndex = -1;

  boolean isFound;
  for (String preferredCandidate : preferences) {
   if (!isFound) {
    for (int i = 0; i < candidates.length; i++) {
     if (preferredCandidate.equals(candidates[i])) {
      candidateIndex = i;
      isFound = true;
      break;
     }
    }
   } else {
    break;
   }
  }

  

// print out a message, indicating the name of the voter, 
// and the name of the candidate they chose.
  if (candidateIndex >= 0 && candidateIndex < candidates.length) {
   System.out.println(name + " votes for " + candidates[candidateIndex]);
  } else {
   System.out.println(name + " chooses not to vote.");
  }

  return candidateIndex;
 }

}

