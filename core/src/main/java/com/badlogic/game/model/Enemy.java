package com.badlogic.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;   
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Enemy extends BaseEntity {
    private Vector2 position;
    private Rectangle patrolBounds;
    private Image image;
    private boolean isActive;
    private Vector2 direction;
    private int health;
    private float speed;

    // to do
    public Enemy(float x, float y, float width, float height, Vector2 direction) {
        
    }

    public Enemy(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.position = new Vector2(x, y);
        this.direction = new Vector2(0, -1);
        this.patrolBounds = new GeometryRec(x, y, width, height);
        this.image = new Image(x, y, 0, 0, "enemy.png");
        this.isActive = true;
        this.health = 3; // Default health
        this.speed = Constant.ENEMY_SPEED; // Default speed
    }

    // to do
    // Getters and Setters

    public float getFrameRow() {
        return image.getFrameRow();
    }

    public float getFrameCol() {
        return image.getFrameCol();
    }

    public float getX() {
        return image.getX();
    }

    public float getY() {
        return image.getY();
    }
        //to do
    // Move enemy in its current direction (direction x)
    // Check if it is out of bounds (patrolBounds), flip directionx
    @Override 
    public void update(float deltaTime) {

        if (Math.random() < 0.01) {
            fireBullet();
        }
        patrolBounds.setPosition(position.x, position.y);
        
    }
    // to do
    @Override
    public void render(SpriteBatch batch) {
        image.enemyDraw(enemy.getFrameRow(), enemy.getFrameCol(), enemy.getX(), enemy.getY(), batch);
    }

    // to do
    @Override
    public boolean isAlive() {}

    @Override
    public void onDestroy() {
        isActive = false;
        // Remove from the game screen
        GameScreen.removeEntity(this);

    }

    public void fireBullet() {
        BulletTask.spamBullet(new Vector2(position.x, position.y), direction, 5, BulletType.ENEMY_BULLET);
    }



}
