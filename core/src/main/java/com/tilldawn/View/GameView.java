package com.tilldawn.View;

import java.security.Key;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.GameController;
import com.tilldawn.Control.PlayerController;
import com.tilldawn.Control.WeaponController;
import com.tilldawn.Control.WorldController;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.Weapon;
import com.tilldawn.Model.World;
import com.tilldawn.Model.Enum.GameTime;
import com.tilldawn.Model.Enum.HeroType;
import com.tilldawn.Model.Enum.Menu;
import com.tilldawn.Model.Enum.WeaponType;
import com.tilldawn.Main;

public class GameView extends View implements InputProcessor {

    private Stage stage;
    private GameController controller;

    private OrthographicCamera camera;

    public GameView(Skin skin, GameTime gameTime, HeroType heroType, WeaponType weaponType) {

        stage = new Stage(new ScreenViewport());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Player player = new Player(heroType);
        World world = new World();
        Weapon weapon = new Weapon(weaponType);

        Game game = new Game(gameTime.getTime(), player, weapon, world, camera);
        PlayerController playerController = new PlayerController(player);
        WorldController worldController = new WorldController(playerController, world);
        WeaponController weaponController = new WeaponController(weapon);

        controller = new GameController(game, playerController, worldController, weaponController);

        game.setController(controller);

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.position.set(controller.getPlayerController().getPlayer().getX(),
                controller.getPlayerController().getPlayer().getY(),
                0);
        camera.update();

        // Use the camera's projection matrix:
        Main.getBatch().setProjectionMatrix(camera.combined);

        Main.getBatch().begin();
        controller.updateGame(stage);

        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int i) {
        if (i == Keys.Q) {
            Main.changeMenu(Menu.PRE_GAME_MENU);
            return true;
        }
        if (i == Keys.G) {
            controller.getWorldController().generateEyebat(1);
            return true;
        }
        if (i == Keys.R) {
            controller.getWeaponController().handleWeaponReload();
            return true;
        }
        if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.S) ||
                Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.D)) {
            controller.handlePlayerMovement(i);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        if (i == Keys.W || i == Keys.S || i == Keys.A || i == Keys.D) {
            controller.handlePlayerIdle(i);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        controller.mouseX = i;
        controller.mouseY = i1;
        controller.getWeaponController().handleWeaponShoot(i, i1);
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        // controller.mouseX = i;
        // controller.mouseY = i1;
        controller.getWeaponController().handleWeaponRotation(i, i1);
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

}
