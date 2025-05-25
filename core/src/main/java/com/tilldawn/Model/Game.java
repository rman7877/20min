package com.tilldawn.Model;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.graphics.OrthographicCamera;


public class Game {

    private static Game game;

    private int remainingTime;
    private int gameTime;
    private int kills;

    private Player player;
    private Weapon weapon;
    private World world;
    private OrthographicCamera camera;

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


    public Game(int gameTime, Player player, Weapon weapon, World world,OrthographicCamera camera) {
        this.gameTime = gameTime;
        this.remainingTime = gameTime;
        this.kills = 0;
        this.player =player;
        this.weapon = weapon;
        this.world = world;
        this.camera = camera;
        startTimer();
        game = this;
    }

    public static Game getGame() {
        return game;
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

    public OrthographicCamera getCamera() {
        return camera;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

}
