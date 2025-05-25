package com.tilldawn.Model;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tilldawn.Control.GameController;
import com.tilldawn.Model.Enum.GameTime;
import com.tilldawn.View.GameView;

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

    private GameController controller;

    public boolean abilityMenu = false;

    GameView gameView;

    public void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (remainingTime > 0) {
                    remainingTime--;

                    if (remainingTime % 3 == 0) {
                        checkTentacleMonsterRespawn();
                        throwEyebatbullet();
                    }

                    if (remainingTime % 10 == 0) {
                        checkEyebatRespawn();
                        if (remainingTime * 2 < gameTime && world.getElder() == null) {

                            Gdx.app.postRunnable(() -> {
                                controller.getWorldController().generateElder();
                            });
                        }
                    }

                } else {
                    timer.cancel();
                }
            }
        }, 0, 1000); // Schedule task to run every 1 second
    }

    public void pauseTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void resumeTimer() {
        if (timer == null) {
            startTimer();
        }
    }

    public void pauseGame() {
        pauseTimer();
        gameView.setIspaused(true);
    }

    public void resumeGame() {
        resumeTimer();
        gameView.setIspaused(false);
    }

    private void checkTentacleMonsterRespawn() {
        int count = getTime() / 30;
        if (count > 0)
            Gdx.app.postRunnable(() -> {
                controller.getWorldController().generateTentacleMonster(count);
            });
    }


    public void decreaseRemainingTime(int amount) {
        this.remainingTime -= amount;
        if (this.remainingTime < 0) {
            this.remainingTime = 0;
        }
    }

    private void checkEyebatRespawn() {
        if (remainingTime % 10 == 0) {
            int count = (getTime() * 4 - getGameTime() + 30) / 30;
            if (count > 0)
                Gdx.app.postRunnable(() -> {
                    controller.getWorldController().generateEyebat(count);
                });
        }
    }

    private void throwEyebatbullet() {
        Gdx.app.postRunnable(() -> {
            controller.getWorldController().throwEyebatBullet();
        });
    }

    public Game(int gameTime, Player player, Weapon weapon, World world, OrthographicCamera camera, GameView gameView) {
        this.gameTime = gameTime;
        this.remainingTime = gameTime;
        this.kills = 0;
        this.player = player;
        this.weapon = weapon;
        this.world = world;
        this.camera = camera;
        this.gameView = gameView;
        startTimer();
        game = this;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        Game.game = game;
    }

    public GameView getGameView() {
        return gameView;
    }

    public int getTime() {
        return gameTime - remainingTime;
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

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public GameController getController() {
        return controller;
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
