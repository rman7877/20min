package com.tilldawn.Control;

import java.security.Key;

import com.badlogic.gdx.Input.Keys;
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

    public void updateGame() {
        worldController.update();
        playerController.update();
        weaponController.update();
    }

    private int getDeltaX(int i) {
        int deltaX = 1;
        if (i == Keys.A)
            deltaX = -1;
        else if (i == Keys.D)
            deltaX = 1;

        return deltaX * playerController.getPlayer().getSpeed()*10;
    }

    private int getDeltaY(int i) {
        int deltaY = 1;
        if (i == Keys.W)
            deltaY = -1;
        else if (i == Keys.S)
            deltaY = 1;

        return deltaY * playerController.getPlayer().getSpeed()*10;
    }

    public void handlePlayerMovement(int i) {
        playerController.handlePlayerMovement(i);
        int deltaX = getDeltaX(i);
        int deltaY = getDeltaY(i);

        weaponController.moveAllBullets(deltaX, deltaY);
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
