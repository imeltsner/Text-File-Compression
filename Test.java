import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //test to verify CharCounter object returns expected frequencies
        System.out.println("FREQUENCIES FROM CHARCOUNTER");
        CharCounter counter = new CharCounter("test.txt");
        HashMap<Character, Integer> frequencies = counter.count();
        for (Character j : frequencies.keySet()) { //print each key value pair
            System.out.println(j + " " + frequencies.get(j));
        }
        System.out.println("END TEST 1");
        System.out.println();

        //test to verify Generate object correctly generates frequencies
        System.out.println("FREQUENCIES FROM GENERATOR");
        Generator generator = new Generator("test.txt");
        HashMap<Character, Integer> probabilities = generator.generateFrequencies();
        for (Character j : probabilities.keySet()) { //print each key value pair
            System.out.println(j + " " + probabilities.get(j));
        }
        System.out.println("END TEST 2");
        System.out.println();

        //test to verify nodes are properly added to list and sorted
        System.out.println("SORTED FREQUENCIES IN ARRAYLIST");
        ArrayList<Node> nodes = generator.generateNodes(probabilities);
        Collections.sort(nodes);
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println(nodes.get(i).getFrequency());
        }
        System.out.println("END TEST 3");
        System.out.println();

        //test to verify nodes are combined into 1 node
        System.out.println("SIZE AND COMBINED FREQUENCY OF NODES");
        ArrayList<Node> mergedNodes = generator.generateTree(nodes);
        System.out.println(mergedNodes.size() + " " + mergedNodes.get(0).getFrequency());
        System.out.println("END TEST 4");
        System.out.println();

        //test to check codes have properly been assigned
        System.out.println("VALUES OF NODES AT TOP OF TREE");
        generator.setCode(mergedNodes.get(0));
        System.out.println("First left = " + mergedNodes.get(0).left.getCode());
        System.out.println("First right = " + mergedNodes.get(0).right.getCode());
        System.out.println("Left left = " + mergedNodes.get(0).left.left.getCode());
        System.out.println("Left right = " + mergedNodes.get(0).left.right.getCode());
        System.out.println("Right left = " + mergedNodes.get(0).right.left.getCode());
        System.out.println("Right right = " + mergedNodes.get(0).right.right.getCode());
        System.out.println("END TEST 5");
        System.out.println();

        //test to check if dictionary of codes is created by generator
        System.out.println("VALUES OF CHARACTERS AND CORRESPONDING CODES IN GENERATOR");
        generator.createCodeDict(mergedNodes.get(0));
        for (Character code : generator.getCodeDict().keySet()) {
            System.out.println(code + " " + generator.getCodeDict().get(code));
        }
        System.out.println("END TEST 6");
        System.out.println();

        //test to check if dictionary of codes is created by encoder
        System.out.println("VALUES OF CHARACTERS AND CODES IN ENCODER");
        Encoder encoder = new Encoder();
        HashMap<Character, String> encoderCodes = encoder.createCodeDict();
        for (Character code : encoderCodes.keySet()) {
            System.out.println(code + " " + encoderCodes.get(code));
        }
        System.out.println("END TEST 7");
        System.out.println();

        //test to check if dictionary of codes is created by decoder
        System.out.println("KEY VALUE PAIRS IN DECODER HUFFMAN TABLE");
        Decoder decoder = new Decoder("decodedFile.txt");
        HashMap<Character, String> huffmanTable = decoder.createHuffmanTable();
        for (Character code : huffmanTable.keySet()) {
            System.out.println(code + " " + huffmanTable.get(code));
        }
        System.out.println("END TEST 8");
        System.out.println();
        
        //test to check reconstructed tree values for decoding
        System.out.println("TOP OF RECONSTURCTED TREE IN DECODER");
        Node tree = decoder.getTreeHead();
        for (Character key : huffmanTable.keySet()) {
            decoder.createTree(key, huffmanTable.get(key), tree);
        }
        System.out.println(tree.left.code);
        System.out.println(tree.right.code);
        System.out.println(tree.left.left.code);
        System.out.println(tree.left.right.code);
        System.out.println(tree.left.left.right.right.character);
    }
}
