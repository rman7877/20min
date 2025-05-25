package com.tilldawn.Control;

import java.security.Key;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Game;

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

    public void updateGame(Stage stage) {
        float playerX = playerController.getPlayer().getX();
        float playerY = playerController.getPlayer().getY();

        worldController.update();
        playerController.update(playerX, playerY);
        weaponController.update(playerX, playerY);
        updateLabels(stage);
    }

    public int mouseX = 0;
    public int mouseY = 0;
    private int graphicsMiddleX = Gdx.graphics.getWidth() / 2;
    private int graphicsMiddleY = Gdx.graphics.getHeight() / 2;

    public void updateLabels(Stage stage) {
        stage.clear();
        Label timeLabel = new Label(
                String.format("Time: %02d:%02d", getGame().getRemainingTime() / 60,
                        getGame().getRemainingTime() % 60),
                App.getSkin());
        Label killsLabel = new Label("Kills: " + getGame().getKills(), App.getSkin());
        Label ammoLabel = new Label("Ammo: " + getWeaponController().getWeapon().getAmmo() + "/"
                + getWeaponController().getWeapon().getType().getMaxAmmo(), App.getSkin());
        Label HPLabel = new Label("HP: " + getPlayerController().getPlayer().getHealth(), App.getSkin());
        Label levelLabel = new Label("Level: " + getPlayerController().getPlayer().getLevel(),
                App.getSkin());

        Label playerX = new Label("Player X: " + getPlayerController().getPlayer().getX(), App.getSkin());
        Label playerY = new Label("Player Y: " + getPlayerController().getPlayer().getY(), App.getSkin());

        Label treeX = new Label("Tree X: " + getWorldController().getWorld().getEnemies().get(0).getRect().getX(),
                App.getSkin());
        Label treeY = new Label("Tree Y: " + getWorldController().getWorld().getEnemies().get(0).getRect().getY(),
                App.getSkin());

        Label mouseXLabel = new Label("Mouse X: " + mouseX, App.getSkin());
        Label mouseYLabel = new Label("Mouse Y: " + mouseY, App.getSkin());
        Label graphicsMiddleXLabel = new Label("Graphics Middle X: " + (graphicsMiddleX - mouseX), App.getSkin());
        Label graphicsMiddleYLabel = new Label("Graphics Middle Y: " + (graphicsMiddleY - mouseY), App.getSkin());

        Table table = new Table();

        table.top();
        table.setFillParent(true);
        table.add(timeLabel).expandX().padTop(10);
        table.add(killsLabel).expandX().padTop(10);
        table.add(ammoLabel).expandX().padTop(10);
        table.add(HPLabel).expandX().padTop(10);
        table.add(levelLabel).expandX().padTop(10);

        table.row();
        table.add(playerX).expandX().padTop(10);
        table.add(playerY).expandX().padTop(10);
        table.add(treeX).expandX().padTop(10);
        table.add(treeY).expandX().padTop(10);

        table.row();
        table.add(mouseXLabel).expandX().padTop(10);
        table.add(mouseYLabel).expandX().padTop(10);
        table.add(graphicsMiddleXLabel).expandX().padTop(10);
        table.add(graphicsMiddleYLabel).expandX().padTop(10);

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
