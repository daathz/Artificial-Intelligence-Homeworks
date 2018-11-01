import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        for (int i = 0; i < 20000; ++i) {
            double zeroProbability = 0.5;
            double oneProbability = 0.5;
            int[] document = targetData.get(i);
            try {
                for (int j = 0; j < document.length; ++j) {
                    zeroProbability *= zeros.get(document[j]);
                    oneProbability *= ones.get(document[j]);
                }
                if (zeroProbability > oneProbability) {
                    System.out.println(0);
                } else {
                    System.out.println(1);
                }
            } catch (NullPointerException e) {
                System.out.println(0);
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
