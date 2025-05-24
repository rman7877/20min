package com.tilldawn.View;

import java.lang.reflect.Field;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Control.ProfileController;
import com.tilldawn.Model.App;
import com.tilldawn.Model.FilePicker;
import com.tilldawn.Model.Result;
import com.tilldawn.Model.Enum.Avatar;
import com.tilldawn.Model.Enum.GameTime;
import com.tilldawn.Model.Enum.HeroType;
import com.tilldawn.Model.Enum.Menu;
import com.tilldawn.Model.Enum.WeaponType;
import com.badlogic.gdx.files.FileHandle;
// import com.badlogic.gdx.scenes.scene2d.ui.FileChooser;

public class PregameView extends View {

    private Stage stage;

    private SelectBox<String> heroSelectBox;
    private SelectBox<String> weaponSelectBox;
    private SelectBox<String> gameTimeSelectBox;

    private TextButton startGameButton;
    private TextButton backButton;

    private Table table;

    // private final ProfileController controller;

    public PregameView(Skin skin) {

        // controller = new ProfileController();

        stage = new Stage();

        heroSelectBox = new SelectBox<>(skin);
        weaponSelectBox = new SelectBox<>(skin);
        gameTimeSelectBox = new SelectBox<>(skin);

        heroSelectBox.setItems(HeroType.getAllHeroTypes());
        weaponSelectBox.setItems(WeaponType.getAllWeaponType());
        gameTimeSelectBox.setItems(GameTime.getAllGameTime());

        startGameButton = new TextButton("Start Game", skin);
        backButton = new TextButton("Back", skin);

        table = new Table();

        handleListeners();

    }

    public void handleListeners() {

        startGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                String selectedHero = heroSelectBox.getSelected();
                String selectedWeapon = weaponSelectBox.getSelected();
                String selectedGameTime = gameTimeSelectBox.getSelected();

                HeroType heroType = HeroType.valueOf(selectedHero);
                WeaponType weaponType = WeaponType.valueOf(selectedWeapon);
                GameTime gameTime = GameTime.getGameTime(selectedGameTime);

                Main.getMain().setScreen(new GameView(App.getSkin(), gameTime, heroType, weaponType));

            }
        });

        backButton.addListener(new ClickListener() {
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

        stage.clear();

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(new Label("Select Hero:", App.getSkin())).padBottom(10);
        table.add(heroSelectBox).padBottom(10).row();
        table.add(new Label("Select Weapon:", App.getSkin())).padBottom(10);
        table.add(weaponSelectBox).padBottom(10).row();
        table.add(new Label("Select Game Time:", App.getSkin())).padBottom(10);
        table.add(gameTimeSelectBox).padBottom(10).row();

        table.add(startGameButton).colspan(2).center().padTop(20);
        table.row();
        table.add(backButton).colspan(2).center().padTop(10);

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

}
