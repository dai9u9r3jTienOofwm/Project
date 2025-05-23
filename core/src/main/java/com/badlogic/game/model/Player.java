package com.badlogic.game.model;

import com.badlogic.gdx.Math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.game.collision.GeometryRec;
import com.badlogic.gdx.graphic.Texture;
import com.badlogic.gdx.util.MathUtils;
import com.badlogic.game.controller.InputController;
import com.badlogic.game.view.GameScreen;
import com.badlogic.game.view.Main;
import com.badlogic.game.view.GameoverScreen;
import com.badlogic.game.view.BulletTask;
import com.badlogic.game.view.Constants;
import com.badlogic.game.view.CollisionCheck;
import com.badlogic.game.util.Constant;

public class Player extends BaseEntity {
    private int health;
    private int powerLevel;
    private GeometryRec bounds;
    private BulletType type;

    public Player(float x, float y, float width, float height) {
        super(x, y, width, height, "player.png");
        this.health = 5; // Default health
        this.speed = Constant.PLAYER_SPEED; // Default speed
        this.powerLevel= 1; // Default power level
        this.bounds = new GeometryRec(x, y, width, height);
        this.type = BulletType.PLAYER_BULLET;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    /**
     * Updates the state of the player.
     *
     * @param deltaTime The time elapsed since the last update, in seconds.
     */
    @Override
    public void update(float deltaTime) {
        // Update player position based on input and speed (
        // Up, Down, Left, Right)
        if (inputController.isPressedUp()) {
            setY(getY() + speed * deltaTime);
        }
        if (inputController.isPressedDown()) {
            setY(getY() - speed * deltaTime);
        }

        if (inputController.isPressedLeft()) {
            setX(getX() - speed * deltaTime);
        }

        if (inputController.isPressedRight()) {
            setX(getX() + speed * deltaTime);
        }

        setX(MathUtils.clamp(getX(), 0, Constant.SCREEN_WIDTH - super.getWidth()));
        setY(MathUtils.clamp(getY(), 0, Constant.SCREEN_HEIGHT - super.getHeight()));

        if (InputController.isShooting()) {
            if (InputController.isFocus()) {
                fireBullet(true);
            } else {
                fireBullet(false);
            }
        } 
        if (InputController.isFocus()) {
            this.setSpeed(Constant.PLAYER_SPEED / 1.5f);
        }
        bounds.setPosition(position.x, position.y);

        if (!isAlive()) {
            onDestroy();
        }

    }

    public void powerUp() {
        powerLevel++;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            alive = false;
        }
    }

    public void getHeal() {
        health += 1;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }   

    @Override
    public void onDestroy() {
        Main.getInstance().setScreen(new GameoverScreen());
        System.out.println("Player is destroyed");
    }

    @Override
    public void render(SpriteBatch batch) {
        // Render the player sprite
        if (isAlive()) {
            this.getImage().playerDraw(this.getWidth(), this.getHeight(), this.getX(), this.getY(), batch);
        }
    }    

    @Override
    public void dispose() {
        // Dispose of any resources used by the player
        this.getImage().dispose();
    }

    public void fireBullet(boolean isFocus) {
        if (isFocus) {
            BulletTask.spamBullet(position, new Vector2(0, 1), powerLevel * 2, type);
        } else {
            BulletTask.spamBullet(position, new Vector2(0, 1), powerLevel, type);
            BulletTask.spamBullet(position, new Vector2(-0,3f, 1), powerLevel, type);
            BulletTask.spamBullert(position, new Vector2(0.3f, 1), powerLevel, type);
        }
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
        bounds.setPosition(x, y);
    }

    
    
}   

