package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Result;
import com.tilldawn.Model.Enum.Menu;

public class MainMenuController {


    public Result profile()
    {
        if (App.getUser() == null)
            return Result.failure("User not logged in.");

        Main.changeMenu(Menu.PROFILE_MENU);
        return Result.success("Profile menu opened successfully.");
    }

    public Result logout()
    {
        App.setUser(null);
        Main.changeMenu(Menu.APP_MENU);
        return Result.success("User logged out successfully.");
    }
}
