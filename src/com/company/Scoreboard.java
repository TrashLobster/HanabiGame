package com.company;

public class Scoreboard {
    private final int maxScore;
    private int currentScore;

    public Scoreboard() {
        this.maxScore = 25;
        this.currentScore = 0;
    }

    public void increaseScore(int points) {
        this.currentScore += points;
        if(this.currentScore > 25) {
            this.currentScore = 25;
        }
    }
}
