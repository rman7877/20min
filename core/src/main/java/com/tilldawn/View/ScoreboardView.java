package com.tilldawn.View;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.tilldawn.Control.ScoreboardController;
import com.tilldawn.Model.App;
import com.tilldawn.Model.FilePicker;
import com.tilldawn.Model.Result;
import com.tilldawn.Model.User;
import com.tilldawn.Model.Enum.Avatar;
import com.tilldawn.Model.Enum.Menu;
import com.tilldawn.Model.Enum.ScoreboardSorts;
import com.badlogic.gdx.files.FileHandle;
// import com.badlogic.gdx.scenes.scene2d.ui.FileChooser;

public class ScoreboardView extends View {

    private Stage stage;

    private Label rankLabel;
    private Label usernameLabel;
    private Label killsLabel;
    private Label longestSurvivalTime;
    private Label scoreLabel;

    private SelectBox<String> sortingOptionsSelectBox;

    private Table table;

    ArrayList<User> users ;

    TextButton sortButton;
    TextButton backButton;

    private final ScoreboardController controller;

    public ScoreboardView(Skin skin) {

        controller = new ScoreboardController();

        users=controller.sortUsers("SCORE");

        stage = new Stage();

        rankLabel = new Label("Rank", skin);
        usernameLabel = new Label("Username", skin);
        scoreLabel = new Label("Score", skin);
        killsLabel = new Label("Kills", skin);
        longestSurvivalTime = new Label("Longest Survival Time", skin);

        sortingOptionsSelectBox = new SelectBox<>(skin);
        sortingOptionsSelectBox.setItems(ScoreboardSorts.getSorts());

        sortButton = new TextButton("Sort", skin);
        backButton = new TextButton("Back", skin);

        table = new Table();

        handleListeners();

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.clear();

        Table table = new Table();
        table.setFillParent(true);
        table.center();

        table.add(sortingOptionsSelectBox).colspan(2).padBottom(20).padRight(50);
        table.add(sortButton).padBottom(20).padRight(50);
        table.add(backButton).padBottom(20);
        table.row();
        table.add(rankLabel).padBottom(20).padRight(50);
        table.add(usernameLabel).padBottom(20).padRight(50);
        table.add(scoreLabel).padBottom(20).padRight(50);
        table.add(killsLabel).padBottom(20).padRight(50);
        table.add(longestSurvivalTime).padBottom(20).padRight(50);
        
        table.row();
        

        // table.add("test").padBottom(20).padRight(50);
        Skin skin = App.getSkin();
        for (int i=0; i < Math.min(users.size(), 10); i++) {
            User user = users.get(i);
            // skin.getFont("default-font").setColor(1, 0, 0, 1); // Set color to red

            Color color = Color.WHITE;
            if(i==0)
                color = Color.GOLD;
            else if(i==1)
                color = Color.VIOLET;
            else if(i==2)
                color = Color.BROWN;
            else if(App.getUser().equals(user))
                color = Color.GREEN;



                
            Label rank = new Label(String.valueOf(users.indexOf(user) + 1), skin);
            Label username = new Label(user.getUsername(), skin);
            Label score = new Label(String.valueOf(user.getScore()), skin);
            Label kills = new Label(String.valueOf(user.getKills()), skin);
            Label longestSurvivalTime = new Label(String.valueOf(user.getLongestSurvivalTime()), skin);

            rank.setColor(color);
            username.setColor(color);
            score.setColor(color);
            kills.setColor(color);
            longestSurvivalTime.setColor(color);

            table.add(rank).padBottom(20).padRight(50);
            table.add(username).padBottom(20).padRight(50);
            table.add(score).padBottom(20).padRight(50);
            table.add(kills).padBottom(20).padRight(50);
            table.add(longestSurvivalTime).padBottom(20).padRight(50);
            table.row();
        }

        stage.addActor(table);

    }

    public void handleListeners() {



        sortButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                users = controller.sortUsers(sortingOptionsSelectBox.getSelected());
                show();
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
