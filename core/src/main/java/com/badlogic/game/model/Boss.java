package com.badlogic.game.model;

import com.badlogic.game.collision.GeometryRec;
import com.badlogic.game.util.Constant;
import com.badlogic.game.view.Image;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Boss extends BaseEntity {
    private int health = 100;
    private int currentPhrase;
    private BossBulletPattern bulletPattern;
    private float stateTime;
    private Vector2 velocity = new Vector2(10f, 10f);
    private int powerLever = 1;

    public Boss(float x, float y, BossBulletPattern pattern) {
        super(new Texture("boss.png"), x, y);
        this.currentPhrase = 1;
        this.bounds = new GeometryRec(x, y, width, height);
        this.bulletPattern = pattern;
        this.stateTime = 0;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if(health <= 0) {
            this.alive = false;
            onDestroy();
        }
    }

    public int getPowerLever() {
        return powerLever;
    }

    public void setPowerLever(int powerLever) {
        this.powerLever = powerLever;
    }

    @Override
    public void update(float deltaTime) {
        stateTime += deltaTime;
        setX(this.x + velocity.x * deltaTime);
        setY(this.y + velocity.y * deltaTime);
        if (x < 0 || x > Constant.SCREEN_WIDTH - width) {
            velocity.x = -velocity.x;
        }

        if ((health <= 55 || stateTime >= 60 ) && currentPhrase == 1 ) {
            transitionToSpellCard1();
        }

        if ((health <= 5 || stateTime >= 120 ) && currentPhrase == 2) {
            health = 100;
            this.powerLever = 2;
            transitionToSpellCard2();
        }

        if (!alive) {
            onDestroy();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    @Override
    public GeometryRec getBounds() {
        return super.getBounds();
    }

    @Override
    public boolean isAlive() {
        return health <= 0;
    }

    private void transitionToSpellCard1() {
        currentPhrase = 2;
        bulletPattern.changeCard(BossBulletPattern.PHRASE_TWO);
    }
    private void transitionToSpellCard2() {
        currentPhrase = 3;
        bulletPattern.changeCard(BossBulletPattern.PHRASE_THREE);
    }

    @Override
    public void onDestroy() {
        alive = false;
        GameScreen.removeEntity(this);
    }
}
