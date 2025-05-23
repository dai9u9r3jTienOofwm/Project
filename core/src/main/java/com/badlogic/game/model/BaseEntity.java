package com.badlogic.game.model;

import com.badlogic.game.collision.GeometryRec;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public abstract class BaseEntity {
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected GeometryRec bounds;
    protected Texture texture;
    protected boolean alive = true;

    public BaseEntity(Texture texture, float x, float y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.bounds = new GeometryRec(x, y, width, height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
        bounds.setX(x);
    }

    public void setY(float y) {
        this.y = y;
        bounds.setY(y);
    }


    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        bounds.setWidth(width);
    }

    public void setHeight(float height) {
        this.height = height;
        bounds.setHeight(height);
    }

    public GeometryRec getBounds() {
        if (bounds == null) {
            bounds = new GeometryRec(x, y, width, height);
        }
        return bounds;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public abstract void update(float deltaTime);

    public abstract void render(SpriteBatch batch);
    // Override in subclasses to render the entity

    public abstract boolean isAlive();

    public void onDestroy() {
        // Override in subclasses to handle destruction
    }

    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }
}
