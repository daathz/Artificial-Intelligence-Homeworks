import java.util.ArrayList;

class NeuralNetwork {

    private static int numberOfNeurons;
    private static ArrayList<ArrayList<Double>> hiddenLayerWeights = new ArrayList<>();
    private static ArrayList<Double> outputLayerWeights = new ArrayList<>();

    private static ArrayList<ArrayList<Double>> inputs;
    private static ArrayList<Double> hiddenOutputs;

    NeuralNetwork(int numberOfNeurons) {
        NeuralNetwork.numberOfNeurons = numberOfNeurons;
        for (int i = 0; i < numberOfNeurons; ++i) {
            ArrayList<Double> hiddenLayerWeightsPerNeuron = new ArrayList<>();
            for (int j = 0; j < 81; ++j) {
                double randomValue = Math.random() * 2 - 1;
                hiddenLayerWeightsPerNeuron.add(randomValue);
            }
            hiddenLayerWeights.add(hiddenLayerWeightsPerNeuron);
        }
        for (int k = 0; k < numberOfNeurons; ++k) {
            double randomValue = Math.random() * 2 - 1;
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

    private static double Sigmoid(double input) {
        return (1.0 / (1 + Math.exp(-input)));
    }

    private static double ReLU(double input) {
        return Math.max(0, input);
    }

    private double FeedForward(ArrayList<Double> inputs) {
        hiddenOutputs = new ArrayList<>();
        for (int i = 0; i < numberOfNeurons; ++i) {
            hiddenOutputs.add(Sigmoid(Neuron(inputs, hiddenLayerWeights.get(i), 1.0)));
        }
        return Neuron(hiddenOutputs, outputLayerWeights, 1.0);
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
        double momentum = 0.0002;
        double learningRate = 0.00002;
        double outputError = target - predicted;
        //double errorSignal = (target - predicted) * predicted * (1 - predicted);
        ArrayList<Double> hiddenErrors = new ArrayList<>();
        for (int i = 0; i < hiddenLayerWeights.size(); ++i) {
            double sum = 0.0;
            for (double weight : hiddenLayerWeights.get(i)) {
                sum += weight * outputError;
            }
            double hiddenError = hiddenOutputs.get(i) * (1 - hiddenOutputs.get(i)) * sum;
            hiddenErrors.add(hiddenError);
        }
        double previousWeight = 1.0;
        for (int j = 0; j < outputLayerWeights.size(); ++j) {
            double weightDiff = (learningRate * outputError * hiddenOutputs.get(j) + previousWeight * momentum);
            double changedWeight = outputLayerWeights.get(j) + weightDiff;
            previousWeight = weightDiff;
            outputLayerWeights.set(j, changedWeight);
        }
        previousWeight = 1.0;
        for (int k = 0; k < hiddenLayerWeights.size(); ++k) {
            for (int l = 0; l < hiddenLayerWeights.get(k).size(); ++l) {
                double weightDiff = (learningRate * inputs.get(k).get(l) * hiddenErrors.get(k)) + (previousWeight * momentum);
                double changedWeight = hiddenLayerWeights.get(k).get(l) + weightDiff;
                previousWeight = weightDiff;
                hiddenLayerWeights.get(k).set(l, changedWeight);
            }
        }
    }

    void fit(ArrayList<ArrayList<Double>> inputs, ArrayList<Double> target) {
        NeuralNetwork.inputs = inputs;
        ArrayList<Double> predictedValues = new ArrayList<>();
        for (int j = 0; j < 7; ++j){
            for (int i = 0; i < inputs.size(); ++i) {
                double predicted = FeedForward(inputs.get(i));
                predictedValues.add(predicted);
                BackPropagation(target.get(i), predicted);
            }
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
