package com.tilldawn.Model.Enum;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public enum WeaponType {
    REVOLVER(20, 1, 1, 6, "assets\\Sprite\\RevolverStill\\RevolverStill.png"),
    SHOTGUN(10, 4, 1, 2, "assets/Sprite/T/T_Shotgun_SS_0.png"),
    SMG_DUAL(8, 1, 2, 24, "assets\\Sprite\\SMGStill\\SMGStill.png");

    private int damage;
    private int projectile;
    private int reloadTime;
    private int maxAmmo;
    private String path;

    WeaponType(int damage, int projectile, int reloadTime, int maxAmmo, String path) {
        this.damage = damage;
        this.projectile = projectile;
        this.reloadTime = reloadTime;
        this.maxAmmo = maxAmmo;
        this.path = path;
    }

    public int getProjectile() {
        return projectile;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public int getDamage() {
        return damage;
    }

    public Sprite getSprite() {
        return new Sprite(new Texture(path));
    }

    public static String[] getAllWeaponType() {
        String[] weaponTypes = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            weaponTypes[i] = values()[i].name();
        }
        return weaponTypes;
    }

}
