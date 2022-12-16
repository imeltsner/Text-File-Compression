import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//an object to read a codebook and create a hashmap with characters and their huffman codes
public class Encoder {
    HashMap<Character, String> codes;
    File codebookFile;

    Encoder() {
        codes = new HashMap<Character, String>();
        codebookFile = new File("codebook");
    }

    public HashMap<Character, String> createCodeDict() {
        try {
            Scanner scan = new Scanner(codebookFile);
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(":");
                Character key = (char) Integer.parseInt(line[0]);
                String value = line[1];
                codes.put(key, value);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return codes;
    }
}
