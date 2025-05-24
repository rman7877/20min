package com.tilldawn.Control;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Result;
import com.tilldawn.Model.User;
import com.tilldawn.Model.Enum.Avatar;
import com.tilldawn.Model.Enum.Menu;
import com.tilldawn.Model.Enum.Regex;
import com.tilldawn.Model.Enum.ScoreboardSorts;

public class ScoreboardController {

    public ArrayList<User> sortUsers(String sortBy) {
        ArrayList<User> users = User.getUsers();
        switch (sortBy) {
            case "SCORE":
                users.sort((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()));
                break;
            case "KILL":
                users.sort((u1, u2) -> Integer.compare(u2.getKills(), u1.getKills()));
                break;
            case "TIME":
                users.sort((u1, u2) -> Integer.compare(u2.getLongestSurvivalTime(), u1.getLongestSurvivalTime()));
                break;
            case "USERNAME":
                users.sort((u1, u2) -> u1.getUsername().compareTo(u2.getUsername()));
                break;
            default:
                break;
        }
        return users;
    }

}
