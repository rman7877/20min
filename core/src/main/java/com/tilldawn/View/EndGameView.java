package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.Control.SignInController;
import com.tilldawn.Control.SignUpController;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.Result;
import com.tilldawn.Model.User;
import com.tilldawn.Model.Enum.Ability;
import com.tilldawn.Model.Enum.Menu;
import com.tilldawn.Model.Enum.SecurityQuestion;

public class EndGameView extends View implements InputProcessor {

    private Stage stage;

    Label resultLabel;
    Label deadOrWin;
    Label usernameLabel;
    Label killsLabel;
    Label scoresLabel;

    private TextButton endGame;

    private Table table;

    public EndGameView(Skin skin) {

        stage = new Stage();
        table = new Table();

        if (Game.getGame() != null) {
            String username = "Guest";
            int survivalTime = Game.getGame().getTime();
            int kills = Game.getGame().getKills();
            int score = kills * survivalTime;

            if (App.getUser() != null) {
                username = App.getUser().getUsername();
                App.getUser().update(score, kills, survivalTime);
            }

            resultLabel = new Label("Game Over", skin);
            deadOrWin = new Label("You " + (Game.getGame().isWon() ? "WON!" : "DIED!"), skin);
            usernameLabel = new Label("Username: " + username, skin);
            killsLabel = new Label("Kills: " + kills, skin);
            scoresLabel = new Label("Score: " + score, skin);
        }
        endGame = new TextButton("End Game", skin);

        handleListeners();

    }

    public void handleListeners() {

        endGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.changeMenu(Menu.MAIN_MENU);
            }
        });

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.clear();
        table.setFillParent(true);

        table.add(resultLabel).padBottom(10).row();
        table.add(deadOrWin).padBottom(10).row();
        table.add(usernameLabel).padBottom(10).row();
        table.add(killsLabel).padBottom(10).row();
        table.add(scoresLabel).padBottom(10).row();
        table.add(endGame).padBottom(10).row();

        stage.addActor(table);

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
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
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

}
