import java.util.ArrayList;
import java.util.Random;

class NeuralNetwork {

    private static int numberOfNeurons;
    private static ArrayList<ArrayList<Double>> hiddenLayerWeights = new ArrayList<>();
    private static ArrayList<Double> outputLayerWeights = new ArrayList<>();

    private static ArrayList<ArrayList<Double>> inputs;
    private static ArrayList<Double> hiddenOutputs;

    NeuralNetwork(int numberOfNeurons) {
        NeuralNetwork.numberOfNeurons = numberOfNeurons;
        Random random = new Random();
        for (int i = 0; i < numberOfNeurons; ++i) {
            ArrayList<Double> hiddenLayerWeightsPerNeuron = new ArrayList<>();
            for (int j = 0; j < 81; ++j) {
                double randomValue = random.nextDouble() * 2 - 1;
                hiddenLayerWeightsPerNeuron.add(randomValue);
            }
            hiddenLayerWeights.add(hiddenLayerWeightsPerNeuron);
        }
        for (int k = 0; k < numberOfNeurons; ++k) {
            double randomValue = random.nextDouble() * 2 - 1;
            outputLayerWeights.add(randomValue);
        }
    }

    private double Neuron(ArrayList<Double> inputs, ArrayList<Double> weights, double bias) {
        double sum = 0;
        for (int i = 0; i < inputs.size(); ++i) {
            double temp = inputs.get(i) * weights.get(i);
            sum += temp;
        }
        return sum + bias;
    }

    private static double SigmoidFunction(double input) {
        return (1.0 / (1 + Math.exp(-input)));
    }

    private static double ReLU(double input) {
        return Math.max(0, input);
    }

    private double FeedForward(ArrayList<Double> inputs) {
        hiddenOutputs = new ArrayList<>();
        for (int i = 0; i < numberOfNeurons; ++i) {
            hiddenOutputs.add(SigmoidFunction(Neuron(inputs, hiddenLayerWeights.get(i), 1.0)));
        }
        return ReLU(Neuron(hiddenOutputs, outputLayerWeights, 1.0));
    }

    static double RootMeanSquaredError(ArrayList<Double> trueValues, ArrayList<Double> predictedValues) {
        double sum = 0.0;
        for (int i = 0; i < trueValues.size(); ++i) {
            sum += Math.pow(trueValues.get(i) - predictedValues.get(i), 2);
        }
        double res = sum * (1.0 / trueValues.size());
        res = Math.sqrt(res);
        return res;
    }

    private static void BackPropagation(double target, double predicted) {
        double learningRate = 0.0001;
        //Derivative of the activation function
        double error = target - predicted;
        if (error <= 0) error = 0;
        for (int i = 0; i < outputLayerWeights.size(); ++i) {
            double changeInWeight = learningRate * error * hiddenOutputs.get(i);
            double changedWeight = outputLayerWeights.get(i) + changeInWeight;
            outputLayerWeights.set(i, changedWeight);
        }
        double error2 = (target - predicted) * predicted * (1.0 - predicted);
        for (int j = 0; j < hiddenLayerWeights.size(); ++j) {
            double sum = 0.0;
            for (Double weight : outputLayerWeights) {
                sum += (weight * error2);
            }
            double delta = (target - predicted) * predicted * sum;
            for (int k = 0; k < hiddenLayerWeights.get(j).size(); ++k) {
                double changeInWeight = learningRate * delta * inputs.get(j).get(k);
                double changedWeight = hiddenLayerWeights.get(j).get(k) + changeInWeight;
                hiddenLayerWeights.get(j).set(k, changedWeight);
            }
        }
    }

    void fit(ArrayList<ArrayList<Double>> inputs, ArrayList<Double> target) {
        NeuralNetwork.inputs = inputs;
        for (int i = 0; i < inputs.size(); ++i) {
            double predicted = FeedForward(inputs.get(i));
            BackPropagation(target.get(i), predicted);
        }
    }

    ArrayList<Double> predict(ArrayList<ArrayList<Double>> inputs) {
        ArrayList<Double> output = new ArrayList<>();
        for (ArrayList<Double> input : inputs) {
            output.add(FeedForward(input));
        }
        return output;
    }
}
