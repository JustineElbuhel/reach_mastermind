package main.logic;

import java.util.Scanner;

public class UserInputValidation {
    /**
     * @method checkUserInput - Checks to see if the player's guess is a 4 digit guess without any letters.
     * @param userGuess - Player's guess that was entered into the console/terminal.
     * @return userGuess - Valid player guess that will be compared to the secret code.
     */
    public String checkUserGuessInput(String userGuess, int codeLength){

        Scanner scanner = new Scanner(System.in);
        while (userGuess.length() != codeLength || userGuess.matches(".[a-zA-Z].*")){
            if (userGuess.matches(".*[a-zA-Z].*") && userGuess.length() != codeLength) {
                System.out.println("*Your guess is too short/long && contains letters.*");
            } else if (userGuess.length() != 4) {
                System.out.println("*Too many/little digits.*");
            } else if (userGuess.matches(".*[a-zA-Z].*")) {
                System.out.println("*Your guess contains letters.*");
            }

            System.out.println("\033[1mPlease enter a " + codeLength + " digit guess.\033[0m");
            System.out.print("Enter guess: ");
            userGuess = scanner.nextLine();
        }
        return userGuess;
    }
}

