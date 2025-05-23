package com.badlogic.game.model;

import com.badlogic.game.collision.GeometryRec;
import com.badlogic.game.task.BulletTask;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.game.controller.InputController;
import com.badlogic.game.view.GameScreen;
import com.badlogic.game.view.GameoverScreen;
import com.badlogic.game.util.Constant;
import com.badlogic.gdx.math.Vector2;

public class Player extends BaseEntity {
    private int health = 5;;
    private int powerLevel = 1;
    private float speed = Constant.PLAYER_SPEED;
    InputController inputController = new InputController();

    public Player(float x, float y) {
        super(new Texture("player.png"), x, y);
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

    @Override
    public void setX(float x) {
        super.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
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

        if (inputController.isShooting()) {
            if (inputController.isFocus()) {
                fireBullet(true);
            } else {
                fireBullet(false);
            }
        }
        if (inputController.isFocus()) {
            this.setSpeed(Constant.PLAYER_SPEED);
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
        if (super.getTexture() != null) {
            batch.draw(super.getTexture(), getX(), getY(), getWidth(), getHeight());
        }
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    public void fireBullet(boolean isFocus) {
        if (isFocus) {
            BulletTask.spamBullet(x, y, new Vector2(0, 1), powerLevel * 1.5f);
        } else {
            BulletTask.spamBullet(x, y, new Vector2(0, 1), powerLevel);
            BulletTask.spamBullet(x, y, new Vector2(-0,3f, 1), powerLevel);
            BulletTask.spamBullet(x, y, new Vector2(0.3f, 1), powerLevel);
        }
    }

}

