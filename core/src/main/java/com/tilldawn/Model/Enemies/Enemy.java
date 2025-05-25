package com.tilldawn.Model.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.Game;

public abstract class Enemy {

    protected static final float speed = 0.25f;

    private int hp;
    private Sprite sprite;
    private CollisionRect rect;
    private Animation<Sprite> animation;

    protected float time = 0;

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

    public void update(float delta) {

        time += delta;

        float playerX = Game.getGame().getPlayer().getX();
        float playerY = Game.getGame().getPlayer().getY();

        float deltaX;
        float deltaY;

        if (playerX > getRect().getX()) {
            deltaX = speed;
        } else {
            deltaX = -speed;
        }

        if (playerY > getRect().getY()) {
            deltaY = speed;
        } else {
            deltaY = -speed;
        }

        movePosition(deltaX, deltaY);
    }

    public void movePosition(float deltaX, float deltaY) {
        getSprite().translate(deltaX, deltaY);
        getRect().setX(getRect().getX() + deltaX);
        getRect().setY(getRect().getY() + deltaY);
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            Game.getGame().getWorld().removeEnemy(this);
            Game.getGame().getController().getWorldController().dropSeed(getRect().getX(), getRect().getY());
            Game.getGame().increaseKills(1);
        }
    }

}
