import java.util.Scanner;
import java.io.*;
import java.lang.StringBuffer;

/*
 * This class implements a spell checker application. 
 * This class requires a proper implementation to the StirngSet class.
 */
public class SpellChecker {

  public static void main(String [] args) {
    File f = new File("dictionary");
    
    try {
      Scanner sk = new Scanner(f);
        
      StringSet x = new StringSet();
    
      // Read in the entire dictionary...
      while (sk.hasNext()) {
        String word = sk.next();
        x.insert(word);      
      }
      System.out.println("Dicitonary loaded...");
     
      sk = new Scanner(System.in);
      
      // Keep suggesting alternatives as long as the user makes an input.
      while (sk.hasNext()) {
        String word = sk.next();
        System.out.println(word);
        if (x.find(word))
	  			System.out.println(word + " is correct.");
        else {
	  			System.out.println("Suggesting alternatives ...");
          // Look into the StringSet for all possible alternatives of the input word mis-spelled by one character.
                for (int i = 0; i < word.length(); i++)
                {
                    StringBuffer string = new StringBuffer(word);
                    for (char letter = 'a'; letter <= 'z'; letter++)
                    {
                    
                    string.setCharAt(i, letter);
                    String str = string.toString();
                    if (x.find(str))
                        System.out.println(str);
                    }

				}
            }
        }
      } 
     catch (FileNotFoundException e) {
      System.out.println("Cannot open file " + f.getAbsolutePath());
      System.out.println(e);
    } 
  } 
}
