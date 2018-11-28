import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<ArrayList<Double>> trainSet = new ArrayList<>();
    static ArrayList<Double> trainValues = new ArrayList<>();
    static ArrayList<ArrayList<Double>> testSet = new ArrayList<>();

    public static void readInput() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 17011; ++i) {
            String temp = scanner.nextLine();
            String[] tempArray = temp.split("\t");
            ArrayList<Double> record = new ArrayList<>();
            for (int j = 0; j < 81; ++j) {
                record.add(Double.parseDouble(tempArray[j]));
            }
            trainSet.add(record);
        }
        for (int k = 0; k < 17011; ++k) {
            trainValues.add(Double.parseDouble(scanner.nextLine()));
        }
        for (int l = 0; l < 4252; ++l) {
            String temp = scanner.nextLine();
            String[] tempArray = temp.split("\t");
            ArrayList<Double> record = new ArrayList<>();
            for (int m = 0; m < 81; ++m) {
                record.add(Double.parseDouble(tempArray[m]));
            }
            testSet.add(record);
        }
    }

    public static void main(String[] args) {
        readInput();
        NeuralNetwork neuralNetwork = new NeuralNetwork(81);
        neuralNetwork.fit(trainSet, trainValues);
        ArrayList<Double> results = neuralNetwork.predict(testSet);
        for (double result : results) {
            System.out.println(result);
        }
    }
}
