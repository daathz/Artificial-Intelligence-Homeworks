import java.util.*;

public class Main {
    static int[][] matrix;
    static int numberOfItems;
    static int numberOfRows;
    static int numberOfColumns;
    static Graph graph;
    static ArrayList<Node> stopBys;


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
        graph = new Graph();
        for (int i = 0; i < numberOfRows; ++i) {
            for (int j = 0; j < numberOfColumns; ++j) {
                graph.addNode(new Node(i, j, matrix[i][j]));
            }
        }
        for (Map.Entry<Integer, Node> entry : graph.getNodes().entrySet()) {
            if (!entry.getValue().westWall) {
                entry.getValue().adjacentNodes.add(graph.getNodes().get(entry.getKey() - 1));
            }
            if (!entry.getValue().northWall && entry.getKey() != 0) {
                entry.getValue().adjacentNodes.add(graph.getNodes().get(entry.getKey() - numberOfColumns));
            }
            if (!entry.getValue().eastWall) {
                entry.getValue().adjacentNodes.add(graph.getNodes().get(entry.getKey() + 1));
            }
            if (!entry.getValue().southWall && entry.getKey() != graph.getNodes().size() - 1) {
                entry.getValue().adjacentNodes.add(graph.getNodes().get(entry.getKey() + numberOfColumns));
            }
        }

    }

    public static void findStops() {
        stopBys = new ArrayList<>();
        stopBys.add(graph.getNodes().get(0));
        for (Map.Entry<Integer, Node> entry : graph.getNodes().entrySet()) {
            if (entry.getValue().treasure && entry.getKey() != 0 && entry.getKey() != graph.getNodes().size()) {
                stopBys.add(entry.getValue());
            }
        }
        stopBys.add(graph.getNodes().get(graph.getNodes().size() - 1));
    }

    static LinkedList<Node> calculateShortestPath(Node source, Node destination) {
        source.setDistance(0);
        HashSet<Node> visitedNodes = new HashSet<>();
        HashSet<Node> unvisitedNodes = new HashSet<>();
        unvisitedNodes.add(source);
        while (unvisitedNodes.size() != 0) {
            Node current = getLowestDistanceNode(unvisitedNodes);
            unvisitedNodes.remove(current);
            for (Node adjacent : current.getAdjacentNodes()) {
                if (!visitedNodes.contains(adjacent)) {
                    if (adjacent == null) {
                        System.err.println(current.id);
                    }
                    calculateMinimumDistance(current, adjacent);
                    unvisitedNodes.add(adjacent);
                }
            }
            visitedNodes.add(current);
        }
        return destination.getShortestPath();
    }

    static Node getLowestDistanceNode(HashSet<Node> unvisitedNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unvisitedNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    static void calculateMinimumDistance(Node source, Node destination) {
        int startDistance = source.getDistance();
        if (startDistance < destination.getDistance()) {
            destination.setDistance(startDistance);
            LinkedList<Node> shortestPath = new LinkedList<>(source.getShortestPath());
            shortestPath.add(source);
            destination.setShortestPath(shortestPath);
        }
    }

    static LinkedList<Integer> findPath() {
        LinkedList<Integer> path = new LinkedList<>();
        for (int i = 1; i < stopBys.size(); ++i) {
            LinkedList<Node> route = calculateShortestPath(stopBys.get(i - 1), stopBys.get(i));
            while (!route.isEmpty()) {
                path.add(route.poll().id);
            }
            for (Map.Entry<Integer, Node> entry : graph.getNodes().entrySet()) {
                entry.getValue().setDistance(Integer.MAX_VALUE);
                entry.getValue().getShortestPath().clear();
            }
        }
        path.add(stopBys.get(stopBys.size() - 1).id);
        return path;
    }

    static void walkTrough(LinkedList<Integer> path) {
        int previousStep = 0;
        for (Integer step : path) {
            if (previousStep != step) {
                Node current = graph.getNodes().get(step);
                System.out.println(current.x + " " + current.y);
                if (current.treasure) {
                    System.out.println("felvesz");
                    current.treasure = false;
                }
            }
            previousStep = step;
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        readMaze();
        makeGraph();
        findStops();
        walkTrough(findPath());

    }
}