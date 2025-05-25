package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {

    private static GameAssetManager gameAssetManager;
    private Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

    private final String bulletSprite = "assets\\bullet.png";

    private final String treePath = "assets/Sprite/T/T_TreeMonster_0.png";
    private final int treeFrameCount = 3;
    private final Animation<Sprite> treeAnimation = getAnimationByFirstSprite(treePath, treeFrameCount);

    private final String tentacleMonsterPath = "assets/Sprite/TentacleIdle/TentacleIdle_0.png";
    private final int tentacleMonsterFrameCount = 4;
    private final Animation<Sprite> tentacleMonsterAnimation = getAnimationByFirstSprite(tentacleMonsterPath, tentacleMonsterFrameCount);

    private final String eyebatPath = "assets\\Sprite\\T\\T_EyeBat_0.png";
    private final int eyebatFrameCount = 4;
    private final Animation<Sprite> eyebatAnimation = getAnimationByFirstSprite(eyebatPath, eyebatFrameCount);

    private final String eyebatBulletPath = "assets\\Sprite\\T\\T_EyeBat_EM.png";

    private final String elderPath= "assets\\Sprite\\ElderBrain\\ElderBrain.png";

    private final String backgroundPath = "assets/background.png";

    public static GameAssetManager getGameAssetManager() {
        if (gameAssetManager == null) {
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }



    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Animation<Sprite> getTreeAnimation() {
        return treeAnimation;
    }

    public Sprite getTreeSprite() {
        return getSpriteByPath(treePath);
    }

    public Animation<Sprite> getTentacleMonsterAnimation() {
        return tentacleMonsterAnimation;
    }

    public Sprite getTentacleMonsterSprite() {
        return getSpriteByPath(tentacleMonsterPath);
    }

    public Animation<Sprite> getEyebatAnimation() {
        return eyebatAnimation;
    }

    public Sprite getEyebatSprite() {
        return getSpriteByPath(eyebatPath);
    }

    public Sprite getEyebatBulletSprite() {
        return getSpriteByPath(eyebatBulletPath);
    }

    public Sprite getBackgroundSprite() {
        return getSpriteByPath(backgroundPath);
    }

    public Sprite getBulletSprite() {
        return getSpriteByPath(bulletSprite);
    }

    public Sprite getElderSprite() {
        return getSpriteByPath(elderPath);
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
