package com.tilldawn.Control;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Result;
import com.tilldawn.Model.User;
import com.tilldawn.Model.Enum.Avatar;
import com.tilldawn.Model.Enum.Menu;
import com.tilldawn.Model.Enum.Regex;

public class SignUpController {

    public Result signUp(String username, String password, String reEnteredPassword, String securityQuestion,
            String answer) {

        if (username.isEmpty() || password.isEmpty() || reEnteredPassword.isEmpty() || securityQuestion.isEmpty()
                || answer.isEmpty())
            return Result.failure("Please fill in all fields.");

        if (User.isUsernameTaken(username))
            return Result.failure("Username is already taken.");

        if (!password.equals(reEnteredPassword))
            return Result.failure("Passwords do not match.");

        if (!password.matches(Regex.PASSWORD_REGEX.getRegex()))
            return Result.failure(
                    "Password must contain at least one uppercase letter, one digit, and one special character.");

        Image avatar = Avatar.getRandomAvatarImage();

        User user = new User(username, password, securityQuestion, answer, avatar);
        User.addUser(user);
        

        return Result.success("User registered successfully.");
    }

    public Result back() {
        Main.changeMenu(Menu.APP_MENU);
        return Result.success("Back to App Menu.");
    }

}
