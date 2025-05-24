package com.tilldawn.Control;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Result;
import com.tilldawn.Model.User;
import com.tilldawn.Model.Enum.Menu;
import com.tilldawn.Model.Enum.Regex;
import com.tilldawn.View.SignInView;

public class SignInController {

    private SignInView view;

    public void setView(SignInView view) {
        this.view = view;
    }


    public Result signIn(String username, String password) {
        if (username.isEmpty() || password.isEmpty())
            return Result.failure("Please fill in all fields.");

        User user = User.getUserByUsername(username);
        if (user == null)
            return Result.failure("Username does not exist.");

        if (!user.getPassword().equals(password))
            return Result.failure("Incorrect password.");

        App.setUser(user);

        return Result.success("User logged in successfully.");
    }


    public Result canShowForgetPasswordDialog(String username)
    {
        if (username.isEmpty())
            return Result.failure("Please fill in Username.");

        User user = User.getUserByUsername(username);
        if (user == null)
            return Result.failure("Username does not exist.");

        return Result.success("Forget Password Dialog can be shown.");
    }


    public Result forgetPassword(User user, String answer,String newPassword, String reEnteredPassword) {

        if(answer.isEmpty() || newPassword.isEmpty() || reEnteredPassword.isEmpty())
            return Result.failure("Please fill in all fields.");

        if (!user.getSecurityQuestionAnswer().equals(answer))
            return Result.failure("Incorrect answer.");

        if (!newPassword.equals(reEnteredPassword))
            return Result.failure("Passwords do not match.");

        if (!newPassword.matches(Regex.PASSWORD_REGEX.getRegex()))
            return Result.failure("Password must contain at least one uppercase letter, one digit, and one special character.");

        user.setPassword(newPassword);

        return Result.success("Password changed successfully.");

    }

    public Result back() {
        Main.changeMenu(Menu.APP_MENU);
        return Result.success("Back to App Menu.");
    }

}
