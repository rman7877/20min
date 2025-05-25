package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;

public class Bullet {

    private Sprite sprite;
    private int damage;

    private CollisionRect rect;

    Vector2 direction;

    private int speed = 5;

    public Bullet(Sprite sprite, CollisionRect rect, Vector2 direction, int damage) {
        this.sprite = sprite;
        this.rect = rect;
        this.direction = direction;
        this.damage = damage;
    }

    public void update() {
        rect.increaseX(direction.x * speed);
        rect.decreaseY(direction.y * speed);
        sprite.setPosition(rect.getX(), rect.getY());
    }

    public CollisionRect getRect() {
        return rect;
    }

    public void draw() {
        sprite.draw(Main.getMain().getBatch());
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
        return rect.getX();
    }

    public float getY() {
        return rect.getY();
    }

    public Vector2 getDirection() {
        return direction;
    }

}
