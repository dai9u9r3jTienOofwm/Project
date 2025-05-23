package com.badlogic.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HealthItem extends BaseEntity{
    private float speed = 100f;
    public int healAmount;
    private boolean alive = true;

    public HealthItem(float x, float y, float width, float height) {
        super(x, y, width, height, "HealItem.png");
        this.healAmount = 1;
    }

    public HealthItem(float x, float y, float width, float height, int healAmount) {
        super(x, y, width, height, "HealItem.png");
        this.healAmount = 1;

    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    public int getHealAmount() {
        return healAmount;
    }

    @Override
    public void update(float deltaTime) {
        position.y -= speed * deltaTime;
        if (position.y < 0) {
            alive = false;
        }

    }

    @Override
    public void render(SpriteBatch batch) {
        this.getImage().hItemDraw(this.getWidth(), this.getHeight(), this.getX(), this.getY(), batch);
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void setAlive(boolean alive) {
        super.setAlive(alive);
    }
}
