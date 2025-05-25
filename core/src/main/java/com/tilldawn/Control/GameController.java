package com.tilldawn.Control;

import java.security.Key;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.Player;

public class GameController {

    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;

    private Game game;

    public GameController(Game game, PlayerController playerController, WorldController worldController,
            WeaponController weaponController) {
        this.game = game;
        this.playerController = playerController;
        this.worldController = worldController;
        this.weaponController = weaponController;
    }

    public void updateGame(Stage stage, float delta) {
        float playerX = playerController.getPlayer().getX();
        float playerY = playerController.getPlayer().getY();

        worldController.update(delta);
        playerController.update(playerX, playerY);
        weaponController.update(playerX, playerY);

        updateLabels(stage);

        // if (game.abilityMenu) {
        // game.pauseGame();
        // App.showDialog("test", () -> {
        // game.abilityMenu = false;
        // game.resumeGame();
        // }, stage);
        // }

    }

    public void pauseGame() {
        Game.getGame().stopTimer();
    }

    public void resumeGame() {
        Game.getGame().resumeTimer();
    }

    public int mouseX = 0;
    public int mouseY = 0;
    private int graphicsMiddleX = Gdx.graphics.getWidth() / 2;
    private int graphicsMiddleY = Gdx.graphics.getHeight() / 2;

    public float angle = 0;
    public float bulletX = 0;
    public float bulletY = 0;

    public void updateLabels(Stage stage) {
        stage.clear();
        Player player = getPlayerController().getPlayer();

        Label timeLabel = new Label(
                String.format("Time: %02d:%02d", getGame().getRemainingTime() / 60,
                        getGame().getRemainingTime() % 60),
                App.getSkin());
        Label killsLabel = new Label("Kills: " + getGame().getKills(), App.getSkin());
        Label ammoLabel = new Label("Ammo: " + getWeaponController().getWeapon().getAmmo() + "/"
                + getWeaponController().getWeapon().getMaxAmmo(), App.getSkin());
        Label HPLabel = new Label("HP: " + player.getHealth(), App.getSkin());
        Label levelLabel = new Label("Level: " + player.getLevel(),
                App.getSkin());
        Label XPLabel = new Label("XP: " + player.getXp(), App.getSkin());

        // Label playerWidth = new Label("Player Width: " + player.getRect().getWidth(), App.getSkin());
        // Label playerHeight = new Label("Player Height: " + player.getRect().getHeight(), App.getSkin());

        int playerXP = player.XPGainedForNextLevel();
        int neededXP = player.XPNeededForNextLevel();
        float progress = (float) playerXP / neededXP;
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.background = App.getSkin().newDrawable("white", Color.DARK_GRAY);
        progressBarStyle.knob = App.getSkin().newDrawable("white", Color.GREEN);
        progressBarStyle.knobBefore = App.getSkin().newDrawable("white", Color.GREEN);

        ProgressBar xpProgressBar = new ProgressBar(0, 1, 0.01f, false, progressBarStyle);
        xpProgressBar.setValue(progress);
        xpProgressBar.setAnimateDuration(0.25f);

        // Label playerXPLabel = new Label("Player XP: " + playerXP, App.getSkin());
        // Label neededXPLabel = new Label("Needed XP: " + neededXP, App.getSkin());

        // Label treeX = new Label("Tree X: " +
        // getWorldController().getWorld().getEnemies().get(0).getRect().getX(),
        // App.getSkin());
        // Label treeY = new Label("Tree Y: " +
        // getWorldController().getWorld().getEnemies().get(0).getRect().getY(),
        // App.getSkin());

        // Label mouseXLabel = new Label("Mouse X: " + mouseX, App.getSkin());
        // Label mouseYLabel = new Label("Mouse Y: " + mouseY, App.getSkin());
        // Label graphicsMiddleXLabel = new Label("Graphics Middle X: " +
        // (graphicsMiddleX - mouseX), App.getSkin());
        // Label graphicsMiddleYLabel = new Label("Graphics Middle Y: " +
        // (graphicsMiddleY - mouseY), App.getSkin());

        // Label angleLabel = new Label("Angle: " + angle, App.getSkin());
        // Label bulletXLabel = new Label("Bullet X: " + bulletX, App.getSkin());
        // Label bulletYLabel = new Label("Bullet Y: " + bulletY, App.getSkin());

        Table table = new Table();

        table.top();
        table.setFillParent(true);
        table.add(timeLabel).expandX().padTop(10);
        table.add(killsLabel).expandX().padTop(10);
        table.add(ammoLabel).expandX().padTop(10);
        table.add(HPLabel).expandX().padTop(10);
        table.add(levelLabel).expandX().padTop(10);
        table.add(XPLabel).expandX().padTop(10);
        // table.row();

        // table.add(playerWidth).expandX().padTop(10);
        // table.add(playerHeight).expandX().padTop(10);

        // table.row();
        // table.add(playerX).expandX().padTop(10);
        // table.add(playerY).expandX().padTop(10);

        table.row();
        table.add(new Label("XP Progress: ", App.getSkin())).expandX().padTop(10);
        table.add(xpProgressBar).expandX().padTop(10).width(200);
        // table.add(playerXPLabel).expandX().padTop(10);
        // table.add(neededXPLabel).expandX().padTop(10);
        // table.add(treeX).expandX().padTop(10);
        // table.add(treeY).expandX().padTop(10);

        // table.row();
        // table.add(mouseXLabel).expandX().padTop(10);
        // table.add(mouseYLabel).expandX().padTop(10);
        // table.add(graphicsMiddleXLabel).expandX().padTop(10);
        // table.add(graphicsMiddleYLabel).expandX().padTop(10);

        // table.row();
        // table.add(angleLabel).expandX().padTop(10);
        // table.add(bulletXLabel).expandX().padTop(10);
        // table.add(bulletYLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    public void handlePlayerMovement(int i) {
        playerController.handlePlayerRun(i);
    }

    public void handlePlayerIdle(int i) {
        playerController.handlePlayerIdle();
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WorldController getWorldController() {
        return worldController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }

    public Game getGame() {
        return game;
    }
}
