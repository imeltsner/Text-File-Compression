import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

//a class that takes a text file and outputs the huffman encoded version
public class Encode {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File uncompressedFile = new File(args[0]);
        File compressedFile = new File(args[1]);
        FileWriter writer = new FileWriter(compressedFile);
        Encoder encoder = new Encoder();
        HashMap<Character, String> codebook = encoder.createCodeDict();
        Scanner scan = new Scanner(uncompressedFile);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            for (int i = 0; i < line.length(); i++) {
                char character = line.charAt(i);
                if (codebook.get(character) != null) {
                    String huffmanCode = codebook.get(character);
                    writer.write(huffmanCode);
                }
            }
            if (scan.hasNextLine()) {
                writer.write(codebook.get('\n'));
            }
        }
        scan.close();
        writer.write(codebook.get('\u0004'));
        writer.close();
    }
}
