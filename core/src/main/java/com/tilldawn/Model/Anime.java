package com.tilldawn.Model;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Anime {
    private Animation<Sprite> frames;
    private float x;
    private float y;
    private float stateTime = 0f;
    private boolean finished = false;
    
    public Anime(Animation<Sprite> frames, float x, float y) {
        this.frames = frames;
        this.x = x;
        this.y = y;
    }

    public void update(float delta) {
        if (!finished) {
            stateTime += delta;
            if (frames.isAnimationFinished(stateTime)) {
                finished = true;
            }
        }
    }

    public void draw(Batch batch) {
        if (!finished) {
            Sprite sprite = frames.getKeyFrame(stateTime, false);
            batch.draw(sprite, x, y);
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public Animation<Sprite> getFrames() {
        return frames;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
