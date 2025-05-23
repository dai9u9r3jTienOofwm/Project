package com.badlogic.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PowerItem extends BaseEntity {
    private float speed = 200f;
    public int powerBuff;

    public PowerItem(float x, float y) {
        super(new Texture("PowerItem.png"), x, y);
        this.powerBuff = 1;

    }

    public PowerItem(float x, float y, int powerBuff) {
        super(new Texture("PowerItem.png"), x, y);
        this.powerBuff = powerBuff;

    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setPowerBuff(int powerBuff) {
        this.powerBuff = powerBuff;
    }

    public int getPowerBuff() {
        return powerBuff;
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

