import java.util.ArrayList;
import java.util.Random;

public class NeuralNetwork {
    private static ArrayList<Double> inputWeights = new ArrayList<>();
    private static ArrayList<Double> hiddenWeights = new ArrayList<>();
    private static int numberOfNeuronInInputLayer;
    private static int numberOfNeuronsInHiddenLayer;

    public NeuralNetwork(int noNiIL, int noNiHL) {
        numberOfNeuronInInputLayer = noNiIL;
        numberOfNeuronsInHiddenLayer = noNiHL;
        Random random = new Random();
        for (int i = 0; i < numberOfNeuronInInputLayer; ++i) {
            double randomValue = random.nextDouble() * 2 - 1;
            inputWeights.add(randomValue);
        }

    }

    private double Neuron(ArrayList<Double> inputs) {
        ArrayList<Double> additive = new ArrayList<>();
        for (int i = 0; i < inputs.size(); ++i) {
            double temp = inputs.get(i) * inputWeights.get(i);
            additive.add(temp);
        }
        double sum = 0;
        for (double added : additive) {
            sum += added;
        }
        return ActivationFunction(sum);
    }

    //TODO Replace ReLU
    private static double ActivationFunction(double input) {
        if (input <= 0) return 0;
        else return input;
    }
}
