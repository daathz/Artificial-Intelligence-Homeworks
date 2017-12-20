package NeuralNetwork;

import java.util.Random;
import java.util.Scanner;

public class NNSolutionOne {

    public static int[] reading() {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String[] layers = input.split(",");
        int[] neuronLayers = new int[layers.length];
        for (int i = 0; i < layers.length; ++i) {
            neuronLayers[i] = Integer.parseInt(layers[i]);
        }

        return neuronLayers;
    }

    public static void writing(int[] neuronLayers) {
        Random random = new Random();
        for (int i = 0; i < neuronLayers.length; ++i) {
            System.out.print(neuronLayers[i]);
            if(i != neuronLayers.length - 1) {
                System.out.print(",");
            }
        }
        System.out.print('\n');
        for (int i = 1; i < neuronLayers.length; ++i) {
            for (int k = 0; k < neuronLayers[i]; ++k) {
                for (int j = 0; j < neuronLayers[i - 1]; ++j) {
                    double weight = random.nextGaussian() * 0.1;
                    System.out.print(weight + ",");
                }
                System.out.println(0.0);
            }
        }
    }

    public static void main(String[] args) {
        writing(reading());
    }

}


