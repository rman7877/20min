package com.tilldawn.Model.Enemies;

import static com.tilldawn.Model.Enemies.Enemy.speed;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.Game;
import com.badlogic.gdx.utils.Timer;

public class Elder extends Enemy {

    private static final float dashSpeedRatio = 500f; // Speed ratio for dashing

    public Elder(Sprite sprite, Animation<Sprite> animation, CollisionRect rect) {
        super(400, sprite, animation, rect);
    }

    public void dash() {
        if (time > 5) {


            float playerX = Game.getGame().getPlayer().getX();
            float playerY = Game.getGame().getPlayer().getY();

            float deltaX;
            float deltaY;

            if (playerX > getRect().getX()) {
                deltaX = speed * dashSpeedRatio;
            } else {
                deltaX = -speed * dashSpeedRatio;
            }

            if (playerY > getRect().getY()) {
                deltaY = speed * dashSpeedRatio;
            } else {
                deltaY = -speed * dashSpeedRatio;
            }

            movePosition(deltaX, deltaY);

            time = 0;

        }
    }

}
