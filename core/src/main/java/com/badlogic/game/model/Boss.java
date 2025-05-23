package com.badlogic.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.game.task.BulletTask;
import com.badlogic.game.view.Image;
import com.badlogic.game.view.GameScreen;
import com.badlogic.game.collision.GeometryRec;
import com.badlogic.game.view.BossBulletPattern;

public class Boss extends BaseEntity {
    private int health;
    private int currentPhrase;
    private BossBulletPattern pattern;
    private float stateTime;
    private boolean isAlive;
    private Image image;
    private GeometryRec bounds;

    public Boss(Vector2 position) {
        super(position.x, position.y, 0, 0);
        this.health = 100; // Default health
        this.currentPhrase = 1;
        this.bounds = new GeometryRec(position.x, position.y, 0, 0);
        this.image = new Image(batch, position.x, position.y, 0, 0, "boss.png");
        this.pattern = new BossBulletPattern(this);
        this.stateTime = 0;
    }

    public Boss(float x, float y, float width, float height, BossBulletPattern pattern) {
        super(x, y, width, height);
        this.health = 100; // Default health
        this.currentPhrase = 1;
        this.bounds = new GeometryRec(x, y, width, height);
        this.image = new Image(batch, x, y, 0, 0, "player.png");
        this.pattern = new BossBulletPattern(this);
        this.stateTime = 0;
    }

    @Override
    public void update(float deltaTime) {
        stateTime += delta;
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        if (position.x < 0 || position.x > Constants.SCREEN_WIDTH - width) {
            velocity.x = -velocity.x;
        }
         if ((health <= 55 || stateTime >= 60 ) && currentPhrase == 1 ) {
            transitionToSpellCard1();
         }

         if ((health <= 5 || stateTime >= 120 )&& currentPhrase == 2) {
            health = 100;
            transitionTOSpellCard2();
         }

         if (!isAlive) {
            onDestroy();
         }
    }

    @Override
    public void render(SpriteBatch batch) {
        image.enemyDraw(enemy.getFrameRow(), enemy.getFrameCol(), enemy.getX(), enemy.getY(), batch);
    }

    @Override 
    public boolean isAlive() {
        return health <= 0;
    }

    private void transitionToSpellCard1() {
        currentPhrase = 2;
        BulletTask.changeCard(BossBulletPattern.PHRASE_TWO);
    }
    private void transtitionToSpellCard2() {
        currentPhrase = 3;
        bulletTask.changeCard(BossBulletPattern.PHRASE_THREE);
    }

    @Override
    public void onDestroy() {
        isAlive = false;
        GameScreen.removeEntity(this);
    }
}
