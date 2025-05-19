package com.badlogic.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;   
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Enemy extends BaseEntity {
    private Vector2 position;
    private Rectangle patrolBounds;
    private Texture texture;
    private boolean isActive;
    private Vector2 direction;
    private int health;
    private float speed;

    public Enemy(float x, float y, float width, float height, Vector2 direction) {
        
    }

    public Enemy(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.position = new Vector2(x, y);
        this.patrolBounds = new Rectangle(x, y, width, height);
        this.texture = new Texture("enemy.png"); // Placeholder texture
        this.isActive = true;
        this.health = 3; // Default health
        this.speed = 2.0f; // Default speed
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
        this.bounds.setPosition(x, y);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public int getHealth() {
        return health;
    }    

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override 
    public void update(float deltaTime) {
        position.x += direction.x * speed * deltaTime; 
    }



}
