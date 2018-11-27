import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static double[][] trainSet = new double[17011][81];
    static double[] trainValues = new double[17011];
    static double[][] testSet = new double[4252][81];

    public static void readInput() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < trainSet.length; ++i) {
            String temp = scanner.nextLine();
            String[] tempArray = temp.split("\t");
            for (int j = 0; j < trainSet[i].length; ++j) {
                trainSet[i][j] = Double.parseDouble(tempArray[j]);
            }
        }
        for (int k = 0; k < trainValues.length; ++k) {
            trainValues[k] = Double.parseDouble(scanner.nextLine());
        }
        for (int l = 0; l < testSet.length; ++l) {
            String temp = scanner.nextLine();
            String[] tempArray = temp.split("\t");
            for (int m = 0; m < testSet[l].length; ++m) {
                testSet[l][m] = Double.parseDouble(tempArray[m]);
            }
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
