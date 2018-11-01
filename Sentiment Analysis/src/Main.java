import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<int[]> inputData = new ArrayList<>();
    static int[] inputLabels = new int[80000];
    static ArrayList<int[]> targetData = new ArrayList<>();
    static int[] targetLabels = new int[20000];

    public static void readInput() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 80000; ++i) {
            String temp = scanner.nextLine();
            if (temp.equals("")) {
                inputData.add(null);
            } else {
                String[] tempArray = temp.split("\\t");
                int[] intArray = new int[tempArray.length];
                for (int j = 0; j < intArray.length; ++j) {
                    try {
                        intArray[j] = Integer.parseInt(tempArray[j]);
                    } catch (NumberFormatException e) {
                        System.out.println(i);
                    }
                }
                inputData.add(intArray);
            }
        }

        for (int i = 0; i < 80000; ++i) {
            inputLabels[i] = Integer.parseInt(scanner.nextLine());
        }

        for (int i = 0; i < 20000; ++i) {
            String temp = scanner.nextLine();
            if (temp.equals("")) {
                targetData.add(null);
            } else {
                String[] tempArray = temp.split("\\t");
                int[] intArray = new int[tempArray.length];
                for (int j = 0; j < intArray.length; ++j) {
                    intArray[j] = Integer.parseInt(tempArray[j]);
                }
                targetData.add(intArray);
            }
        }
    }

    public static void generateRandomOutput() {
        for (int i = 0; i < 20000; ++i) {
            System.out.println(Math.round(Math.random()));
        }
    }

    public static void main(String[] args) {
        readInput();
        generateRandomOutput();
    }
}
