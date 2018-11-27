package NeuralNetwork;

import java.util.Scanner;

public class NNSolutionTwo {

    static int[] architecture;
    static double[][] weights;
    static int numberOfInputs;
    static double[][] inputs;

    public static void reading() {
        Scanner scanner = new Scanner(System.in);
        String reader = scanner.nextLine();
        String[] architectureRaw = reader.split(",");
        architecture = new int[architectureRaw.length];

        for (int i = 0; i < architectureRaw.length; ++i) {
            architecture[i] = Integer.parseInt(architectureRaw[i]);
        }

        int numberOfRows = 0;
        for (int i = 1; i < architecture.length; ++i) {
            numberOfRows += architecture[i];
        }

        weights = new double[numberOfRows][];
        for (int i = 0; i < numberOfRows; ++i) {
            reader = scanner.nextLine();
            String[] weightsRaw = reader.split(",");
            weights[i] = new double[weightsRaw.length];
            for (int j = 0; j < weightsRaw.length; ++j) {
                weights[i][j] = Double.parseDouble(weightsRaw[j]);
            }
        }

        numberOfInputs = Integer.parseInt(scanner.nextLine());
        inputs = new double[numberOfInputs][architecture[0]];
        for (int i = 0; i < numberOfInputs; ++i) {
            reader = scanner.nextLine();
            String[] inputRaw = reader.split(",");
            for (int j = 0; j < inputRaw.length; ++j) {
                inputs[i][j] = Double.parseDouble(inputRaw[j]);
            }
        }

    }

    public static void main(String[] args) {
        reading();
        for (int i = 0; i < weights.length; ++i) {
            for (int j = 0; j < weights[i].length; ++j) {
                System.out.print(weights[i][j] + ",");
            }
            System.out.print('\n');
        }
        for (int i = 0; i < inputs.length; ++i)  {
            for (int j = 0; j < inputs[i].length; ++j) {
                System.out.print(inputs[i][j] + ",");
            }
            System.out.print('\n');
        }
    }
}
