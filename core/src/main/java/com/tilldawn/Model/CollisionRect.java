package com.tilldawn.Model;

public class CollisionRect {

    private float x;
    private float y;
    private float width;
    private float height;

    public CollisionRect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(float dx, float dy) {
        this.x = dx;
        this.y = dy;
    }

    public boolean intersects(CollisionRect other) {
        return this.x < other.x + other.width &&
                this.x + this.width > other.x &&
                this.y < other.y + other.height &&
                this.y + this.height > other.y;
    }

}
