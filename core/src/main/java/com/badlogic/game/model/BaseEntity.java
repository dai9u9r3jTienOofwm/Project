package com.badlogic.game.model;

import com.badlogic.gdx.Math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.collision.GeometryBase;
import com.badlogic.gdx.graphics.Texture;


public abstract class BaseEntity {
    private Vector2 position;
    private Vector2 size;
    private GeometryBase bounds;
    private Image image;

    public BaseEntity(float x, float y, float width, float height, String texturePath) {
        this.position = new Vector2(x, y);
        this.size = new Vector2(width, height);
        this.bounds = new GeometryRec(x, y, width, height);
        this.image = new Image(x, y, 0, 0, texturePath);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(float width, float height) {
        this.size.set(width, height);
    }

    public float getWidth() {
        return size.x;
    }

    public float getHeight() {
        return size.y;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }   

    public GeometryBase getBounds() {
        if (bounds == null) {
            bounds = new GeometryRec(position.x, position.y, size.x, size.y);
        }
        return bounds;
    }

    public void setBounds(GeometryBase bounds) {
        this.bounds = bounds;
    }

    public Image getImage() {
        return image;
    }

    public abstract void update(float deltaTime);

    public abstract void render(SpriteBatch batch);

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
