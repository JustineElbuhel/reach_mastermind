package main.ui;

import java.util.Scanner;

import main.domain.SecretCode;
import main.logic.Mastermind;

public class TextUI {

    private Scanner scanner;
    private Mastermind mastermind;
    private static final String lineBreak = "*************************************";

    public TextUI(Scanner scanner){
        this.scanner = scanner;
        this.mastermind = new Mastermind();
    }

    public void start() throws Exception {
        String difficulty = "";
        int codeLength = 0;
        int roundN = 1;


        System.out.println("Welcome to my Mastermind");
        System.out.println("Can you figure out the secret code before your chances run out?");
        System.out.println();
        System.out.println(lineBreak);
        System.out.println();

        while(true){
            System.out.println("Choose a difficulty:");
            System.out.println("[1] Easy    - 4 Digit Secret Code");
            System.out.println("[2] Medium  - 5 Digit Secret Code");
            System.out.println("[3] Hard    - 6 Digit Secret Code");

            System.out.print("> ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("1")) {
                difficulty = "Easy";
                codeLength = 4;
            } else if (answer.equals("2")) {
                difficulty = "Medium";
                codeLength = 5;
            } else if (answer.equals("3")) {
                difficulty = "Hard";
                codeLength = 6;
            }

            String secretCode = new SecretCode(codeLength).generateSecretCode();
            System.out.println(secretCode);     //? SECRET CODE

            System.out.println(lineBreak);
            System.out.println("Difficulty: " + difficulty);
            System.out.println("You have 10 chances to guess the " + codeLength + " digit code.");
            System.out.println("Good luck");
            System.out.println(lineBreak);
            System.out.println();

            while (roundN <= 10) {
                System.out.println("Round " + roundN);
                System.out.print("Enter guess: ");
                String userGuess = scanner.nextLine();

                if (secretCode.equals(userGuess)) {
                    System.out.println(lineBreak);
                    System.out.println("Congrats! You guessed the secret code!");
                    System.out.println(lineBreak);
                    break;
                } else {
                    mastermind.checkPieces(secretCode, userGuess, codeLength);
                }
                roundN++;
                System.out.println();
            }

            if (roundN > 10) {
                System.out.println(lineBreak);
                System.out.println("Sorry, you ran out of guesses. The secret code was " + secretCode);
                System.out.println(lineBreak);
            }

            System.out.println();
            System.out.println("Would you like to play again?");
            System.out.println("[Y] Yes");
            System.out.println("[N] No");
            String playAgain = scanner.nextLine();

            if(playAgain.equalsIgnoreCase("N")){
                System.out.println("Game closing. Thank you for playing.");
                break;
            }
        }
    }
}