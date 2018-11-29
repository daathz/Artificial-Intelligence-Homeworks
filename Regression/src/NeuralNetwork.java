import java.util.ArrayList;
import java.util.Random;

class NeuralNetwork {

    private static ArrayList<ArrayList<Double>> hiddenLayerWeights = new ArrayList<>();
    private static ArrayList<Double> outputLayerWeights = new ArrayList<>();
    private static ArrayList<Double> hiddenOutputs;

    NeuralNetwork(int numberOfNeurons) {
        Random random = new Random();
        for (int i = 0; i < numberOfNeurons; ++i) {
            ArrayList<Double> hiddenLayerWeightsPerNeuron = new ArrayList<>();
            for (int j = 0; j < numberOfNeurons; ++j) {
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

    private double FeedForward(ArrayList<Double> inputs) {
            hiddenOutputs = new ArrayList<>();
            for (int i = 0; i < inputs.size(); ++i) {
                hiddenOutputs.add(Neuron(inputs, hiddenLayerWeights.get(i), 1.0));
            }
            return Neuron(hiddenOutputs, outputLayerWeights, 1.0);
        }

    private double RootMeanSquaredError(double trueValue, double predictedValue) {
        return Math.sqrt(0.5 * Math.pow((trueValue - predictedValue), 2));
    }

    private static void BackPropagation(double target, double predicted) {
        double learningRate = 0.01;
        double diffOfVals = target - predicted;
        double errorSignal = diffOfVals * predicted * (1.0 - predicted);
        //Output Layer
        for (int i = 0; i < outputLayerWeights.size(); ++i) {
            double changeInWeight = learningRate * errorSignal * hiddenOutputs.get(i);
            outputLayerWeights.set(i, (outputLayerWeights.get(i) + changeInWeight));
        }
        //Hidden Layer
        for (int j = 0; j < hiddenLayerWeights.size(); ++j) {
            double sum = 1.0;
            for (int k = 0; k < outputLayerWeights.size(); ++k) {
                sum *= (outputLayerWeights.get(k) * errorSignal);
            }
            double errorSignalOfNode = (target - predicted) * predicted * sum;
            for (int l = 0; l < hiddenLayerWeights.get(j).size(); ++l) {
                double changeInWeight = learningRate * errorSignalOfNode * ;
                hiddenLayerWeights.get(j)
                        .set(l, (hiddenLayerWeights.get(j).get(l) + changeInWeight));
            }
        }
    }

    void fit(ArrayList<ArrayList<Double>> inputs, ArrayList<Double> target) {
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
