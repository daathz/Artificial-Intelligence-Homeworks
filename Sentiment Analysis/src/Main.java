import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static ArrayList<int[]> inputData = new ArrayList<>();
    static int[] inputLabels = new int[80000];
    static ArrayList<int[]> targetData = new ArrayList<>();

    public static void readInput() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 80000; ++i) {
            String temp = scanner.nextLine();
            if (temp.equals("")) {
                inputData.add(null);
            } else {
                String[] tempArray = temp.split("\\t");
                int[] intArray = new int[tempArray.length];
                for (int j = 0; j < intArray.length; ++j) {
                    try {
                        intArray[j] = Integer.parseInt(tempArray[j]);
                    } catch (NumberFormatException e) {
                        System.out.println(i);
                    }
                }
                inputData.add(intArray);
            }
        }

        for (int i = 0; i < 80000; ++i) {
            inputLabels[i] = Integer.parseInt(scanner.nextLine());
        }

        for (int i = 0; i < 20000; ++i) {
            String temp = scanner.nextLine();
            if (temp.equals("")) {
                targetData.add(null);
            } else {
                String[] tempArray = temp.split("\\t");
                int[] intArray = new int[tempArray.length];
                for (int j = 0; j < intArray.length; ++j) {
                    intArray[j] = Integer.parseInt(tempArray[j]);
                }
                targetData.add(intArray);
            }
        }
    }

    public static HashMap<Integer, ArrayList<Integer>> makeInitialVocabulary() {
        HashMap<Integer, ArrayList<Integer>> vocabulary = new HashMap<>();
        for (int i = 0; i < 80000; ++i) {
            try {
                for (int j = 0; j < inputData.get(i).length; ++j) {
                    if (!vocabulary.containsKey(inputData.get(i)[j])) {
                        ArrayList<Integer> values = new ArrayList<>();
                        values.add(inputLabels[i]);
                        vocabulary.put(inputData.get(i)[j], values);
                    } else {
                        ArrayList<Integer> values = vocabulary.get(inputData.get(i)[j]);
                        values.add(inputLabels[i]);
                        vocabulary.replace(inputData.get(i)[j], values);
                    }
                }
            } catch (NullPointerException e) {
            }
        }

        return vocabulary;
    }

    public static HashMap<Integer, Double> makeZeroVocabulary(HashMap<Integer, ArrayList<Integer>> initial) {
        HashMap<Integer, Double> vocabulary = new HashMap<>();
        for (int key : initial.keySet()) {
            ArrayList<Integer> list = initial.get(key);
            int counter = 0;
            for (int labels : list) {
                if (labels == 0) {
                    counter++;
                }
            }
            double result = counter / (double) list.size();
            vocabulary.put(key, result);
        }

        return vocabulary;
    }

    public static HashMap<Integer, Double> makeOneVocabulary(HashMap<Integer, ArrayList<Integer>> initial) {
        HashMap<Integer, Double> vocabulary = new HashMap<>();
        for (int key : initial.keySet()) {
            ArrayList<Integer> list = initial.get(key);
            int counter = 1;
            for (int labels : list) {
                if (labels == 0) {
                    counter++;
                }
            }
            double result = counter / (double) list.size();
            vocabulary.put(key, result);
        }

        return vocabulary;
    }

    public static void printResult(HashMap<Integer, Double> zeros, HashMap<Integer, Double> ones) {
        for (int[] message : targetData) {
            if (message == null) {
                System.out.println(0);
                continue;
            }
            int zeroWords = 0;
            int oneWords = 0;
            for (int word : message) {
                if (zeros.get(word) == null) {
                    zeroWords++;
                    oneWords++;
                } else if (zeros.get(word) > ones.get(word)) {
                    zeroWords++;
                } else {
                    oneWords++;
                }
            }
            double probOfZero = Math.log(zeroWords / (message.length + 0.0));
            double probOfOne = Math.log(oneWords / (message.length + 0.0));
            if (probOfZero > probOfOne) {
                System.out.println(0);
            } else {
                System.out.println(1);
            }
        }
    }

    public static void generateRandomOutput() {
        for (int i = 0; i < 20000; ++i) {
            System.out.println(Math.round(Math.random()));
        }
    }

    public static void main(String[] args) {
        readInput();
        HashMap<Integer, ArrayList<Integer>> initialVocabulary = makeInitialVocabulary();
        HashMap<Integer, Double> zeroVocabulary = makeZeroVocabulary(initialVocabulary);
        HashMap<Integer, Double> oneVocabulary = makeOneVocabulary(initialVocabulary);
        printResult(zeroVocabulary, oneVocabulary);
    }
}
