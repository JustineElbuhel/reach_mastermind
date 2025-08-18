package main.ui;

import java.util.*;

import main.domain.Player;

import main.infrastructure.SecretCode;
import main.logic.Mastermind;

public class TextUI {

    private Scanner scanner;
    private Mastermind mastermind;
    private List<Player> allPlayers;
    private static final String lineBreak = "*************************************";

    public TextUI(Scanner scanner){
        this.scanner = scanner;
        this.mastermind = new Mastermind();
        this.allPlayers = new ArrayList<>();
    }

    public void start() throws Exception {

        System.out.println("Welcome to my Mastermind");
        System.out.println("Can you figure out the secret code before your chances run out?");
        System.out.println();
        System.out.println(lineBreak);

        while(true){

            System.out.println();
            System.out.println("Main Menu");
            System.out.println("[1] Single Player");
            System.out.println("[2] Multiplayer Versus");
            System.out.println("[x] Close game");
            System.out.print("> ");
            String gameModeSelection = scanner.nextLine();

            if(gameModeSelection.equals("1")){
                System.out.println("Single Player");

                System.out.print("Enter player name: ");
                String playerName = scanner.nextLine();
                Player player = new Player(playerName);
                allPlayers.add(player);

                while(true) {
                    singlePlayerMode(player);

                    System.out.println();
                    System.out.println("Play again?");
                    System.out.println("[Y] Yes");
                    System.out.println("[N] No");
                    String samePlayerResponse = scanner.nextLine();
                    if (samePlayerResponse.equalsIgnoreCase("N")) {
                        break;
                    }
                }

            } else if (gameModeSelection.equals("2")){
                System.out.println("Multiplayer Versus");

                System.out.print("Enter first player's name: ");
                String playerOneName = scanner.nextLine();
                System.out.print("Enter second player's name: ");
                String playerTwoName = scanner.nextLine();
                Player player1 = new Player(playerOneName);
                Player player2 = new Player(playerTwoName);
                allPlayers.add(player1);
                allPlayers.add(player2);

                while (true) {
                    multiplayerMode(player1, player2);

                    System.out.println();
                    System.out.println("Play again?");
                    System.out.println("[Y] Yes");
                    System.out.println("[N] No");
                    String samePlayersResponse = scanner.nextLine();
                    if (samePlayersResponse.equalsIgnoreCase("N")) {
                        break;
                    }
                }
            } else if (gameModeSelection.equalsIgnoreCase("x")){
                System.out.println();
                System.out.println("Game closing. Thank you for playing.");

                System.out.println("Scoreboard:");
                for(Player player : allPlayers){
                    System.out.println(player.getPlayerInfo());
                }
                System.out.println();
                break;
            }
        }
    }

    private void singlePlayerMode(Player player) throws Exception {

        int roundN = 1;

        int codeLength = selectDifficulty();

        String secretCode = new SecretCode(codeLength).generateSecretCode();
        System.out.println(secretCode);     //? SECRET CODE

        while (roundN <= 10) {
            System.out.println("Round " + roundN);
            System.out.print("Enter guess: ");
            String userGuess = scanner.nextLine();

            if (secretCode.equals(userGuess)) {
                player.addWin();
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
            player.addLoss();
            System.out.println(lineBreak);
            System.out.println("Sorry, you ran out of guesses. The secret code was " + secretCode);
            System.out.println(lineBreak);
        }
    }

    private void multiplayerMode(Player player1, Player player2) throws Exception {
        int roundN = 1;

        int codeLength = selectDifficulty();

        String secretCode = new SecretCode(codeLength).generateSecretCode();
        System.out.println(secretCode);     //? SECRET CODE


        while (roundN <= 10) {
            System.out.println("Round " + roundN);
            System.out.print(player1.getName() + " enter guess: ");
            String user1Guess = scanner.nextLine();

            if (secretCode.equals(user1Guess)) {
                player1.addWin();
                player2.addLoss();

                System.out.println(lineBreak);
                System.out.println("Congrats! " + player1.getName() +" guessed the secret code!");
                System.out.println(lineBreak);
                break;
            } else {
                mastermind.checkPieces(secretCode, user1Guess, codeLength);
            }

            System.out.print(player2.getName() + " enter guess: ");
            String user2Guess = scanner.nextLine();

            if (secretCode.equals(user2Guess)) {
                player2.addWin();
                player1.addLoss();

                System.out.println(lineBreak);
                System.out.println("Congrats! " + player2.getName() + " guessed the secret code!");
                System.out.println(lineBreak);
                break;
            } else {
                mastermind.checkPieces(secretCode, user1Guess, codeLength);
            }

            roundN++;
            System.out.println();
        }

        if (roundN > 10) {
            player1.addLoss();
            System.out.println(lineBreak);
            System.out.println("Sorry, you ran out of guesses. The secret code was " + secretCode);
            System.out.println(lineBreak);
        }
    }

    private int selectDifficulty(){
        String difficulty = "";
        int codeLength = 4;

        System.out.println();
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

        System.out.println(lineBreak);
        System.out.println("Difficulty: " + difficulty);
        System.out.println("You have 10 chances to guess the " + codeLength + " digit code.");
        System.out.println("Good luck");
        System.out.println(lineBreak);
        System.out.println();
        return codeLength;
    }
}