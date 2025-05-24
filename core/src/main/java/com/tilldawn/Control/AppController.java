package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Enum.Menu;

public class AppController {

    public void signIn() {
        Main.changeMenu(Menu.SIGN_IN_MENU);
    }

    public void signUp() {
        Main.changeMenu(Menu.SIGN_UP_MENU);
    }

    public void enterAsGuest() {
        App.setIsGuest(true);
        Main.changeMenu(Menu.MAIN_MENU);
    }

}
