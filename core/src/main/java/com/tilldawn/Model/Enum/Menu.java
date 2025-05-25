package com.tilldawn.Model.Enum;

import com.tilldawn.Model.App;
import com.tilldawn.View.AppView;
import com.tilldawn.View.GameView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.PauseMenu;
import com.tilldawn.View.PregameView;
import com.tilldawn.View.ProfileView;
import com.tilldawn.View.ScoreboardView;
import com.tilldawn.View.SignInView;
import com.tilldawn.View.SignUpView;
import com.tilldawn.View.View;

public enum Menu {
    APP_MENU(new AppView(App.getSkin())),
    SIGN_IN_MENU(new SignInView(App.getSkin())),
    SIGN_UP_MENU(new SignUpView(App.getSkin())),
    MAIN_MENU(new MainMenuView(App.getSkin())),
    PROFILE_MENU(new ProfileView(App.getSkin())),
    SCOREBOARD_MENU(new ScoreboardView(App.getSkin())),
    // GAME_MENU(new GameView(App.getSkin())),
    PRE_GAME_MENU(new PregameView(App.getSkin())),
    PAUSE_MENU(new PauseMenu(App.getSkin()));

    private View view;

    Menu(View view) {
        this.view = view;
    }

    public View getView() {
        switch (this) {
            case APP_MENU:
                return new AppView(App.getSkin());
            case SIGN_IN_MENU:
                return new SignInView(App.getSkin());
            case SIGN_UP_MENU:
                return new SignUpView(App.getSkin());
            case MAIN_MENU:
                return new MainMenuView(App.getSkin());
            case PROFILE_MENU:
                return new ProfileView(App.getSkin());
            case SCOREBOARD_MENU:
                return new ScoreboardView(App.getSkin());
            // case GAME_MENU:
            //     return new GameView(App.getSkin());
            case PRE_GAME_MENU:
                return new PregameView(App.getSkin());
            case PAUSE_MENU:
                return new PauseMenu(App.getSkin());
            
            default:
                return null;
        }
        // return view;
    }


}
