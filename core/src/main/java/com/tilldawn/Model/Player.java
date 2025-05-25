package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tilldawn.Model.Enum.HeroType;

public class Player {

    private Sprite sprite;
    private int health;
    private int speed;
    private CollisionRect rect;
    private int level;

    private HeroType heroType;

    private float time = 0;

    private boolean isPlayerIdle = true;
    private boolean isMoving = false;

    public Player(HeroType heroType) {
        this.heroType = heroType;
        sprite = heroType.getFirstSpriteIdle();
        this.health = heroType.getHealth();
        this.speed = heroType.getSpeed();
        this.level = 0;

        sprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        sprite.setSize(50, 50);
        rect = new CollisionRect((float) Gdx.graphics.getWidth() / 2,
                (float) Gdx.graphics.getHeight() / 2, sprite.getWidth(), sprite.getHeight());

    }

    public HeroType getHeroType() {
        return heroType;
    }

    public void setSprite(Sprite playerSprite) {
        this.sprite = playerSprite;
    }

    public void setX(float x) {
        rect.setX(x);
    }

    public void setY(float y) {
        rect.setY(y);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setRect(CollisionRect rect) {
        this.rect = rect;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPlayerIdle(boolean isPlayerIdle) {
        this.isPlayerIdle = isPlayerIdle;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public Sprite getSprite() {
        return sprite;
    }
    public int getLevel() {
        return level;
    }

    public float getX() {
        return rect.getX();
    }

    public float getY() {
        return rect.getY();
    }

    public int getHealth() {
        return health;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public float getTime() {
        return time;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public boolean isMoving() {
        return isMoving;
    }

}
