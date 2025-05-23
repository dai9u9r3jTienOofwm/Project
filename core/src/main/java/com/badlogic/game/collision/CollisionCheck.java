package com.badlogic.game.collision;

public class CollisionCheck {
    private GeometryBase shape1;
    private GeometryBase shape2;

    public CollisionCheck(GeometryBase shape1, GeometryBase shape2) {
        this.shape1 = shape1;
        this.shape2 = shape2;
    }

    public boolean checkCollision() {
        if(shape1.getBound().overlaps(shape2.getBound())) { // Implement collision detection logic here
            // For example, check if the bounding boxes of the shapes intersect
            return true; // Placeholder return value
        }
        return false; // Placeholder return value
    }

    public GeometryBase getShape1() {
        return shape1;
    }

    public GeometryBase getShape2() {
        return shape2;
    }

}
