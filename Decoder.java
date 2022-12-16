import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

//a class to decode a huffman encoded text file
public class Decoder {
    HashMap<Character, String> huffmanTable;
    File codebook;
    Node head;
    FileWriter writer;

    Decoder(String outputFile) throws IOException{
        codebook = new File("codebook");
        huffmanTable = new HashMap<Character, String>();
        head = new Node();
        writer = new FileWriter(outputFile);
    }

    public HashMap<Character, String> createHuffmanTable() {
        try {
            Scanner scan = new Scanner(codebook);
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(":");
                Character key = (char) Integer.parseInt(line[0]);
                String value = line[1];
                huffmanTable.put(key, value);
            }
            scan.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return huffmanTable;
    }

    public void createTree(Character symbol, String huffmanCode, Node n) {
        if (huffmanCode.charAt(0) == '0' && n.left == null) {
            n.left = new Node();
            n.left.code = n.code + "0";
            if (huffmanCode.length() == 1) {
                n.left.character = symbol;
            } else {
                createTree(symbol, huffmanCode.substring(1, huffmanCode.length()), n.left);
            }
        } else if (huffmanCode.charAt(0) == '0') {
            if (huffmanCode.length() == 1) {
                n.left.character = symbol;
            } else {
                createTree(symbol, huffmanCode.substring(1, huffmanCode.length()), n.left);
            }
        }
        if (huffmanCode.charAt(0) == '1' && n.right == null) {
            n.right = new Node();
            n.right.code = n.code + "1";
            if (huffmanCode.length() == 1) {
                n.right.character = symbol;
            } else {
                createTree(symbol, huffmanCode.substring(1, huffmanCode.length()), n.right);
            }
        } else if (huffmanCode.charAt(0) == '1') {
            if (huffmanCode.length() == 1) {
                n.right.character = symbol;
            } else {
                createTree(symbol, huffmanCode.substring(1, huffmanCode.length()), n.right);
            }
        }
    }

    public void decodeFile(File compressedFile) throws FileNotFoundException, IOException {
        Node currentNode = getTreeHead();
        Scanner scan = new Scanner(compressedFile);
        while (scan.hasNextLine()) {
            String[] next = scan.nextLine().split("");
            for (String num : next) {
                if (num.equals("0") && currentNode.left != null) {
                    currentNode = currentNode.left;
                } else if (num.equals("1") && currentNode.right != null) {
                    currentNode = currentNode.right;
                }
                if (currentNode.character != null) {
                    if (currentNode.character != '\u0004') {
                        writer.write(currentNode.character);
                        currentNode = getTreeHead();
                    } else {
                        break;
                    }
                }
            }
        }
        scan.close();
        writer.close();
    }

    public Node getTreeHead() {
        return head;
    }
}
