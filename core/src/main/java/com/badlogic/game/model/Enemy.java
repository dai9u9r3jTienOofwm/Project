package com.badlogic.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;   
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Enemy extends BaseEntity {
    private boolean isActive;
    private Vector2 direction;
    private int health;
    private float speed;
    private BulletType type = BulletType.ENEMY_BULLET;

    // to do
    public Enemy(float x, float y, float width, float height, Vector2 direction) {
        super(x, y, width, height, "enemy.png");
        this.direction = new Vector2(direction.x, direction.y);
        this.isActive = true;
        this.health = 5; // Default health
        this.speed = Constant.ENEMY_SPEED; // Default speed
    }

    public Enemy(float x, float y, float width, float height) {
        super(x, y, width, height, "enemy.png");
        this.direction = new Vector2(0, -1);
        this.isActive = true;
        this.health = 5; // Default health
        this.speed = Constant.ENEMY_SPEED; // Default speed
    }

    // to do
    // Getters and Setters

    @Override
    public GeometryRec getBounds() {
        return super.getBounds();
    }
        //to do
    // Move enemy in its current direction (direction x)
    // Check if it is out of bounds (patrolBounds), flip directionx
    @Override 
    public void update(float deltaTime) {
        position.x = direction.x * deltaTime * Constant.ENEMY_SPEED;
        position.y = direction.y * deltaTime * Constant.ENEMY_SPEED;

        if (direction.x <= 0 || direction.x >= Constant.SCREEN_WIDTH) {
            direction.x = -direction.x;
        }

        if (Math.random() < 0.01) {
            fireBullet();
        }
        patrolBounds.setPosition(position.x, position.y);
        
    }
    // to do
    @Override
    public void render(SpriteBatch batch) {
        image.enemyDraw(this.getWidth(), this.getHeight(), this.getX(), this.getY(), batch);
    }

    // to do
    @Override
    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if(health <= 0) {
            this.alive = false;
        }
    }

    @Override
    public void onDestroy() {
        isActive = false;
        // Remove from the game screen
        GameScreen.removeEntity(this);

    }

    public void fireBullet() {
        BulletTask.spamBullet(new Vector2(position.x, position.y), direction, 5, type);
    }



}
