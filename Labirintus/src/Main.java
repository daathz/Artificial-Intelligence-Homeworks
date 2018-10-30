import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int[][] maze;
    static int numberOfItems;
    static int numberOfRows;
    static int numberOfColumns;
    static Coordinate actualCoordinate;
    static Node[][] graph;


    public static void readLabyrinth() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> temp = new ArrayList<>();
        while (true) {
            String nextLine = scanner.nextLine();
            if (nextLine.split(" ").length == 1) {
                temp.add(nextLine);
                break;
            } else {
                temp.add(nextLine);
            }
        }
        String[] stemp = temp.get(0).split(" ");
        numberOfRows = temp.size() - 1;
        numberOfColumns = stemp.length;
        numberOfItems = Integer.parseInt(temp.get(temp.size() - 1));
        maze = new int[numberOfRows][numberOfColumns];
        for (int i = 0; i < temp.size() - 1; ++i) {
            stemp = temp.get(i).split(" ");
            for (int j = 0; j < stemp.length; ++j) {
                maze[i][j] = Integer.parseInt(stemp[j]);
            }
        }
        actualCoordinate = new Coordinate(0, 0);
    }

    public static void makeGraph() {
        graph = new Node[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; ++i) {
            for (int j = 0; j < numberOfColumns; ++j) {
                graph[i][j] = new Node(new Coordinate(i, j), maze[i][j]);
            }
        }
        //TODO Add adjacencies
    }

//    public static void oneStep() {
//        // Basic (shit) algorithm
//        int value = maze[actualRow][actualColumn];
//        boolean isItem = (value & 16) != 0;
//        boolean cantGoNorth = (value & 1) != 0;
//        boolean cantGoEast = (value & 2) != 0;
//        boolean cantGoSouth = (value & 4) != 0;
//        boolean cantGoWest = (value & 8) != 0;
//
//        if (isItem) {
//            System.out.println("felvesz");
//        }
//
//        if (!cantGoEast && actualColumn + 1 < numberOfColumns) {
//            actualColumn++;
//            System.out.println(actualRow + " " + actualColumn);
//        }
//        else if (!cantGoSouth && actualRow + 1 < numberOfRows) {
//            actualRow++;
//            System.out.println(actualRow + " " + actualColumn);
//        }
//        else if (!cantGoWest && actualColumn > 0) {
//            actualColumn--;
//            System.out.println(actualRow + " " + actualColumn);
//        }
//        else if (!cantGoNorth && actualRow > 0) {
//            actualRow--;
//            System.out.println(actualRow + " " + actualColumn);
//        }
//    }

    public static void main(String[] args) {
        readLabyrinth();
    }
}

class Coordinate {
    public int row;
    public int column;

    public Coordinate(int r, int c) {
        row = r;
        column = c;
    }
}

