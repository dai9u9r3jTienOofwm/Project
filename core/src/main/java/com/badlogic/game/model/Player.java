package com.badlogic.game.model;

import com.badlogic.gdx.Math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GeometryBase;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphic.Texture;
import com.badlogic.gdx.util.MathUtils;
import com.badlogic.game.controller.InputController;
import com.badlogic.game.view.GameScreen;
import com.badlogic.game.view.Main;
import com.badlogic.game.view.GameoverScreen;
import com.badlogic.game.view.BulletTask;
import com.badlogic.game.view.Constants;
import com.badlogic.game.view.CollisionCheck;

public class Player extends BaseEntity {
    private int health;
    private float speed;
    private int powerLevel;
    private Rectangle bounds;
    private Image image;

    public Player(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.health = 5; // Default health
        this.speed = 5.0f; // Default speed
        this.powerLeve = 1; // Default power level
        this.bounds = new Rectangle(x, y, width, height);
        this.texture = new Texture("player.png"); // Placeholder texture
        image = new Image(batch, x, y, 0, 0, "player.png");
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

    public Vector2 getPosition() {
        return position;
    }

    public Image getImage() {
        return image;
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
        if (InputController.isPressedUp()) {
            position.y += speed * deltaTime;
        }
        if (InputController.isPressedDown()) {
            position.y -= speed * deltaTime;
        }

        if (InputController.isPressedLeft()) {
            position.x -= speed * deltaTime;
        }

        if (InputController.isPressedRight()) {
            position.x += speed * deltaTime;
        }

        position.x = MathUtils.clamp(position.x, 0, Constants.SCREEN_WIDTH - bounds.width);
        position.y = MathUtils.clamp(position.y, 0, Constants.SCREEN_HEIGHT - bounds.height);

        if (InputController.isShooting()) {
            if (InputController.isFocus()) {
                fireBullet(true);
            } else {
                fireBullet(false);
            }
        } 
        if (InputController.isFocus()) {
            this.setSpeed(2.0f);
        }
        bounds.setPosition(position.x, position.y);

    }

    public void powerUp() {
        powerLevel++;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }   

    @Override
    public void onDestroy() {
        Main.getInstance().setScreen(new GameoverScreen());
        System.out.println("Player is destroyed");
    }

    @Override
    public void render(SpriteBatch batch) {
        // Render the player sprite
        image.playerDraw();
    }    

    @Override
    public void dispose() {
        image.dispose();
    }

    public void fireBullet(boolean isFocus) {
        if (isFocus) {
            BulletTask.spamBullet(position, new Vector2(0, 1), powerLevel * 1.5f);
        } else {
            BulletTask.spamBullet(position, new Vector2(0, 1), powerLevel);
            BulletTask.spamBullet(position, new Vector2(-0,3f, 1), powerLevel);
            BulletTask.spamBullert(position, new Vector2(0.3f, 1), powerLevel);
        }
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
        bounds.setPosition(x, y);
    }

    
    
}   

