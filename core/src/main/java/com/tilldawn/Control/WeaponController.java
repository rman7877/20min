package com.tilldawn.Control;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;
import com.tilldawn.Model.Bullet;
import com.tilldawn.Model.Weapon;

public class WeaponController {

    private Weapon weapon;
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    public WeaponController(Weapon weapon) {
        this.weapon = weapon;
    }

    public void update() {
        weapon.getSmgSprite().draw(Main.getBatch());
        updateBullets();
    }


    public void handleWeaponReload() {
        if (weapon.getAmmo() < weapon.getType().getMaxAmmo()) {
            weapon.setAmmo(weapon.getType().getMaxAmmo());
        }
    }

    public void handleWeaponRotation(int x, int y) {

        Sprite smgSprite = weapon.getSmgSprite();

        float weaponCenterX = (float) Gdx.graphics.getWidth() / 2;
        float weaponCenterY = (float) Gdx.graphics.getHeight() / 2;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);

        smgSprite.setRotation((float) 3.14-angle*MathUtils.radiansToDegrees);

    }

    public void handleWeaponShoot(int x, int y) {
        if (weapon.getAmmo() > 0) {
            Bullet bullet = new Bullet(x, y);
            bullets.add(bullet);
            weapon.setAmmo(weapon.getAmmo() - 1);
        }
    }

    public void updateBullets() {
        for (Bullet bullet : bullets) {

            bullet.getSprite().draw(Main.getBatch());

            Vector2 direction = new Vector2(Gdx.graphics.getWidth() / 2 - bullet.getX(),
                    Gdx.graphics.getHeight() / 2 - bullet.getY()).nor();

            bullet.getSprite().setPosition(bullet.getSprite().getX() - direction.x * bullet.getSpeed(),
                    bullet.getSprite().getY() + direction.y * bullet.getSpeed());

        }
    }

    public void moveAllBullets(int x, int y) {
        for (Bullet bullet : bullets) {
            bullet.getSprite().setPosition(bullet.getSprite().getX() + x, bullet.getSprite().getY() + y);
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

}
