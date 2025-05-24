package com.tilldawn.Model.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Model.CollisionRect;

public class Elder extends Enemy{

    public Elder(int hp, Sprite sprite, Animation<Sprite> animation, CollisionRect rect) {
        super(hp, sprite, animation, rect);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
