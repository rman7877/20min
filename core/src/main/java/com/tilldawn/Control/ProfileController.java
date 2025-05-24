package com.tilldawn.Control;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Result;
import com.tilldawn.Model.User;
import com.tilldawn.Model.Enum.Avatar;
import com.tilldawn.Model.Enum.Menu;
import com.tilldawn.Model.Enum.Regex;

public class ProfileController {

    public Result changeUsername(String newUsername) {
        if (newUsername.isEmpty())
            return Result.failure("Please fill in the new username.");

        if(User.isUsernameTaken(newUsername))
            return Result.failure("Username already taken.");

        User user = App.getUser();
        user.setUsername(newUsername);

        return Result.success("Username changed successfully.");
    }

    public Result setSelectedAvatar(Avatar avatar) {
        if (avatar == null)
            return Result.failure("Please select an avatar.");

        User user = App.getUser();
        user.setAvatar(avatar.getImage());

        return Result.success("Avatar changed successfully.");
    }

    public Result setSelectedAvatar(Image image)
    {
        if (image == null)
            return Result.failure("Please select an avatar.");

        User user = App.getUser();
        user.setAvatar(image);

        return Result.success("Avatar changed successfully.");
    }

    public Result changePassword(String oldPassword, String newPassword, String reEnteredPassword) {
        
        if (oldPassword.isEmpty() || newPassword.isEmpty() || reEnteredPassword.isEmpty())
            return Result.failure("Please fill in all fields.");

        User user = App.getUser();
        if (!user.getPassword().equals(oldPassword))
            return Result.failure("Incorrect old password.");

        if (!newPassword.equals(reEnteredPassword))
            return Result.failure("New passwords do not match.");

        if(!newPassword.matches(Regex.PASSWORD_REGEX.getRegex()))
            return Result.failure("New password does not meet the requirements.");

        user.setPassword(newPassword);

        return Result.success("Password changed successfully.");
    }


    public Result deleteAccount() {
        User user = App.getUser();
        App.setUser(null);

        User.removeUser(user);
        // Main.changeMenu(Menu.APP_MENU);

        return Result.success("Account deleted successfully.");
    }

}
