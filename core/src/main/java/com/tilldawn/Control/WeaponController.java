package com.tilldawn.Control;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;
import com.tilldawn.Model.Bullet;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.Weapon;

public class WeaponController {

    private Weapon weapon;
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    public WeaponController(Weapon weapon) {
        this.weapon = weapon;
    }

    public void update(float playerX, float playerY) {
        Sprite weaponSprite = weapon.getSprite();
        weaponSprite.setPosition(playerX + 25, playerY + 5);
        weaponSprite.draw(Main.getBatch());
        updateBullets();
    }

    public void handleWeaponReload() {
        if (weapon.getAmmo() < weapon.getType().getMaxAmmo()) {
            weapon.setAmmo(weapon.getType().getMaxAmmo());
        }
    }

    private float getAngle(int x, int y) {
        float weaponCenterX = (float) Gdx.graphics.getWidth() / 2;
        float weaponCenterY = (float) Gdx.graphics.getHeight() / 2;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);
        return angle;

    }

    public void handleWeaponRotation(int x, int y) {

        Sprite smgSprite = weapon.getSprite();

        float angle = getAngle(x, y);

        smgSprite.setRotation((float) 3.14 - angle * MathUtils.radiansToDegrees);

    }

    public void handleWeaponShoot(int x, int y) {
        if (weapon.getAmmo() > 0) {

            float weaponX = Game.getGame().getWeapon().getX();
            float weaponY = Game.getGame().getWeapon().getY();

            float angle = getAngle(x, y);

            Vector2 direction = new Vector2(MathUtils.cos(angle), MathUtils.sin(angle));

            Bullet bullet = new Bullet(weaponX, weaponY, direction);
            bullets.add(bullet);

            weapon.setAmmo(weapon.getAmmo() - 1);
        }
    }

    public void updateBullets() {
        for (Bullet bullet : bullets) {
            bullet.update();
            bullet.draw();
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

}
