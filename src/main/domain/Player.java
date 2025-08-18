package main.domain;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private String name;
    private ArrayList<Integer> scores;
    private int wins;
    private int loses;

    public Player(String name){
        this.wins = 0;
        this.loses = 0;
        this.name = name;
        this.scores = new ArrayList<>();

    }

    public String getName(){
        return this.name;
    }

    public void addScore(int score){
        scores.add(score);
    }

    public void addWin(){
        this.wins++;
    }

    public void addLoss(){
        this.loses++;
    }

    public String getWinsLosses(){
        return "Total Wins: " + wins + ", Total Loses: " + loses;
    }

    public int getHighScore(){
        return Collections.max(scores);
    }

    public String getPlayerInfo(){
        return this.name + "=> High Score: " + Collections.max(scores) + " Wins: " + wins + " Losses: " + loses;
    }
}
