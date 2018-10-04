import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int[][] labyrinth;
    static  int numberOfItems;
    static int positionX = 0;
    static int positionY = 0;

    public static void read_labyrinth() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> temp = new ArrayList<>();
        while(true) {
            String nextLine = scanner.nextLine();
            if (nextLine.split(" ").length == 1) {
                temp.add(nextLine);
                break;
            }
            else {
                temp.add(nextLine);
            }
        }
        String[] stemp = temp.get(0).split(" ");
        int numberOfRows = temp.size() - 1;
        int numberOfColumns = stemp.length;
        numberOfItems = Integer.parseInt(temp.get(temp.size() - 1));
        labyrinth = new int[numberOfRows][numberOfColumns];
        for (int i = 0; i < temp.size() - 1; ++i) {
            stemp = temp.get(i).split(" ");
            for (int j = 0; j < stemp.length; ++j) {
                labyrinth[i][j] = Integer.parseInt(stemp[j]);
            }
        }
        System.out.println("asd");
    }

    public static void findTheItems() {
        
    }

    public static void main(String[] args) {
        read_labyrinth();
    }
}
