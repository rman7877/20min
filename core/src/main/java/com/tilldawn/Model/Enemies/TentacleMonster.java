package com.tilldawn.Model.Enemies;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.Game;

public class TentacleMonster extends Enemy {

    private static final float speed = 0.25f;

    public TentacleMonster(Sprite sprite, Animation<Sprite> animation, CollisionRect rect) {
        super(25, sprite, animation, rect);
    }

    // @Override
    // public void update() {
    //     float playerX = Game.getGame().getPlayer().getX();
    //     float playerY = Game.getGame().getPlayer().getY();

    //     float deltaX;
    //     float deltaY;

    //     if(playerX > getRect().getX()) {
    //         deltaX = speed;
    //     } else {
    //         deltaX = -speed;
    //     }

    //     if(playerY > getRect().getY()) {
    //         deltaY = speed;
    //     } else {
    //         deltaY = -speed;
    //     }

    //     movePosition(deltaX, deltaY);
    // }

    // public void movePosition(float deltaX, float deltaY) {
    //     getSprite().translate(deltaX, deltaY);
    //     getRect().setX(getRect().getX() + deltaX);
    //     getRect().setY(getRect().getY() + deltaY);
    // }

    // public void setPosition(float x, float y) {
    //     getSprite().setPosition(x, y);
    //     getRect().setX(x);
    //     getRect().setY(y);
    // }

}
