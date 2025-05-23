package com.badlogic.game.collision;

import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public abstract class GeometryBase {
    // Base class for geometric shapes
    // This class can be extended to create specific shapes like Circle, Rectangle, etc.

    public GeometryBase() {
        // Constructor
    }

    public abstract float getArea();

    public abstract float getPerimeter();

    public abstract Rectangle getBound();

    public abstract boolean containsPoint(float x, float y);

}
