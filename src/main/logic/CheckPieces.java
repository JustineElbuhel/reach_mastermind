package main.logic;

import java.util.ArrayList;

public class CheckPieces{

    public void checkPieces(String secretCode, String userGuess, int codeLength){
        int correctN = 0;
        int correctLocation = 0;
        ArrayList<Character> numbersGuessed = new ArrayList<>();

        for(int i = 0; i < codeLength; i++){
            if(userGuess.charAt(i) == secretCode.charAt(i)){
                correctN++;
                correctLocation++;
            } else {
                for(int j = 0; j < codeLength; j++){
                    if(numbersGuessed.contains(userGuess.charAt(i))){
                        break;
                    } else if(userGuess.charAt(i) == secretCode.charAt(j) && i != j){
                        correctN++;
                        break;
                    }
                }
            }

            numbersGuessed.add(userGuess.charAt(i));
        }

        if(correctN == 0 && correctLocation == 0){
            System.out.println("All pieces are incorrect.");
        } else {
            System.out.println(correctN + " correct number and " + correctLocation + " placed correctly.");
        }
    }
}
