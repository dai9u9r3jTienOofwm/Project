package com.badlogic.game.model;

import com.badlogic.game.collision.GeometryRec;
import com.badlogic.game.task.BulletTask;
import com.badlogic.game.util.Constant;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Enemy extends BaseEntity {
    private Vector2 direction;
    private int health;
    private float speed;
    private BulletType type = BulletType.ENEMY_BULLET;

    // to do
    public Enemy(float x, float y, float width, float height, Vector2 direction) {
        super(x, y, width, height, "enemy.png");
        this.direction = new Vector2(direction.x, direction.y);
        this.health = 5; // Default health
        this.speed = Constant.ENEMY_SPEED; // Default speed
    }

    public Enemy(float x, float y, float width, float height) {
        super(x, y, width, height, "enemy.png");
        this.direction = new Vector2(0, -1);

        this.health = 5; // Default health
        this.speed = Constant.ENEMY_SPEED; // Default speed
    }

    // to do
    // Getters and Setters


    @Override
    public float getHeight() {
        return super.getHeight();
    }

    @Override
    public float getWidth() {
        return super.getWidth();
    }

    @Override
    public float getX() {
        return super.getX();
    }

    @Override
    public float getY() {
        return super.getY();
    }

    @Override
    public GeometryRec getBounds() {
        return super.getBounds();
    }

    //to do
    // Move enemy in its current direction (direction x)
    // Check if it is out of bounds (patrolBounds), flip directionx
    @Override
    public void update(float deltaTime) {
        if (Math.random() < 0.01) {
            fireBullet();
        }
        super.setX(super.getX() + direction.x * speed * deltaTime);
        super.setY(super.getY() + direction.y * speed * deltaTime);
        if (super.getX() < 0 || super.getX() > Constant.SCREEN_WIDTH - super.getWidth()) {
            direction.x = -direction.x;
        }

        if (!isAlive()) {
            onDestroy();
        }
    }
    // to do
    @Override
    public void render(SpriteBatch batch) {
        this.getImage().enemyDraw(this.getWidth(), this.getHeight(), this.getX(), this.getY(), batch);
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
