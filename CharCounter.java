import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;

//A class to count the number of chars in a text file and calculate the relative frequency of each char
public class CharCounter {
    HashMap<Character, Integer> counts;
    File trainingFile;

    CharCounter(String aFile)  throws FileNotFoundException {
        trainingFile = new File(aFile);
        counts = new HashMap<Character, Integer>();
        for (int i = 7; i<255; i++) {
            char letter = (char) i;
            counts.put(letter, 0);
        }
    }

    HashMap<Character, Integer> count() {
        //counts number of chars and stores them in a hashmap
        try {
            Scanner scan = new Scanner(trainingFile);
            while (scan.hasNext()) {
                String word = scan.next();
                for (int i = 0; i < word.length(); i++) {
                    String letter = "" + word.charAt(i);
                    if ((letter.codePointAt(0) < 255 && letter.codePointAt(0) > 6) || letter.codePointAt(0) == 4) {
                        if (counts.containsKey(word.charAt(i))) {
                            counts.put(word.charAt(i), counts.get(word.charAt(i))+1);
                        } else {
                            counts.put(word.charAt(i), 1);
                        }
                    }
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        counts.put('\u0004', 1);
        return counts;
    }
}