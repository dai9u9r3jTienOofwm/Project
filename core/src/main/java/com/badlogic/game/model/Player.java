package com.badlogic.game.model;

import com.badlogic.gdx.Math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GeometryBase;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphic.Texture;

public class Player extends BaseEntity {
    private int health;
    private float speed;
    private int powerLevel;
    private Rectangle bounds;
    private Texture texture;
    private boolean isActive;

    public Player(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.health = 5; // Default health
        this.speed = 5.0f; // Default speed
        this.powerLeve = 1; // Default power level
        this.bounds = new Rectangle(x, y, width, height);
        this.texture = new Texture("player.png"); // Placeholder texture
        this.isActive = true;
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
            fireBullet();
        }

        if (InputController.isFocus()) {
            this.setSpeed(2);
            fireBullet2();
        }

        bounds.setPosition(position.x, position.y);

        // Check for collisions with enemies and bullets using
        // Dùng for loop check enemy trong gameScreen, nếu va chạm thì dùng takeDamage
        // takeDamage(1);

        // Dùng for loop check enemy trong gameScreen, nếu va chạm thì dùng takeDamage
        // takeDamage(1);

        // Check health, if health <= 0, call onDestroy()
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
        isActive = false;
        Main.getInstance().setScreen(new GameoverScreen());
        System.out.println("Player is destroyed");
    }

    @Override
    public void render(SpriteBatch batch) {
        // Render the player sprite
        if (texture != null) {
            batch.draw(texture, getPosition().x, getPosition().y, bounds.width, bounds.height);
        }
    }    
}   
