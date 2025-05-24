package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.Control.AppController;
import com.tilldawn.Model.App;
import com.tilldawn.Model.User;
import com.tilldawn.Model.Enum.Menu;



public class AppView extends View {

    private Stage stage;

    private TextButton signInButton;
    private TextButton signUpButton;
    private TextButton enterAsGuest;
    private TextButton exitButton;

    private Table table;

    private final AppController controller;

    public AppView(Skin skin) {

        controller = new AppController();

        stage = new Stage();
        signInButton = new TextButton("Sign In", skin);
        signUpButton = new TextButton("Sign Up", skin);
        exitButton = new TextButton("Exit", skin);
        enterAsGuest = new TextButton("Enter as Guest", skin);

        table = new Table();

        handleListeners();

    }

    public void handleListeners() {
        signInButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.signIn();
            }
        });

        signUpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.signUp();
            }
        });

        enterAsGuest.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.enterAsGuest();
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.setUser(User.getUserByUsername("admin"));
                Main.changeMenu(Menu.MAIN_MENU);
                // System.exit(0);
            }
        });
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.clear();
        table.setFillParent(true);
        table.center();
        table.add(signInButton).padBottom(50);
        table.row();
        table.add(signUpButton).padBottom(50);
        table.row();
        table.add(enterAsGuest).padBottom(50);
        table.row();
        table.add(exitButton).padBottom(50);

        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        // controller.handleMainMenuButtons();

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

}
