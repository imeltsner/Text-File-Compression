//A an object representing a node of a huffman tree
public class Node implements Comparable<Node> {
    Character character;
    int frequency;
    String code;
    Node left, right;

    Node() {
        //base constructor
        character = null;
        frequency = 0;
        code = "";
        left = null;
        right = null;
    }

    Node(Node a, Node b) {
        //constructs a node that points to two other nodes in tree
        character = null;
        frequency = a.getFrequency() + b.getFrequency();
        code = "";
        left = a;
        right = b;
    }

    public void setFrequency(int num) {
       frequency = num; 
    }

    public void setChar (Character val) {
        character = val;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getCode() {
        return code;
    }

    public int compareTo(Node n) {
        if (this.frequency < n.getFrequency()) {
            return -1;
        } else if (this.frequency == n.getFrequency()) {
            return 0;
        } else {
            return 1;
        }
    }
}
