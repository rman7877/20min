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

public class PauseMenu extends View implements InputProcessor {

    private Stage stage;

    private TextButton resumeButton;
    private TextButton saveButton;
    private TextButton showCheatCodeButton;
    private TextButton showAbilitiesButton;
    private TextButton giveUpButton;
    private TextButton blacAndWhiteButton;
    private TextButton saveAndExitButton;

    private Table table;
    // private boolean showChooseAbilitysDialog = false;

    public PauseMenu(Skin skin) {

        // controller = new SignInController();
        // controller.setView(this);

        stage = new Stage();
        table = new Table();

        resumeButton = new TextButton("Resume", skin);
        saveButton = new TextButton("Save", skin);
        showCheatCodeButton = new TextButton("Cheat Codes", skin);
        showAbilitiesButton = new TextButton("Show Abilities", skin);
        giveUpButton = new TextButton("Give Up", skin);
        blacAndWhiteButton = new TextButton("Black and White", skin);
        saveAndExitButton = new TextButton("Save and Exit", skin);

        handleListeners();

    }

    public void showAllAbilitiesDialog() {

        Skin skin = App.getSkin();

        Dialog abilitiesDialog = new Dialog("Abilities", skin);
        Table contentTable = abilitiesDialog.getContentTable();

        // Label label = new Label("Abilities:", skin);
        // contentTable.add(label).padBottom(10).row();

        for (Ability ability : Game.getGame().getPlayer().getAbilities()) {
            Label abilityLabel = new Label(ability.name(), skin);
            contentTable.add(abilityLabel).padBottom(5).row();
        }

        abilitiesDialog.button("Close", skin);

        abilitiesDialog.show(stage);

    }

    public void handleListeners() {

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(GameView.getGameView());
                Game.getGame().resumeGame();
            }
        });

        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(GameView.getGameView());
                Game.getGame().resumeGame();
            }
        });

        showCheatCodeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Game.getGame().showCheatCodes();
            }
        });

        showAbilitiesButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showAllAbilitiesDialog();
            }
        });

        giveUpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Game.getGame().giveUp();
                Main.changeMenu(Menu.MAIN_MENU);
            }
        });

        blacAndWhiteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Game.getGame().toggleBlackAndWhite();
            }
        });

        saveAndExitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Game.getGame().saveAndExit();
                Main.changeMenu(Menu.MAIN_MENU);
            }
        });

    }


    public void showChooseAbilityDialog() {

        ScreenUtils.clear(0, 0, 0, 1);

        Skin skin = App.getSkin();

        Dialog chooseAbilityDialog = new Dialog("", skin);
        Table contentTable = chooseAbilityDialog.getContentTable();

        Label label = new Label("Choose an ability:", skin);
        contentTable.add(label).padBottom(10).row();

        SelectBox<Ability> abilitySelectBox = new SelectBox<>(skin);
        abilitySelectBox.setItems(Ability.values());
        Ability[] randomAbilities = Ability.values();
        java.util.Collections.shuffle(java.util.Arrays.asList(randomAbilities));
        abilitySelectBox.setItems(randomAbilities[0], randomAbilities[1], randomAbilities[2]);
        contentTable.add(abilitySelectBox).width(300).padBottom(20).row();

        TextButton chooseButton = new TextButton("Choose", skin);

        chooseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Ability selectedAbility = abilitySelectBox.getSelected();
                if (selectedAbility != null) {
                    selectedAbility.setAbility(Game.getGame().getPlayer(),
                            Game.getGame().getController().getWeaponController().getWeapon());
                    chooseAbilityDialog.hide();
                }
            }
        });

        chooseAbilityDialog.button(chooseButton);

        chooseAbilityDialog.show(stage);

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        

        table.clear();
        table.setFillParent(true);
        table.add(resumeButton).width(400).height(100).pad(10);
        table.row().padTop(20);
        table.add(saveButton).width(400).height(100).pad(10);
        table.row().padTop(20);
        table.add(showCheatCodeButton).width(400).height(100).pad(10);
        table.row().padTop(20);
        table.add(showAbilitiesButton).width(400).height(100).pad(10);
        table.row().padTop(20);
        table.add(giveUpButton).width(400).height(100).pad(10);
        table.row().padTop(20);
        table.add(blacAndWhiteButton).width(400).height(100).pad(10);
        table.row().padTop(20);
        table.add(saveAndExitButton).width(400).height(100).pad(10);



        stage.addActor(table);

        if (Game.getGame().abilityMenu) {
            showChooseAbilityDialog();
            Game.getGame().abilityMenu = false;
        }

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
