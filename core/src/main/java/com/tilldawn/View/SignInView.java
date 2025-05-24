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
import com.tilldawn.Model.Enum.Menu;
import com.tilldawn.Model.Enum.SecurityQuestion;

public class SignInView extends View {

    private Stage stage;

    private Label usernameLabel;
    private Label passwordLabel;

    private TextField usernameField;
    private TextField passwordField;

    private TextButton forgetPasswordButton;
    private TextButton loginButton;
    private TextButton backButton;

    private Table table;

    private final SignInController controller;

    public SignInView(Skin skin) {

        controller = new SignInController();
        controller.setView(this);

        stage = new Stage();
        table = new Table();

        usernameLabel = new Label("Username:", skin);
        passwordLabel = new Label("Password:", skin);

        usernameField = new TextField("", skin);
        passwordField = new TextField("", skin);

        forgetPasswordButton = new TextButton("Forget Password", skin);
        loginButton = new TextButton("Log In", skin);

        backButton = new TextButton("Back", skin);

        handleListeners();

    }

    public void handleListeners() {

        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                Result result = controller.signIn(username, password);

                App.showDialog(result.getMessage(), () -> {
                    if (result.isSuccessful())
                        Main.changeMenu(Menu.MAIN_MENU);
                }, stage);
            }
        });

        forgetPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                String username = usernameField.getText();
                Result result = controller.canShowForgetPasswordDialog(username);

                if (result.isSuccessful()) {
                    User user = User.getUserByUsername(username);
                    showForgetPasswordDialog(user);
                } else {
                    App.showDialog(result.getMessage(), stage);
                }

            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.back();
            }
        });

    }

    public void showForgetPasswordDialog(User user) {

        Skin skin = App.getSkin();

        Dialog forgotPasswordDialog = new Dialog("", skin);
        Table contentTable = forgotPasswordDialog.getContentTable();

        Label label = new Label(user.getSecurityQuestion(), skin);
        TextField answer = new TextField("", skin);
        Label newPasswordLabel = new Label("New Password:", skin);
        Label reEnteredPasswordLabel = new Label("Re-enter New Password:", skin);
        TextField newPasswordField = new TextField("", skin);
        TextField reEnteredPasswordField = new TextField("", skin);
        TextButton changeButton = new TextButton("Change Password", skin);
        TextButton cancelButton = new TextButton("Cancel", skin);

        contentTable.add(label).padBottom(10).row();
        contentTable.add(answer).width(300).padBottom(20).row();
        contentTable.add(newPasswordLabel).padBottom(10);
        contentTable.add(newPasswordField).width(300).padBottom(20).row();
        contentTable.add(reEnteredPasswordLabel).padBottom(10);
        contentTable.add(reEnteredPasswordField).width(300).padBottom(20).row();

        forgotPasswordDialog.button(changeButton);
        forgotPasswordDialog.button(cancelButton);

        changeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                
                Result result = controller.forgetPassword(user, answer.getText(), newPasswordField.getText(), reEnteredPasswordField.getText());

                if (result.isSuccessful()) {
                    App.showDialog(result.getMessage(), () -> {
                        Main.changeMenu(Menu.SIGN_IN_MENU);
                    }, stage);
                } else {
                    App.showDialog(result.getMessage(), stage);
                    forgotPasswordDialog.hide();
                }

                // forgotPasswordDialog.hide();
            }
        });

        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                forgotPasswordDialog.hide();
            }
        });

        forgotPasswordDialog.show(stage);
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
        table.add(loginButton).colspan(2).width(400).height(100);
        table.row().padTop(20);
        table.add(forgetPasswordButton).colspan(2).width(400).height(100);
        table.row().padTop(20);
        table.add(backButton).colspan(2).width(400).height(100);

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
