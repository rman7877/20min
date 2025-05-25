package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.tilldawn.Model.Result;
import com.tilldawn.Model.User;
import com.tilldawn.Model.Enum.Ability;
import com.tilldawn.Model.Enum.HeroType;
import com.tilldawn.Model.Enum.Menu;
import com.tilldawn.Model.Enum.SecurityQuestion;

public class TalentView extends View {

    private Stage stage;

    private TextButton backButton;

    private Table table;

    public TalentView(Skin skin) {

        stage = new Stage();
        table = new Table();

        Label heroDescription1 = new Label(HeroType.DASHER.getDescription(), skin);
        Label heroDescription2 = new Label(HeroType.SHANA.getDescription(), skin);
        Label heroDescription3 = new Label(HeroType.DIAMOND.getDescription(), skin);
        Label heroDescription4 = new Label(HeroType.SCARLET.getDescription(), skin);
        Label heroDescription5 = new Label(HeroType.LILITH.getDescription(), skin);

        Label abilityDescription1 = new Label(Ability.AMOCREASE.name() +" : "+ Ability.AMOCREASE.getDescription(), skin);
        Label abilityDescription2 = new Label(Ability.DAMAGER.name() +" : "+ Ability.DAMAGER.getDescription(), skin);
        Label abilityDescription3 = new Label(Ability.PROCREASE.name() +" : "+ Ability.PROCREASE.getDescription(), skin);
        Label abilityDescription4 = new Label(Ability.SPEEDY.name() +" : "+ Ability.SPEEDY.getDescription(), skin);
        Label abilityDescription5 = new Label(Ability.VITALITY.name() +" : "+ Ability.VITALITY.getDescription(), skin);

        table.setFillParent(true);

        stage.addActor(table);

        table.add(new Label("Heroes:", skin)).colspan(5).pad(10).row();
        table.add(heroDescription1).pad(10).row();
        table.add(heroDescription2).pad(10).row();
        table.add(heroDescription3).pad(10).row();
        table.add(heroDescription4).pad(10).row();
        table.add(heroDescription5).pad(10).row();

        table.row();
        table.row();

        table.add(new Label("Abilities:", skin)).colspan(5).pad(10).row();
        table.add(abilityDescription1).pad(10).row();
        table.add(abilityDescription2).pad(10).row();
        table.add(abilityDescription3).pad(10).row();
        table.add(abilityDescription4).pad(10).row();
        table.add(abilityDescription5).pad(10).row();

        backButton = new TextButton("Back", skin);

        handleListeners();

    }

    public void handleListeners() {

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
