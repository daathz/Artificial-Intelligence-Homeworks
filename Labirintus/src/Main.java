import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int[][] matrix;
    static int numberOfItems;
    static int numberOfRows;
    static int numberOfColumns;
    static Node[][] graph;


    public static void readMaze() {
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
        matrix = new int[numberOfRows][numberOfColumns];
        for (int i = 0; i < temp.size() - 1; ++i) {
            stemp = temp.get(i).split(" ");
            for (int j = 0; j < stemp.length; ++j) {
                matrix[i][j] = Integer.parseInt(stemp[j]);
            }
        }
    }

    public static void makeGraph() {
        graph = new Node[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; ++i) {
            for (int j = 0; j < numberOfColumns; ++j) {
                graph[i][j] = new Node(i, j, matrix[i][j]);
            }
        }
        for (int i = 0; i < numberOfRows; ++i) {
            for (int j = 0; j < numberOfColumns; ++j) {
                if (!graph[i][j].westWall && j > 0) {
                    graph[i][j].adjacentNodes.add(graph[i][j - 1]);
                }
                if (!graph[i][j].northWall && i > 0) {
                    graph[i][j].adjacentNodes.add(graph[i - 1][j]);
                }
                if (!graph[i][j].eastWall && j < numberOfColumns - 1) {
                    graph[i][j].adjacentNodes.add(graph[i][j + 1]);
                }
                if (!graph[i][j].southWall && i < numberOfRows - 1) {
                    graph[i][j].adjacentNodes.add(graph[i + 1][j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        readMaze();
        makeGraph();
    }
}