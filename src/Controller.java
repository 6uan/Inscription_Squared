import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    public static void main(String[] args) {
        yourInfoHeader();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the length of the perfect square you are looking for, or 0 for every combination:");
        int targetLength = scanner.nextInt();
        System.out.println();

        try {
            String path = "/Users/mac/Downloads/Inscription_Squared/Data/data.txt";
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (String value : values) {
                    // Use a list to collect results
                    List<String> results = findPerfectSquares(value, targetLength);
                    for (String result : results) {
                        System.out.println(result);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    // Modified to include targetLength parameter
    public static List<String> findPerfectSquares(String value, int targetLength) {
        List<String> perfectSquares = new ArrayList<>();
        // Adjusting the loop to consider the targetLength
        int startLength = targetLength == 0 ? 1 : targetLength; // If 0, start with 1; else, use targetLength
        int endLength = targetLength == 0 ? value.length() : targetLength; // If 0, go up to value.length(); else, use targetLength
        for (int windowLength = startLength; windowLength <= endLength; windowLength++) {
            for (int start = 0; start <= value.length() - windowLength; start++) {
                String substring = value.substring(start, start + windowLength);
                // Avoid parsing excessively long numbers which could throw an exception
                try {
                    long num = Long.parseLong(substring);
                    if (isPerfectSquare(num)) {
                        perfectSquares.add(String.format("Inscription: %s, Perfect Square: %s, Square Root: %d", value, substring, (long) Math.sqrt(num)));
                    }
                } catch (NumberFormatException e) {
                    // Handle or log the exception as necessary
                }
            }
        }
        return perfectSquares;
    }

    public static void yourInfoHeader() {
        System.out.println("====================================================");
        System.out.println("\tWelcome to Inscription Squared");
        System.out.println("\t    find the perfect square");
        System.out.println("====================================================");
        System.out.println();
    }

    public static boolean isPerfectSquare(long num) {
        if (num < 0) return false;
        long sqrt = (long) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}
