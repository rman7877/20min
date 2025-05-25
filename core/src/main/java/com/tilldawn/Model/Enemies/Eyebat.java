package com.tilldawn.Model.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Model.Bullet;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;

public class Eyebat extends Enemy {

    public Eyebat(Sprite sprite, Animation<Sprite> animation, CollisionRect rect) {
        super(50, sprite, animation, rect);
        // TODO Auto-generated constructor stub
    }

    private float getAngle() {
        float PlayerX = Game.getGame().getPlayer().getRect().getX();
        float PlayerY = Game.getGame().getPlayer().getRect().getY();

        float angle = (float) Math.atan2(PlayerY - getRect().getY(),
                PlayerX - getRect().getX());
        return angle;

    }

    public void throwBullet() {
        Sprite bulletSprite = GameAssetManager.getGameAssetManager().getEyebatBulletSprite();
        bulletSprite.setPosition(getRect().getX() + getRect().getWidth() / 2,
                getRect().getY() + getRect().getHeight() / 2);

        bulletSprite.setSize(20, 20);

        CollisionRect bulletRect = new CollisionRect(bulletSprite.getX(), bulletSprite.getY(), bulletSprite.getWidth(),
                bulletSprite.getHeight());

        // float angle = getAngle();
        // Vector2 direction = new Vector2(MathUtils.cos(angle),
        // MathUtils.sin(angle)).nor();

        Vector2 start = new Vector2(bulletSprite.getX(), -bulletSprite.getY());
        Vector2 player = new Vector2(Game.getGame().getPlayer().getX(),
                -Game.getGame().getPlayer().getY());

        Vector2 direction = player.sub(start).nor(); // Normalize the direction vector

        // Game.getGame().getController().angle = MathUtils.radiansToDegrees * angle;
        Game.getGame().getController().bulletX = bulletSprite.getX();
        Game.getGame().getController().bulletY = bulletSprite.getY();

        Game.getGame().getWorld().addEyebatBullet(new Bullet(bulletSprite, bulletRect, direction, 1));
    }

}
