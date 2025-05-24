package com.tilldawn.Model.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Model.CollisionRect;

public abstract class Enemy {

    private int hp;
    private Sprite sprite;
    private CollisionRect rect;
    private Animation<Sprite> animation;

    private float time = 0;

    public Enemy(int hp, Sprite sprite, Animation<Sprite> animation, CollisionRect rect) {
        this.hp = hp;
        this.sprite = sprite;
        this.animation = animation;
        this.rect = rect;
    }

    public Enemy(Sprite sprite, Animation<Sprite> animation, CollisionRect rect) {
        this(-1, sprite, animation, rect);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public void setRect(CollisionRect rect) {
        this.rect = rect;
    }

    public Animation<Sprite> getAnimation() {
        return animation;
    }

    public void setAnimation(Animation<Sprite> animation) {
        this.animation = animation;
    }

    public abstract void update();
}
