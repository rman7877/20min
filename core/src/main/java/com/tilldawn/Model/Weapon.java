package com.tilldawn.Model;

import javax.swing.Spring;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Model.Enum.WeaponType;

public class Weapon {

    private final Sprite sprite;

    private int ammo;
    private WeaponType type;

    public Weapon(WeaponType type) {
        this.type = type;
        this.ammo = type.getMaxAmmo();
        this.sprite = type.getSprite();
        sprite.setPosition((float) Gdx.graphics.getWidth() / 2-10, (float) Gdx.graphics.getHeight() / 2-10 );
        sprite.setSize(40, 40);
    }

    public WeaponType getType() {
        return type;
    }

    public Sprite getSmgSprite() {
        return sprite;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

}
