import java.io.File;
import java.io.IOException;
import java.util.HashMap;

//a class to decode a huffman encoded text file that outputs original file
public class Decode {
    public static void main(String[] args) throws IOException {
        File compressedFile = new File(args[0]);
        String decompressedFile = args[1];
        Decoder decoder = new Decoder(decompressedFile);
        HashMap<Character, String> huffmanTable = decoder.createHuffmanTable();
        Node tree = decoder.getTreeHead();
        for (Character key : huffmanTable.keySet()) {
            decoder.createTree(key, huffmanTable.get(key), tree);
        }
        decoder.decodeFile(compressedFile);
    }
}
