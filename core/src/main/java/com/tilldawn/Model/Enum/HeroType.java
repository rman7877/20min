package com.tilldawn.Model.Enum;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public enum HeroType {
    SHANA(4, 4,
            new String[] { "assets/Heros/Shana/idle/1.png", "assets/Heros/Shana/idle/2.png",
                    "assets/Heros/Shana/idle/3.png",
                    "assets/Heros/Shana/idle/4.png", "assets/Heros/Shana/idle/5.png", "assets/Heros/Shana/idle/6.png" },
            new String[] { "assets/Heros/Shana/run/1.png", "assets/Heros/Shana/run/2.png",
                    "assets/Heros/Shana/run/3.png",
                    "assets/Heros/Shana/run/4.png" }),
    DIAMOND(7, 10, new String[] { "assets/Heros/Diamond/idle/1.png", "assets/Heros/Diamond/idle/2.png",
            "assets/Heros/Diamond/idle/3.png",
            "assets/Heros/Diamond/idle/4.png", "assets/Heros/Diamond/idle/5.png", "assets/Heros/Diamond/idle/6.png" },
            new String[] { "assets/Heros/Diamond/run/1.png", "assets/Heros/Diamond/run/2.png",
                    "assets/Heros/Diamond/run/3.png",
                    "assets/Heros/Diamond/run/4.png" }),
    SCARLET(3, 5, new String[] { "assets/Heros/Scarlet/idle/1.png", "assets/Heros/Scarlet/idle/2.png",
            "assets/Heros/Scarlet/idle/3.png",
            "assets/Heros/Scarlet/idle/4.png", "assets/Heros/Scarlet/idle/5.png", "assets/Heros/Scarlet/idle/6.png" },
            new String[] { "assets/Heros/Scarlet/run/1.png", "assets/Heros/Scarlet/run/2.png",
                    "assets/Heros/Scarlet/run/3.png",
                    "assets/Heros/Scarlet/run/4.png" }),
    LILITH(5, 3, new String[] { "assets/Heros/Lilith/idle/1.png", "assets/Heros/Lilith/idle/2.png",
            "assets/Heros/Lilith/idle/3.png",
            "assets/Heros/Lilith/idle/4.png", "assets/Heros/Lilith/idle/5.png", "assets/Heros/Lilith/idle/6.png" },
            new String[] { "assets/Heros/Lilith/run/1.png", "assets/Heros/Lilith/run/2.png",
                    "assets/Heros/Lilith/run/3.png",
                    "assets/Heros/Lilith/run/4.png" }),
    DASHER(2, 10, new String[] { "assets/Heros/Dasher/idle/1.png", "assets/Heros/Dasher/idle/2.png",
            "assets/Heros/Dasher/idle/3.png",
            "assets/Heros/Dasher/idle/4.png", "assets/Heros/Dasher/idle/5.png", "assets/Heros/Dasher/idle/6.png" },
            new String[] { "assets/Heros/Dasher/run/1.png", "assets/Heros/Dasher/run/2.png",
                    "assets/Heros/Dasher/run/3.png",
                    "assets/Heros/Dasher/run/4.png" });

    private int health;
    private int speed;
    private String[] idlePath;
    private String[] runPath;

    HeroType(int health, int speed, String[] idlePath, String[] runPath) {
        this.health = health;
        this.speed = speed;
        this.idlePath = idlePath;
        this.runPath = runPath;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public Sprite getFirstSpriteIdle() {
        return new Sprite(new Texture(idlePath[0]));
    }

    public Animation<Sprite> getIdleAnimation() {
        Sprite[] idleTextures = new Sprite[idlePath.length];
        for (int i = 0; i < idlePath.length; i++) {
            idleTextures[i] = new Sprite(new Texture(idlePath[i]));
        }
        return new Animation<Sprite>(0.1f, idleTextures);
    }

    public Animation<Sprite> getRunRightAnimation() {
        Sprite[] runSprites = new Sprite[runPath.length];
        for (int i = 0; i < runPath.length; i++) {
            Texture texture = new Texture(runPath[i]);
            runSprites[i] = new Sprite(texture);
        }
        return new Animation<Sprite>(0.1f, runSprites);
    }

    public Animation<Sprite> getRunLeftAnimation() {
        Sprite[] runSprites = new Sprite[runPath.length];
        for (int i = 0; i < runPath.length; i++) {
            Texture texture = new Texture(runPath[i]);
            Sprite sprite = new Sprite(texture);
            sprite.flip(true, false); 
            runSprites[i] = sprite;
        }
        return new Animation<Sprite>(0.1f, runSprites);
    }

    public static String[] getAllHeroTypes() {
        String[] heroTypes = new String[HeroType.values().length];
        for (int i = 0; i < HeroType.values().length; i++) {
            heroTypes[i] = HeroType.values()[i].name();
        }
        return heroTypes;
    }

}
