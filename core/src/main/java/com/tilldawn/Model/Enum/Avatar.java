package com.tilldawn.Model.Enum;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public enum Avatar {
    AVATAR1("assets/avatars/1.jpg"),
    AVATAR2("assets/avatars/2.png"),
    AVATAR3("assets/avatars/3.png"),
    AVATAR4("assets/avatars/4.png"),
    AVATAR5("assets/avatars/5.png"),
    AVATAR6("assets/avatars/6.jpg"),
    AVATAR7("assets/avatars/7.jpg"),
    AVATAR8("assets/avatars/8.jpg"),
    AVATAR9("assets/avatars/9.png"),
    AVATAR10("assets/avatars/10.png");

    private String path;

    Avatar(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static Avatar getRandomAvatar() {
        int randomIndex = (int) (Math.random() * values().length);
        return values()[randomIndex];
    }

    public static Image getRandomAvatarImage() {
        Avatar randomAvatar = getRandomAvatar();
        Texture texture = new Texture(randomAvatar.getPath());
        Image image = new Image(texture);
        return image;
    }

    public Image getImage() {
        return new Image(new Texture(path));
    }
}
