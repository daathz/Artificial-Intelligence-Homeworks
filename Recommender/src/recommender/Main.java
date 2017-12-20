package recommender;

import java.util.Scanner;

public class Main {
    public static int numberOfRatings;
    public static int[][] ratingMatrix;

    public static void init() {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        String[] temps = temp.split("\t");
        numberOfRatings = Integer.parseInt(temps[0]);
        int numberOfUsers = Integer.parseInt(temps[1]);
        int numberOfMovies = Integer.parseInt(temps[2]);
        ratingMatrix = new int[numberOfUsers][numberOfMovies];

    }

    public static void createRatingMatrix() {
        int user;
        int movie;
        int rating;
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < numberOfRatings; ++i) {
            String temp = scanner.nextLine();
            String[] temps = temp.split("\t");
            rating = Integer.parseInt(temps[2]);
            user = Integer.parseInt(temps[0]);
            movie = Integer.parseInt(temps[1]);
            ratingMatrix[user][movie] = rating;
        }
    }

    public static void main(String[] args) {
        init();
        createRatingMatrix();
    }
}
