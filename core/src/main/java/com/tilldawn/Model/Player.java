package com.tilldawn.Model;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tilldawn.Main;
import com.tilldawn.Model.Enum.Ability;
import com.tilldawn.Model.Enum.HeroType;
import com.tilldawn.Model.Enum.Menu;

public class Player {

    private Sprite sprite;
    private int health;
    private int maxHealth;
    private int speed;
    private CollisionRect rect;
    private int level;

    private int xp;

    private HeroType heroType;

    private float time = 0;

    private boolean isPlayerIdle = true;
    private boolean isMoving = false;
    private boolean isInvincible = false;

    private ArrayList<Ability> abilities;

    public int lastLevelXp = 0;

    public Player(HeroType heroType) {
        this.heroType = heroType;
        this.maxHealth = heroType.getHealth();
        sprite = heroType.getFirstSpriteIdle();
        this.health = heroType.getHealth();
        this.speed = heroType.getSpeed();
        this.level = 1;
        this.xp = 0;
        abilities = new ArrayList<>();

        sprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        sprite.setSize(50, 50);
        rect = new CollisionRect((float) Gdx.graphics.getWidth() / 2,
                (float) Gdx.graphics.getHeight() / 2, sprite.getWidth(), sprite.getHeight());

    }

    public HeroType getHeroType() {
        return heroType;
    }

    public int XPNeededForNextLevel() {
        // return level * 20;
        return level * 2;
    }

    public int XPGainedForNextLevel() {
        return xp - lastLevelXp;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public void addAbility(Ability ability) {
        if (!abilities.contains(ability)) {
            abilities.add(ability);
        }
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

    public void setLevel(int level) {
        this.level = level;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getXp() {
        return xp;
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean isInvincible) {
        this.isInvincible = isInvincible;
    }

    public void increaseXp(int xp) {
        this.xp += xp;
        if (this.xp >= XPNeededForNextLevel() + lastLevelXp) {
            levelUp();
        }
    }

    public void levelUp() {
        lastLevelXp = XPNeededForNextLevel();
        level++;

        Game.getGame().abilityMenu = true;

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

    public void increaseHealth(int health) {
        this.health += health;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
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

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

}
