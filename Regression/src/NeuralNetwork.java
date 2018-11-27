import java.util.ArrayList;
import java.util.Random;

public class NeuralNetwork {
    private static int numberOfLayers;

    private static ArrayList<Double> inputWeights = new ArrayList<>();
    private static ArrayList<Double> hiddenWeights = new ArrayList<>();

    public NeuralNetwork(int numberOfLayers) {
        this.numberOfLayers = numberOfLayers;
        Random random = new Random();
        for (int i = 0; i < numberOfLayers; ++i) {
            double randomValue = random.nextDouble() * 2 - 1;
            inputWeights.add(randomValue);
        }
    }

    private double Neuron(double[] inputs, ArrayList<Double> weights, double bias) {
        ArrayList<Double> additive = new ArrayList<>();
        for (int i = 0; i < inputs.length; ++i) {
            double temp = inputs[i] * weights.get(i);
            additive.add(temp);
        }
        double sum = 0;
        for (double added : additive) {
            sum += added;
        }
        return ActivationFunction(sum + bias);
    }

    private double Neuron(ArrayList<Double> inputs, ArrayList<Double> weights, double bias) {
        ArrayList<Double> additive = new ArrayList<>();
        for (int i = 0; i < inputs.size(); ++i) {
            double temp = inputs.get(i) * weights.get(i);
            additive.add(temp);
        }
        double sum = 0;
        for (double added : additive) {
            sum += added;
        }
        return ActivationFunction(sum + bias);
    }

    //TODO Find out which Activation function to use
    private static double ActivationFunction(double input) {
        if (input <= 0) return 0;
        else return input;
    }

    private double RMSE(double trueValue, double predictedValue) {
        return Math.sqrt(0.5 * Math.pow((trueValue - predictedValue), 2));
    }

    private static void BackPropagation() {

    }

    public void fit(double[][] input, double[] target) {
        for (int i = 0; i < input.length; ++i) {
            ArrayList<Double> inputLayerOutputs = new ArrayList<>();
            for (int j = 0; j < numberOfLayers; ++j) {
                double output = Neuron(input[i], inputWeights, 1);
                inputLayerOutputs.add(output);
            }
            ArrayList<Double> hiddenLayerOutputs = new ArrayList<>();
            for (int k = 0; k < numberOfLayers; ++k) {
                double output = Neuron(inputLayerOutputs, hiddenWeights, 1);
                hiddenLayerOutputs.add(output);
            }
            double sum = 0;
            for (double value : hiddenLayerOutputs) {
                sum += value;
            }

        }
    }

    public ArrayList<Double> predict(double[][] input) {
        ArrayList<Double> result = new ArrayList<>();
        for (double[] inputValues : input) {
            ArrayList<Double> inputLayerOutputs = new ArrayList<>();
            for (int j = 0; j < numberOfLayers; ++j) {
                double output = Neuron(inputValues, inputWeights, 1);
                inputLayerOutputs.add(output);
            }
            ArrayList<Double> hiddenLayerOutputs = new ArrayList<>();
            for (int k = 0; k < numberOfLayers; ++k) {
                double output = Neuron(inputLayerOutputs, hiddenWeights, 1);
                hiddenLayerOutputs.add(output);
            }
            double sum = 0;
            for (double value : hiddenLayerOutputs) {
                sum += value;
            }
            result.add(sum);
        }
        return result;
    }
}
