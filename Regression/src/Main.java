import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static ArrayList<ArrayList<Double>> trainSet = new ArrayList<>();
    static ArrayList<Double> trainValues = new ArrayList<>();
    static ArrayList<ArrayList<Double>> testSet = new ArrayList<>();
    static ArrayList<Double> testValues = new ArrayList<>();

    private static void readInput() {
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
        for (int m = 0; m < 4252; ++m) {
            testValues.add(Double.parseDouble(scanner.nextLine()));
        }
    }

    private static void normalize(ArrayList<ArrayList<Double>> inputs) {
        for (int i = 0; i < 81; ++i) {
            ArrayList<Double> featureList = new ArrayList<>();
            for (ArrayList<Double> input : inputs) {
                featureList.add(input.get(i));
            }
            double max = Collections.max(featureList);
            double min = Collections.min(featureList);
            for (ArrayList<Double> input : inputs) {
                double result = (input.get(i) - min) / (max - min);
                input.set(i, result);
            }
        }
    }

    public static void main(String[] args) {
        readInput();
        NeuralNetwork neuralNetwork = new NeuralNetwork(8);
        normalize(trainSet);
        normalize(testSet);
        neuralNetwork.fit(trainSet, trainValues);
        ArrayList<Double> results = neuralNetwork.predict(testSet);
        for (double result : results) {
            System.out.println(result);
        }
        System.out.println("RMSE: " + NeuralNetwork.RootMeanSquaredError(testValues, results));
    }
}
