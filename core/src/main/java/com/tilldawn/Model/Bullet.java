package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet {

    private Texture texture = GameAssetManager.getGameAssetManager().getBullet();
    private Sprite sprite = new Sprite(texture);
    private int damage;
    private int x;
    private int y;

    private int speed = 10;

    public Bullet(int x, int y) {
        sprite.setSize(20, 20);
        this.x = x;
        this.y = y;
        sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - sprite.getHeight() / 2);// TODO:check
    }

    public Texture getTexture() {
        return texture;
    }

    public int getSpeed() {
        return speed;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getDamage() {
        return damage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
