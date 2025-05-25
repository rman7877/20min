package com.tilldawn.Model;

import javax.swing.Spring;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Model.Enum.WeaponType;

public class Weapon {

    private final Sprite sprite;

    private int ammo;
    private int maxAmmo;
    private int damage;
    private int projectile;
    private WeaponType type;

    private boolean autoReload = true;
    private boolean isReloading = false;

    public Weapon(WeaponType type) {
        this.type = type;
        this.damage = type.getDamage();
        this.ammo = type.getMaxAmmo();
        this.maxAmmo = type.getMaxAmmo();
        this.projectile = type.getProjectile();
        this.sprite = type.getSprite();
        sprite.setPosition((float) Gdx.graphics.getWidth() / 2 - 10, (float) Gdx.graphics.getHeight() / 2 - 10);
        sprite.setSize(40, 40);
    }

    public WeaponType getType() {
        return type;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isAutoReload() {
        return autoReload;
    }

    public boolean isReloading() {
        return isReloading;
    }

    public void setReloading(boolean isReloading) {
        this.isReloading = isReloading;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }

    public int getAmmo() {
        return ammo;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getProjectile() {
        return projectile;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public void setProjectile(int projectile) {
        this.projectile = projectile;
    }

    public void decreaseAmmo() {
        if (ammo > 0) {
            ammo--;
        }
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

}
