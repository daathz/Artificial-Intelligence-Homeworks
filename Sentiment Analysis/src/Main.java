import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static ArrayList<int[]> inputData = new ArrayList<>();
    static int[] inputLabels = new int[8000];
    static ArrayList<int[]> targetData = new ArrayList<>();
    static int[] targetLabels = new int[2000];

    public static void readInput() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 8000; ++i) {
            String temp = scanner.nextLine();
            String[] tempArray = temp.split("\t");
            int[] intArray = Arrays.asList(tempArray).stream().mapToInt(Integer::parseInt).toArray();
            inputData.add(intArray);
        }

        for (int i = 0; i < 8000; ++i) {
            inputLabels[i] = Integer.parseInt(scanner.nextLine());
        }

        for (int i = 0; i < 2000; ++i) {
            String temp = scanner.nextLine();
            String[] tempArray = temp.split("\t");
            int[] intArray = Arrays.asList(tempArray).stream().mapToInt(Integer::parseInt).toArray();
            inputData.add(intArray);
        }
    }

    public static void main(String[] args) {
        readInput();
    }
}
