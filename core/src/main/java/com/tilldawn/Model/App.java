package com.tilldawn.Model;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class App {

    private static User user;
    private static boolean isGuest = false;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        App.user = user;
        isGuest = false;
    }

    public static void setIsGuest(boolean isGuest) {
        App.isGuest = isGuest;
        if (isGuest)
            user = null;
    }

    public static boolean isGuest() {
        return isGuest;
    }



    public static void showDialog(String message, Runnable onClose, Stage stage) {
        Dialog dialog = new Dialog("", App.getSkin()) {
            @Override
            protected void result(Object object) {
                if ((boolean) object) {
                    onClose.run();
                }
            }
        };
        dialog.text(message);
        dialog.button("OK", true).getButtonTable().getCells().first().size(200, 100);
        dialog.show(stage);
    }

    public static void showDialog(String message, Stage stage) {
        Dialog dialog = new Dialog("", App.getSkin());
        dialog.text(message);
        dialog.button("OK", true).getButtonTable().getCells().first().size(200, 100);
        dialog.show(stage);
    }

    public static Skin getSkin() {
        return GameAssetManager.getGameAssetManager().getSkin();
    }

}
