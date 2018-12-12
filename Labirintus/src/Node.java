import java.util.ArrayList;

class Node {
    static int idCounter = 0;
    int id;
    int x;
    int y;
    boolean treasure = false;
    ArrayList<Node> adjacentNodes = new ArrayList<>();

    boolean westWall = false;
    boolean northWall = false;
    boolean eastWall = false;
    boolean southWall = false;

    Node(int x, int y, int value) {
        id = idCounter;
        idCounter++;
        this.x = x;
        this.y = y;

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
}