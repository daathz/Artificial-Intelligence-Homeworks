import java.util.*;

class Node {
    static int idCounter = 0;
    int id;
    int x;
    int y;
    boolean treasure = false;

    LinkedList<Node> shortestPath = new LinkedList<>();

    int distance;

    LinkedList<Node> adjacentNodes = new LinkedList<>();

    boolean westWall = false;
    boolean northWall = false;
    boolean eastWall = false;
    boolean southWall = false;

    Node(int x, int y, int value) {
        id = idCounter;
        idCounter++;
        this.x = x;
        this.y = y;
        distance = Integer.MAX_VALUE;

        if ((value & 16) != 0) {
            treasure = true;
        }
        if ((value & 1) != 0) {
            northWall = true;
        }
        if ((value & 2) != 0) {
            eastWall = true;
        }
        if ((value & 4) != 0) {
            southWall = true;
        }
        if ((value & 8) != 0) {
            westWall = true;
        }
    }

    public LinkedList<Node> getAdjacentNodes() {
        return adjacentNodes;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public LinkedList<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }
}