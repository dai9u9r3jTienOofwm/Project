package com.badlogic.game;

public class ScoreManager {
    private long currentScore;

    public ScoreManager() {
        this.currentScore = 0;
    }

    public long getCurrentScore() {
        return currentScore;
    }
    public void addScore(long score) {
        this.currentScore += score;
    }

    public void resetScore() {
        this.currentScore = 0;
    }
}
