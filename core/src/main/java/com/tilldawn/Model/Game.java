package com.tilldawn.Model;

import java.util.Timer;
import java.util.TimerTask;


public class Game {

    private int remainingTime;
    private int gameTime;
    private int kills;

    private Timer timer;

    public void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (remainingTime > 0) {
                    remainingTime--;
                } else {
                    timer.cancel();
                }
            }
        }, 0, 1000); // Schedule task to run every 1 second
    }

    // public void stopTimer() {
    //     if (timer != null) {
    //         timer.cancel();
    //     }
    // }

    public Game(int gameTime) {
        this.gameTime = gameTime;
        this.remainingTime = gameTime;
        this.kills = 0;
        startTimer();
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setTime(int time) {
        this.remainingTime = time;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

}
