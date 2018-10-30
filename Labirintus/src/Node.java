import java.util.ArrayList;

public class Node {
    static int idCounter = 0;
    int id;
    Coordinate coordinate;
    boolean treasure = false;
    ArrayList<Node> adjacentNodes = new ArrayList<>();

    boolean westWall = false;
    boolean northWall = false;
    boolean eastWall = false;
    boolean southWall = false;

    public Node(Coordinate coordinate, int value) {
        id = idCounter;
        idCounter++;
        this.coordinate = coordinate;

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

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
