# Mastermind game

Mastermind is a game where a player tries to guess a secret code consisting of numbers. The computer will provide feedback whether the player's guess is correct or not. The player must guess the right number combination within 10 rounds per game. 


## Game rules

- The computer will generate a random number based on difficulty. It will only be consisting of digits between [0-7].
- The player has 10 chances to guess the secret code.
- After each guess, the computer will provide feedback:
    - The player had guess a correct number
    - The player had guessed a correct number and its correct location
    - The player’s guess was incorrect

    ```Example responses```

    Secret Code => "1234"
    * Player guess: "0000"    | Response: "All pieces are incorrect."
    * Player guess: "1200"    | Response: "2 correct numbers and 2 correct locations"
    * Player guess: "2100"    | Response: "2 correct numbers and 0 correct locations"

## Installing the game

### Prerequisites

To operate this game, the user must have access to a CLI (Command Line Interface).

(Ex: Windows user's can use *Windows Powershell* or *Terminal* applications. macOS users can use the *Terminal* 
application. IDE terminals can also be used.)

### Cloning the Repository

To download this game locally onto your device run the git clone command seen below into the CLI of your choosing. 

```
git clone https://github.com/JustineElbuhel/reach_mastermind.git
```

### API Key

A bearer token is needed to properly generate a secret code from https://www.random.org's API. After the repository is downloaded, the user has to create an account [here](https://accounts.random.org/) from Random.org. After an account is created, in your account settings scroll down until you see API services and click *Use this Service*. It should take you to Random.org's [API Dashboard](https://api.random.org/dashboard). Go ahead and create a New API key. Click on your Key Name you just created and you should be able to see and API Key listed at the top of all the details. Copy that to your clipboard, it will be needed for the next step.

We will need to change one line in the repository downloaded. You can do this either in an IDE or text editor. You will need to navigate to the SecretCode.java file, it is inside src/main/infrastructure. On line 32

```
.header("Authorization", "Bearer " + API_TOKEN)
```

Replace 'API_TOKEN' with the API Key in your clipboard inside quotation marks.

```
.header("Authorization", "Bearer " + "your token here")
```

You can save the file and return to your CLI.

### Running the Application

Once the repository has been cloned to your device, you will want to move directories to be in the correct folder. This can be done by inputting the command below into your CLI.

```
cd reach_mastermind/src
```

Next, you need to compile the Java files by running this command

```
javac main/logic/CheckPieces.java main/logic/UserInputValidation.java main/logic/UserInputValidation.java  main/domain/player.java main/infrastructure/SecretCode.java main/ui/TextUI.java main/Main.java
```

The final step to set up the game is to run the application. Go ahead and enter this command into the CLI

``` 
java main.Main
```
## Class Structure

### Player
Holds general player information, used to display the scoreboard when closing the game.


### SecretCode
Calls the random integer API to generate 4 digits that will be concatenated into String secretCode. If a http response other than 200 occurs, the program will set String secretCode = "" then generate random number.

### CheckPieces
Compares the player's guess to the secret code and will output how many pieces were correctly placed and how many pieces were correct but misplaced.
- @param secretCode - Code the player is trying to guess
- @param userGuess - The guess the player inputs into the console
- @param codeLength - The length of the secret code, determined byt the difficulty

### UserInputValue
Checks to see if the player's guess is the correct number of digits without any letters.
- @param userGuess - Player's guess that was entered into the console/terminal

### TextUI
Executes the text ui, and calls upon the right methods based on user selection (difficulty, game mode)
### Main
Executes the game 

## Extensions

### Multiplayer Versus Mode
This game mode allows for two people two compete to find the secret code before the other. Wins and losses will be stored in the Player Object inside of a list.

### Scoreboard
When the game is being closed, all player scores played during that session will be displayed. It will inclide each player's name, their wins, and their losses. 

## Technologies

* [Java](https://developer.mozilla.org/en-US/docs/Web/HTML)
* [JUnit4](https://junit.org/junit4/)
* [Random Integer API](https://www.random.org/clients/http/api/)

## Authors

**Justine Elbuhel**
- [Link to GitHub](https://github.com/JustineElbuhel)
- [Link to LinkedIn](https://www.linkedin.com/)
