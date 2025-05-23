package com.badlogic.game.collision;
// package main.java.com.badlogic.game.collision;


import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class GeometryRec extends GeometryBase {
    private float x;
    private float y;
    private float width;
    private float height;

    public GeometryRec(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getArea() {
        return width * height;
    }

    public float getPerimeter() {
        return 2 * (width + height);
    }

    public boolean containsPoint(float pointX, float pointY) {
        return (pointX >= x && pointX <= x + width && pointY >= y && pointY <= y + height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
    public Rectangle getBound() {
        return new com.badlogic.gdx.math.Rectangle(x, y, width, height);
    }

}
