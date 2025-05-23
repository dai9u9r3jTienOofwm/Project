package com.badlogic.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HealthItem extends BaseEntity{
    private float speed = 200f;
    public int healAmount;

    public HealthItem(float x, float y) {
        super(new Texture("HealItem.png"), x, y);
        this.healAmount = 1;

    }

    public HealthItem(float x, float y, int healAmount) {
        super(new Texture("HealItem.png"), x, y);
        this.healAmount = healAmount;

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
        y -= speed * deltaTime;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
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

