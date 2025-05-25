package com.tilldawn.View;

import java.security.Key;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
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
import com.tilldawn.Model.Enum.Ability;
import com.tilldawn.Model.Enum.GameTime;
import com.tilldawn.Model.Enum.HeroType;
import com.tilldawn.Model.Enum.Menu;
import com.tilldawn.Model.Enum.WeaponType;
import com.tilldawn.Main;

public class GameView extends View implements InputProcessor {

    private static GameView gameView;

    private Stage stage;
    private GameController controller;

    private OrthographicCamera camera;

    private boolean isPaused = false;

    private ShapeRenderer shapeRenderer;

    private Texture circleTexture;

    public GameView(Skin skin, GameTime gameTime, HeroType heroType, WeaponType weaponType) {

        stage = new Stage(new ScreenViewport());

        shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Player player = new Player(heroType);
        World world = new World();
        Weapon weapon = new Weapon(weaponType);

        Game game = new Game(gameTime.getTime(), player, weapon, world, camera, this);
        PlayerController playerController = new PlayerController(player);
        WorldController worldController = new WorldController(playerController, world);
        WeaponController weaponController = new WeaponController(weapon);

        controller = new GameController(game, playerController, worldController, weaponController);

        game.setController(controller);

        gameView = this;

    }

    public static GameView getGameView() {
        return gameView;
    }

    private void createCircleTexture() {
        int size = 80;
        Pixmap pixmap = new Pixmap(size, size, Pixmap.Format.RGBA8888);

        pixmap.setBlending(Pixmap.Blending.None);
        pixmap.setColor(1, 1, 1, 0.3f);
        pixmap.fillCircle(size / 2, size / 2, size / 2);

        circleTexture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void show() {
        if (circleTexture == null) {
            createCircleTexture();
        }

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this);

        Pixmap original = new Pixmap(Gdx.files.internal("assets\\cursor.png"));
        Pixmap resized = new Pixmap(64, 64, original.getFormat());
        resized.drawPixmap(original,
                0, 0, original.getWidth(), original.getHeight(),
                0, 0, 16, 16);
        Cursor cursor = Gdx.graphics.newCursor(resized, 0, 0);
        Gdx.graphics.setCursor(cursor);
        original.dispose();
        resized.dispose();

    }

    @Override
    public void render(float v) {

        if (isPaused || Game.getGame().abilityMenu || Game.getGame().isEnded()) {
            Game.getGame().pauseGame();
            if (Game.getGame().isEnded()) {
                Main.changeMenu(Menu.END_GAME_MENU);
                return;
                // dispose();
            } else {
                Main.changeMenu(Menu.PAUSE_MENU);
                return;
            }
        }

        ScreenUtils.clear(0, 0, 0, 1);

        camera.position.set(controller.getPlayerController().getPlayer().getX(),
                controller.getPlayerController().getPlayer().getY(),
                0);
        camera.update();

        // Use the camera's projection matrix:
        Main.getBatch().setProjectionMatrix(camera.combined);

        Main.getBatch().begin();
        controller.updateGame(stage, v);

        Main.getBatch().end();

        // Main.getBatch().draw(
        // circleTexture,
        // (Gdx.graphics.getWidth() + Game.getGame().getPlayer().getRect().getWidth()) /
        // 2f - 40,
        // (Gdx.graphics.getHeight() + Game.getGame().getPlayer().getRect().getHeight())
        // / 2f - 40);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 0.3f);
        shapeRenderer.circle(
                (Gdx.graphics.getWidth() + Game.getGame().getPlayer().getRect().getWidth()) /
                        2f,
                (Gdx.graphics.getHeight() + Game.getGame().getPlayer().getRect().getHeight())
                        / 2f,
                40);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

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
        // if (circleTexture != null)
        // circleTexture.dispose();
        stage.dispose();
        shapeRenderer.dispose();
    }

    @Override
    public boolean keyDown(int i) {
        if (i == Keys.E) {
            controller.getWorldController().generateElder();
            return true;
        }
        if (i == Keys.H) {
            Game.getGame().getPlayer().increaseHealth(1);
            return true;
        }
        if (i == Keys.T) {
            Game.getGame().decreaseRemainingTime(60);
        }
        if (i == Keys.L) {
            Player player = Game.getGame().getPlayer();
            player.increaseXp(player.XPNeededForNextLevel() - player.XPGainedForNextLevel());
            return true;
        }
        if (i == Keys.I) {
            Game.getGame().getPlayer().setHealth(10000);
            return true;
        }

        if (i == Keys.Q) {
            Main.changeMenu(Menu.PRE_GAME_MENU);
            return true;
        }
        if (i == Keys.B) {
            controller.getWorldController().generateEyebat();
            return true;
        }
        if (i == Keys.Z) {
            Ability.SPEEDY.setAbility(Game.getGame().getPlayer(),
                    Game.getGame().getController().getWeaponController().getWeapon());
            return true;
        }
        if (i == Keys.X) {
            Ability.AMOCREASE.setAbility(Game.getGame().getPlayer(),
                    Game.getGame().getController().getWeaponController().getWeapon());
            return true;
        }
        if (i == Keys.C) {
            Ability.DAMAGER.setAbility(Game.getGame().getPlayer(),
                    Game.getGame().getController().getWeaponController().getWeapon());
            return true;
        }
        if (i == Keys.V) {
            Ability.PROCREASE.setAbility(Game.getGame().getPlayer(),
                    Game.getGame().getController().getWeaponController().getWeapon());
            return true;
        }
        if (i == Keys.Q) {
            Game.getGame().getWeapon().setAutoReload(!Game.getGame().getWeapon().isAutoReload());
            return true;
        }
        if (i == Keys.F) {
            Ability.VITALITY.setAbility(Game.getGame().getPlayer(),
                    Game.getGame().getController().getWeaponController().getWeapon());
            return true;
        }

        if (i == Keys.U) {
            controller.getWorldController().generateTentacleMonster();
            return true;
        }
        if (i == Keys.R) {
            controller.getWeaponController().handleWeaponReload();
            return true;
        }
        if (i == Keys.P) {
            isPaused = !isPaused;
            if (isPaused) {
                // Gdx.input.setInputProcessor(null); // Disable input processing
                Game.getGame().pauseGame();
            } else {
                // Gdx.input.setInputProcessor(this); // Re-enable input processing
                Game.getGame().resumeGame();
            }

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

    public Stage getStage() {
        return stage;
    }

    public Skin getSkin() {
        return App.getSkin();
    }

    public void setIspaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

}
