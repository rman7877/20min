package com.tilldawn.Model;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Seed {

    CollisionRect rect;
    Sprite sprite;

    public Seed(Sprite sprite, CollisionRect rect) {
        this.sprite = sprite;
        this.rect = rect;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public Sprite getSprite() {
        return sprite;
    }

}
