import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//a class that creates a codebook with decimal value of characters and corresponding huffman codes
public class Generate {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileName = args[0];
        Generator generator = new Generator(fileName);
        HashMap<Character, Integer> frequencies = generator.generateFrequencies();
        ArrayList<Node> nodes = generator.generateNodes(frequencies);
        Collections.sort(nodes);
        ArrayList<Node> tree = generator.generateTree(nodes);
        generator.setCode(tree.get(0)); 
        generator.createCodeDict(tree.get(0));
        HashMap<Character, String> codes = generator.getCodeDict();
        File output = new File("codebook");
        FileWriter writer = new FileWriter(output);
        for (Character c : codes.keySet()) {
            if (c != null) {
                int UTFdecimal = (int) c;
                String huffmanCode = codes.get(c);
                writer.write(UTFdecimal + ":" + huffmanCode + "\n");
            }
        }
        writer.close();
    }
}
