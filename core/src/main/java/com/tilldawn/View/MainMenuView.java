package com.tilldawn.View;

import java.lang.reflect.Field;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Result;
import com.tilldawn.Model.Enum.Avatar;
import com.tilldawn.Model.Enum.Menu;

public class MainMenuView extends View {

    private Stage stage;

    private TextButton settingsButton;
    private TextButton profileButton;
    private TextButton preGameButton;
    private TextButton scoreboardButton;
    private TextButton talentButton;
    private TextButton resumeSavedButton;
    private TextButton logoutButton;

    private Table table;

    private Image avatar;
    private Label usernameLabel;

    private final MainMenuController controller;

    public MainMenuView(Skin skin) {

        controller = new MainMenuController();

        stage = new Stage();

        settingsButton = new TextButton("Settings", skin);
        profileButton = new TextButton("Profile", skin);
        preGameButton = new TextButton("Pre Game", skin);
        scoreboardButton = new TextButton("Scoreboard", skin);
        talentButton = new TextButton("Talent", skin);
        resumeSavedButton = new TextButton("Resume Saved Game", skin);
        logoutButton = new TextButton("Logout", skin);

        if (App.isGuest()) {
            avatar = Avatar.AVATAR10.getImage();
            usernameLabel = new Label("Guest", skin);
        } else if (App.getUser() != null) {
            avatar = App.getUser().getAvatar();
            usernameLabel = new Label(App.getUser().getUsername(), skin);
        }

        table = new Table();

        handleListeners();

    }

    public void handleListeners() {
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // controller.settings();
            }
        });

        profileButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.profile();
                if (!result.isSuccessful())
                    App.showDialog(result.getMessage(), stage);
            }
        });

        preGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.changeMenu(Menu.PRE_GAME_MENU);
            }
        });

        scoreboardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.changeMenu(Menu.SCOREBOARD_MENU);
            }
        });

        talentButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // controller.talent();
            }
        });

        resumeSavedButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // controller.resumeSavedGame();
            }
        });

        logoutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.logout();
            }
        });
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.clear();

        Table rootTable = new Table();
        rootTable.setFillParent(true);

        Table leftTable = new Table();
        leftTable.add(avatar).width(300).height(300).expand().center().padBottom(50);
        leftTable.row();
        leftTable.add(usernameLabel).expand().center().padBottom(50);

        Table rightTable = new Table();
        rightTable.add(settingsButton).padBottom(20);
        rightTable.row();
        rightTable.add(profileButton).padBottom(20);
        rightTable.row();
        rightTable.add(preGameButton).padBottom(20);
        rightTable.row();
        rightTable.add(scoreboardButton).padBottom(20);
        rightTable.row();
        rightTable.add(talentButton).padBottom(20);
        rightTable.row();
        rightTable.add(resumeSavedButton).padBottom(20);
        rightTable.row();
        rightTable.add(logoutButton).padBottom(20);

        rootTable.add(leftTable).expandY().left().padRight(50);
        rootTable.add(rightTable).expandY().right().padLeft(50);

        stage.addActor(rootTable);

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

}
