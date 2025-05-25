package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;

public class Bullet {

    private Texture texture = GameAssetManager.getGameAssetManager().getBullet();
    private Sprite sprite = new Sprite(texture);
    private int damage;
    private float x;
    private float y;

    Vector2 direction;

    private int speed = 10;

    public Bullet(float x, float y, Vector2 direction) {
        sprite.setSize(20, 20);
        this.x = x;
        this.y = y;
        this.direction = direction;
        sprite.setPosition(x, y);
    }

    public void update() {
        x += direction.x * speed;
        y -= direction.y * speed;
        sprite.setPosition(x, y);
    }

    public void draw() {
        sprite.draw(Main.getMain().getBatch());
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Vector2 getDirection() {
        return direction;
    }

}
