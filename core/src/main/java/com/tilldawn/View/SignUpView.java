package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.Control.SignUpController;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Result;
import com.tilldawn.Model.Enum.Menu;
import com.tilldawn.Model.Enum.SecurityQuestion;

public class SignUpView extends View {

    private Stage stage;

    private Label usernameLabel;
    private Label passwordLabel;
    private Label reEnteredPasswordLabel;
    private Label sequrityQuestionLabel;

    private TextField usernameField;
    private TextField passwordField;
    private TextField reEnteredPasswordField;
    private TextField sequrityQuestionField;

    private SelectBox<String> securityQuestionSelectBox;

    private TextButton registerButton;
    private TextButton backButton;

    private Table table;

    private final SignUpController controller;

    public SignUpView(Skin skin) {

        controller = new SignUpController();

        stage = new Stage();
        table = new Table();

        usernameLabel = new Label("Username:", skin);
        passwordLabel = new Label("Password:", skin);
        reEnteredPasswordLabel = new Label("Re-enter Password:", skin);
        sequrityQuestionLabel = new Label("Security Question:", skin);

        usernameField = new TextField("", skin);
        passwordField = new TextField("", skin);
        reEnteredPasswordField = new TextField("", skin);
        sequrityQuestionField = new TextField("", skin);

        securityQuestionSelectBox = new SelectBox<>(skin);
        securityQuestionSelectBox.setItems(SecurityQuestion.getAllQuestions());

        registerButton = new TextButton("Register", skin);
        backButton = new TextButton("Back", skin);

        handleListeners();

    }

    public void handleListeners() {
        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                String username = usernameField.getText();
                String password = passwordField.getText();
                String reEnteredPassword = reEnteredPasswordField.getText();
                String securityQuestion = securityQuestionSelectBox.getSelected();
                String answer = sequrityQuestionField.getText();

                Result result = controller.signUp(username, password, reEnteredPassword, securityQuestion, answer);

                App.showDialog(result.getMessage(), () -> {
                    if (result.isSuccessful())
                        Main.changeMenu(Menu.APP_MENU);
                }, stage);

            }
        });
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.back();
            }
        });
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.clear();

        table.setFillParent(true);
        table.add(usernameLabel).pad(10);
        table.add(usernameField).width(400).height(100);
        table.row().padTop(20);
        table.add(passwordLabel).pad(10);
        table.add(passwordField).width(400).height(100);
        table.row().padTop(20);
        table.add(reEnteredPasswordLabel).pad(10);
        table.add(reEnteredPasswordField).width(400).height(100);
        table.row().padTop(20);
        table.add(sequrityQuestionLabel).pad(10);
        table.add(securityQuestionSelectBox).width(1200).height(100);
        table.add(sequrityQuestionField).width(400).height(100);
        table.row().padTop(70);
        table.add(registerButton).colspan(4).width(450);
        table.row().padTop(20);
        table.add(backButton).colspan(4).width(450);

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
