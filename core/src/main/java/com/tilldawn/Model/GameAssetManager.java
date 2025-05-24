package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {

    private static GameAssetManager gameAssetManager;
    private Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

    private final Texture bulletTexture = new Texture("assets\\bullet.png");

    private final String treePath = "assets/Sprite/T/T_TreeMonster_0.png";
    private final int treeFrameCount = 3;

    private final String backgroundPath = "assets/background.png";

    public static GameAssetManager getGameAssetManager() {
        if (gameAssetManager == null) {
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public Texture getBullet() {
        return bulletTexture;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Animation<Sprite> getTreeAnimation() {
        return getAnimationByFirstSprite(treePath, treeFrameCount);
    }

    public Sprite getTreeSprite() {
        return getSpriteByPath(treePath);
    }

    public Sprite getBackgroundSprite() {
        return getSpriteByPath(backgroundPath);
    }

    public Sprite getSpriteByPath(String path) {
        return new Sprite(new Texture(path));
    }

    public Animation<Sprite> getAnimationByFirstSprite(String path, int frameCount) {
        Sprite[] frames = new Sprite[frameCount];

        // حذف کامل شماره و پسوند از انتهای فایل
        int lastUnderscore = path.lastIndexOf('_');
        int dotPng = path.lastIndexOf(".png");

        if (lastUnderscore == -1 || dotPng == -1 || lastUnderscore > dotPng) {
            throw new IllegalArgumentException("Invalid sprite path format: " + path);
        }

        String basePath = path.substring(0, lastUnderscore); // تا قبل از _
        String extension = path.substring(dotPng); // .png

        for (int i = 0; i < frameCount; i++) {
            String framePath = basePath + "_" + i + extension;
            Texture texture = new Texture(Gdx.files.internal(framePath));
            frames[i] = new Sprite(texture);
        }

        return new Animation<Sprite>(0.1f, frames);
    }

}
