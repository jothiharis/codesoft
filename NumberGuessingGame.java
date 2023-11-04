import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
       
        int minNum = 1;
        int maxNum = 100;
        int maxAttempts = 10;
        int rounds = 0;
        int totalAttempts = 0;
int Score;
       
        System.out.println("Welcome to the Number Guessing Game!");
       
        while (true) {
            int secretNumber = random.nextInt(maxNum - minNum + 1) + minNum;
            int attempts = 0;
            System.out.println("Round " + (rounds + 1));
            System.out.println("Guess a number between " + minNum + " and " + maxNum + ".");
           
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
               
                attempts++;
                totalAttempts++;
               
                if (guess < secretNumber) {
                    System.out.println("Too low! Try again.");
                } else if (guess > secretNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the secret number " + secretNumber + " in " + attempts + " attempts.");
                    break;
                }
            }
           
            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've reached the maximum number of attempts. The secret number was " + secretNumber + ".");
            }
   Score=110-(attempts*10);
            System.out.println("Round "+(rounds+1)+" is completed and your score is "+Score);
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
           
            if (!playAgain.equals("yes")) {
                break;
            }
           
            rounds++;
        }
       
        System.out.println("Game over! You played " + (rounds + 1) + " round(s) and took " + totalAttempts + " attempts.");
       
        scanner.close();
    }
}
