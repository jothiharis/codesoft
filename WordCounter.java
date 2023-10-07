import java.util.*;
import java.io.*;
import java.util.regex.*;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Prompt user for input
        System.out.println("Enter text or provide a file path:");
        String input = scanner.nextLine();

        // Step 2: Read input text or file and store it in a string
        String text = "";
        try {
            File file = new File(input);
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    text += fileScanner.nextLine() + "\n";
                }
                fileScanner.close();
            } else {
                text = input;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        }

        // Step 3: Split the string into an array of words using space or punctuation as delimiters
        String[] words = text.split("[\\s\\p{Punct}]+");

        // Step 4: Initialize a counter variable to keep track of the number of words
        int wordCount = 0;

        // Step 5: Iterate through the array of words and increment the counter for each word encountered
        for (String word : words) {
            if (!word.isEmpty()) { // Ignore empty strings
                wordCount++;
            }
        }

        // Step 6: Display the total count of words to the user
        System.out.println("Total number of words: " + wordCount);

        // Step 7: Ignore common words or stop words (you can define your list of stop words)
        Set<String> stopWords = new HashSet<>(Arrays.asList("the", "and", "is", "in", "on", "at"));
        int nonStopWordCount = 0;
        for (String word : words) {
            if (!word.isEmpty() && !stopWords.contains(word.toLowerCase())) {
                nonStopWordCount++;
            }
        }
        System.out.println("Number of non-stop words: " + nonStopWordCount);

        // Step 8: Provide statistics like the number of unique words or the frequency of each word
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }
        System.out.println("Number of unique words: " + wordFrequency.size());
        System.out.println("Word frequencies:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        scanner.close();
    }
}
