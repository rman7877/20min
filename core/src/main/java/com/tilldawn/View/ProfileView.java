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
import com.tilldawn.Model.Enum.Menu;



import com.badlogic.gdx.files.FileHandle;
// import com.badlogic.gdx.scenes.scene2d.ui.FileChooser;

public class ProfileView extends View {

    private Stage stage;

    private TextButton settingsButton;
    private TextButton profileButton;
    private TextButton preGameButton;
    private TextButton scoreboardButton;
    private TextButton talentButton;
    private TextButton resumeSavedButton;
    private TextButton logoutButton;

    private Table table;

    TextButton changeUsernameButton;
    TextButton changePasswordButton;
    TextButton changeAvatarButton;
    TextButton chooseAvatarButton;
    TextButton deleteAccountButton;
    TextButton backButton;

    private final ProfileController controller;

    public ProfileView(Skin skin) {

        controller = new ProfileController();

        stage = new Stage();

        changeUsernameButton = new TextButton("Change Username", skin);
        changePasswordButton = new TextButton("Change Password", skin);
        changeAvatarButton = new TextButton("Change Avatar", skin);
        chooseAvatarButton = new TextButton("Choose Avatar", skin);
        deleteAccountButton = new TextButton("Delete Account", skin);
        backButton = new TextButton("Back", skin);


        table = new Table();

        handleListeners();

    }

    public void showChangeUsernameDialog() {

        Skin skin = App.getSkin();

        Dialog changeUsernameDialog = new Dialog("", skin);
        Table contentTable = changeUsernameDialog.getContentTable();

        Label label = new Label("new Ussername:", skin);
        TextField answer = new TextField("", skin);

        TextButton changeButton = new TextButton("Change Username", skin);
        TextButton cancelButton = new TextButton("Cancel", skin);

        contentTable.add(label).padBottom(10).row();
        contentTable.add(answer).width(300).padBottom(20).row();

        changeUsernameDialog.button(changeButton);
        changeUsernameDialog.button(cancelButton);

        changeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                Result result = controller.changeUsername(answer.getText());

                if (result.isSuccessful()) {
                    App.showDialog(result.getMessage(), () -> {
                        Main.changeMenu(Menu.PROFILE_MENU);
                    }, stage);
                } else {
                    App.showDialog(result.getMessage(), () -> {
                        showChangeUsernameDialog();
                    }, stage);

                }

            }
        });

        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changeUsernameDialog.hide();
            }
        });

        changeUsernameDialog.show(stage);
    }

    public void showChangePasswordDialog() {

        Skin skin = App.getSkin();

        Dialog changePasswordDialog = new Dialog("", skin);
        Table contentTable = changePasswordDialog.getContentTable();

        Label label = new Label("Old Password:", skin);
        TextField oldPasswordField = new TextField("", skin);
        Label newPasswordLabel = new Label("New Password:", skin);
        Label reEnteredPasswordLabel = new Label("Re-enter New Password:", skin);
        TextField newPasswordField = new TextField("", skin);
        TextField reEnteredPasswordField = new TextField("", skin);
        TextButton changeButton = new TextButton("Change Password", skin);
        TextButton cancelButton = new TextButton("Cancel", skin);

        contentTable.add(label).padBottom(10).row();
        contentTable.add(oldPasswordField).width(300).padBottom(20).row();
        contentTable.add(newPasswordLabel).padBottom(10);
        contentTable.add(newPasswordField).width(300).padBottom(20).row();
        contentTable.add(reEnteredPasswordLabel).padBottom(10);
        contentTable.add(reEnteredPasswordField).width(300).padBottom(20).row();

        changePasswordDialog.button(changeButton);
        changePasswordDialog.button(cancelButton);

        changeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                Result result = controller.changePassword(oldPasswordField.getText(), newPasswordField.getText(),
                        reEnteredPasswordField.getText());

                if (result.isSuccessful()) {
                    App.showDialog(result.getMessage(), () -> {
                        Main.changeMenu(Menu.PROFILE_MENU);
                    }, stage);
                } else {
                    App.showDialog(result.getMessage(), () -> {
                        showChangePasswordDialog();
                    }, stage);

                }

            }
        });

        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changePasswordDialog.hide();
            }
        });

        changePasswordDialog.show(stage);

    }

    public void showChangeAvatarDialog() {

        Skin skin = App.getSkin();

        Dialog changeAvatarDialog = new Dialog("", skin);
        Table contentTable = changeAvatarDialog.getContentTable();

        Label label = new Label("Choose Avatar:", skin);

        Image avatar1 = Avatar.AVATAR1.getImage();
        Image avatar2 = Avatar.AVATAR2.getImage();
        Image avatar3 = Avatar.AVATAR3.getImage();
        Image avatar4 = Avatar.AVATAR4.getImage();
        Image avatar5 = Avatar.AVATAR5.getImage();
        Image avatar6 = Avatar.AVATAR6.getImage();
        Image avatar7 = Avatar.AVATAR7.getImage();
        Image avatar8 = Avatar.AVATAR8.getImage();
        Image avatar9 = Avatar.AVATAR9.getImage();
        Image avatar10 = Avatar.AVATAR10.getImage();

        // TextButton changeButton = new TextButton("Change Avatar", skin);
        // TextButton cancelButton = new TextButton("Cancel", skin);

        contentTable.add(label).padBottom(10).colspan(5).center().row();

        contentTable.add(avatar1).width(100).height(100).pad(5);
        contentTable.add(avatar2).width(100).height(100).pad(5);
        contentTable.add(avatar3).width(100).height(100).pad(5);
        contentTable.add(avatar4).width(100).height(100).pad(5);
        contentTable.add(avatar5).width(100).height(100).pad(5).row();

        contentTable.add(avatar6).width(100).height(100).pad(5);
        contentTable.add(avatar7).width(100).height(100).pad(5);
        contentTable.add(avatar8).width(100).height(100).pad(5);
        contentTable.add(avatar9).width(100).height(100).pad(5);
        contentTable.add(avatar10).width(100).height(100).pad(5).row();

        // changeAvatarDialog.button(changeButton);
        // changeAvatarDialog.button(cancelButton);

        avatar1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.setSelectedAvatar(Avatar.AVATAR1);
                App.showDialog(result.getMessage(), stage);
                changeAvatarDialog.hide();
            }
        });

        avatar2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.setSelectedAvatar(Avatar.AVATAR2);
                App.showDialog(result.getMessage(), stage);
                changeAvatarDialog.hide();
            }
        });

        avatar3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.setSelectedAvatar(Avatar.AVATAR3);
                App.showDialog(result.getMessage(), stage);
                changeAvatarDialog.hide();
            }
        });

        avatar4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.setSelectedAvatar(Avatar.AVATAR4);
                App.showDialog(result.getMessage(), stage);
                changeAvatarDialog.hide();
            }
        });

        avatar5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.setSelectedAvatar(Avatar.AVATAR5);
                App.showDialog(result.getMessage(), stage);
                changeAvatarDialog.hide();
            }
        });

        avatar6.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.setSelectedAvatar(Avatar.AVATAR6);
                App.showDialog(result.getMessage(), stage);
                changeAvatarDialog.hide();
            }
        });

        avatar7.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.setSelectedAvatar(Avatar.AVATAR7);
                App.showDialog(result.getMessage(), stage);
                changeAvatarDialog.hide();
            }
        });

        avatar8.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.setSelectedAvatar(Avatar.AVATAR8);
                App.showDialog(result.getMessage(), stage);
                changeAvatarDialog.hide();
            }
        });

        avatar9.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.setSelectedAvatar(Avatar.AVATAR9);
                App.showDialog(result.getMessage(), stage);
                changeAvatarDialog.hide();
            }
        });

        avatar10.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.setSelectedAvatar(Avatar.AVATAR10);
                App.showDialog(result.getMessage(), stage);
                changeAvatarDialog.hide();
            }
        });

        changeAvatarDialog.show(stage);

    }

    public void showChooseAvatarDialog()
    {
        Skin skin = App.getSkin();

        Dialog chooseAvatarDialog = new Dialog("", skin);
        Table contentTable = chooseAvatarDialog.getContentTable();

        String filePath=FilePicker.pickFile();

        if(filePath.endsWith(".jpg") || filePath.endsWith(".png"))
        {
            FileHandle fileHandle = Gdx.files.absolute(filePath);
            Texture texture = new Texture(fileHandle);
            Image image = new Image(texture);

            contentTable.add(image).width(100).height(100).pad(5).row();

            TextButton changeButton = new TextButton("Change Avatar", skin);
            TextButton cancelButton = new TextButton("Cancel", skin);

            chooseAvatarDialog.button(changeButton);
            chooseAvatarDialog.button(cancelButton);

            changeButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Result result = controller.setSelectedAvatar(image);
                    App.showDialog(result.getMessage(), stage);
                    chooseAvatarDialog.hide();
                }
            });

            cancelButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    chooseAvatarDialog.hide();
                }
            });

            chooseAvatarDialog.show(stage);
        }
        else
        {
            App.showDialog("Please select a valid image file.", stage);
        }

    }
    

    public void handleListeners() {
        changeUsernameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showChangeUsernameDialog();
            }
        });

        changePasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showChangePasswordDialog();
            }
        });

        changeAvatarButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showChangeAvatarDialog();
            }
        });

        chooseAvatarButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                showChooseAvatarDialog();
            }
        });

        deleteAccountButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.deleteAccount();
                App.showDialog(result.getMessage(), () -> {
                    if (result.isSuccessful())
                        Main.changeMenu(Menu.APP_MENU);
                }, stage);
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
        table.add(changeUsernameButton).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(changePasswordButton).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(changeAvatarButton).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(chooseAvatarButton).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(deleteAccountButton).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(backButton).fillX().uniformX();

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
