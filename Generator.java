import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

//A class to generate a Huffman table
public class Generator {
    CharCounter counter;
    ArrayList<Node> nodes;
    HashMap<Character, String> codes;

    Generator(String fileName) throws FileNotFoundException {
        counter = new CharCounter(fileName);
        nodes = new ArrayList<Node>();
        codes = new HashMap<Character, String>();
    }

    public HashMap<Character, Integer> generateFrequencies() {
        //generates a hashmap of each character and its relative frequency
        HashMap<Character, Integer> frequencies = counter.count();
        return frequencies;
    }    
    
    public ArrayList<Node> generateNodes(HashMap<Character, Integer> frequencies) {
        //generates a list of Nodes containing a character and a frequency
        for (Character letter : frequencies.keySet()) {
            Node newNode = new Node();
            newNode.setChar(letter);
            newNode.setFrequency(frequencies.get(letter));
            nodes.add(newNode);
        }
        return nodes;
    }

    public ArrayList<Node> generateTree(ArrayList<Node> nodes) {
        //generates a tree of nodes ordered by relative frequency
        while (nodes.size() > 1) {
            Node a = nodes.get(0);
            Node b = nodes.get(1);
            Node newNode = new Node(a, b);
            nodes.remove(0);
            nodes.remove(0);
            nodes.add(newNode);
            Collections.sort(nodes);
        }
        return nodes;
    }

    public void setCode(Node n) {
        if (n.left != null) {
            n.left.code = n.code + "0";
            setCode(n.left);
        }
        if (n.right != null) {
            n.right.code = n.code + "1";
            setCode(n.right);
        }
    }

    public void createCodeDict(Node n) {
        if (n.left != null) {
            codes.put(n.left.character, n.left.code);
            createCodeDict(n.left);
        }
        if (n.right != null) {
            codes.put(n.right.character, n.right.code);
            createCodeDict(n.right);
        }
    }

    public HashMap<Character, String> getCodeDict() {
        return codes;
    }
}
