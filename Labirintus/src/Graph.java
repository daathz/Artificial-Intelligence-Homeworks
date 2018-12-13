import java.util.HashMap;

public class Graph {

    private HashMap<Integer, Node> nodes = new HashMap<>();

    public void addNode(Node node) {
        nodes.put(node.id, node);
    }

    public HashMap<Integer, Node> getNodes() {
        return nodes;
    }
}
