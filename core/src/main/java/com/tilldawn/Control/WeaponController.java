package com.tilldawn.Control;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;
import com.tilldawn.Model.Bullet;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Weapon;
import com.tilldawn.Model.Enemies.Enemy;
import com.tilldawn.Model.Enemies.Tree;

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
        if (weapon.getAmmo() < weapon.getMaxAmmo()) {
            weapon.setAmmo(weapon.getMaxAmmo());
            weapon.setReloading(true);
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    weapon.setReloading(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
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
        if (weapon.getAmmo() > 0 && !weapon.isReloading()) {

            float weaponX = Game.getGame().getWeapon().getX();
            float weaponY = Game.getGame().getWeapon().getY();

            float angle = getAngle(x, y);
            Vector2 direction = new Vector2(MathUtils.cos(angle), MathUtils.sin(angle));

            Sprite bulletSprite = GameAssetManager.getGameAssetManager().getBulletSprite();
            bulletSprite.setPosition(weaponX + 25, weaponY + 5);
            bulletSprite.setSize(25, 25);

            CollisionRect bulletRect = new CollisionRect(bulletSprite.getX(), bulletSprite.getY(),
                    bulletSprite.getWidth(), bulletSprite.getHeight());

            Bullet bullet = new Bullet(bulletSprite, bulletRect, direction, weapon.getDamage());
            bullets.add(bullet);

            weapon.decreaseAmmo();
            if (weapon.isAutoReload() && weapon.getAmmo() <= 0) {
                handleWeaponReload();
            }
        }
    }

    public void updateBullets() {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.update();
            bullet.draw();

            Iterator<Enemy> enemyIterator = Game.getGame().getWorld().getEnemies().iterator();
            while (enemyIterator.hasNext()) {

                Enemy enemy = enemyIterator.next();

                if (!(enemy instanceof Tree) && bullet.getRect().intersects(enemy.getRect())) {
                    enemy.takeDamage(bullet.getDamage(),bullet.getDirection());
                    bulletIterator.remove();
                    break;
                }
            }
        }

    }

    public Weapon getWeapon() {
        return weapon;
    }

}
