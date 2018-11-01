import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static ArrayList<int[]> inputData = new ArrayList<>();
    static int[] inputLabels = new int[80000];
    static ArrayList<int[]> targetData = new ArrayList<>();
    static int zeroWords = 0;
    static int oneWords = 0;

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

    public static HashMap<Integer, Double[]> makeVocabulary() {
        HashMap<Integer, Integer[]> vocabWithFreqs = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> vocabWithList = new HashMap<>();
        HashMap<Integer, Double[]>  vocabWithProbs = new HashMap<>();
        for (int i = 0; i < 80000; ++i) {
            try {
                for (int j = 0; j < inputData.get(i).length; ++j) {
                    if (!vocabWithList.containsKey(inputData.get(i)[j])) {
                        ArrayList<Integer> values = new ArrayList<>();
                        values.add(inputLabels[i]);
                        vocabWithList.put(inputData.get(i)[j], values);
                    } else {
                        ArrayList<Integer> values = vocabWithList.get(inputData.get(i)[j]);
                        values.add(inputLabels[i]);
                        vocabWithList.replace(inputData.get(i)[j], values);
                    }
                }
            } catch (NullPointerException e) {
            }
        }

        for (int word : vocabWithList.keySet()) {
            int counterZero = 0;
            int counterOne = 0;
            ArrayList<Integer> list = vocabWithList.get(word);
            for (int label : list) {
                if (label == 1) {
                    counterOne++;
                    oneWords++;
                } else {
                    counterZero++;
                    zeroWords++;
                }
            }
            Integer[] freq = {counterZero, counterOne};
            vocabWithFreqs.put(word, freq);
        }
        for (int word: vocabWithFreqs.keySet()) {
            double probOfZero = (vocabWithFreqs.get(word)[0] + 1) / (zeroWords + vocabWithFreqs.size() + 0.0);
            double probOfOne = (vocabWithFreqs.get(word)[1] + 1) / (oneWords + vocabWithFreqs.size() + 0.0);
            Double[] probs = {probOfZero, probOfOne};
            vocabWithProbs.put(word, probs);
        }
        return vocabWithProbs;
    }

    public static void predictLabel(HashMap<Integer, Double[]> vocabulary) {
        for (int[] message: targetData) {
            double probOfZero = 1;
            double probOfOne = 1;
            if (message == null) {
                System.out.println(1);
                continue;
            }
            for (int word : message) {
                probOfZero *= vocabulary.get(word)[0];
                probOfOne *= vocabulary.get(word)[1];
            }
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
        HashMap<Integer, Double[]> vocabulary = makeVocabulary();
        predictLabel(vocabulary);
    }
}
