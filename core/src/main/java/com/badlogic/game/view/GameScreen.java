package com.badlogic.game.view;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.game.model.BaseEntity;
import com.badlogic.game.model.Player;
import com.badlogic.game.view.Renderer;
import java.util.List;
import java.util.ArrayList;
import com.badlogic.game.view.GameoverScreen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.game.collision.CollisionCheck;
import com.badlogic.game.InputController;


public class GameScreen implements Screen {
    SpriteBatch batch;
    Player player;
    OrthographicCamera camera;
    ArrayList<Enemy> enemies;
    ArrayList<Bullet> playerBullets;
    ArrayList<Bullet> enemyBullets;
    ArrayList<Bullet> bossBullets;
    Boss boss;
    BossBulletPattern bossBulletPattern;
    BossBulletPattern pattern;
    float stateTime;

    public GameScreen() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        player = new Player();
        enemies = new ArrayList<>();
        playerBullets = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        bossBullets = new ArrayList<>();
        pattern = new BossBulletPattern();
        stateTime = 0f;

    // Initialize boss if needed
        boss = new Boss();

        Gdx.input.setInputProcessor(this); // Allow the screen to capture user input
    }
    
    public void show() {
        player = new Player();
        enemies = new ArrayList<>();
        playerBullets = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        boss = new Boss();
        pattern = new BossBulletPattern();
        stateTime = 0f;
    }


    private void update(float delta) {
    stateTime += delta;

    player.update(delta);
    for (Enemy enemy : enemies) {
        enemy.update(delta);
    }
    for (Bullet bullet : playerBullets) {
        bullet.update(delta);
    }
    for (Bullet bullet : enemyBullets) {
        bullet.update(delta);
    }
    for (Bullet bullet : bossBullets) {
        bullet.update(delta);
    }

    if (boss != null) {
        boss.update(delta);
        bossBulletPattern.executeAttack(stateTime); // Fire bullets dynamically
    }

    checkCollisions();
}


    public void removeEntity(BaseEntity entity) {
        if (entity instanceof Enemy) {
            enemies.remove(entity);
        } else if (entity instanceof Bullet) {
            playerBullets.remove(entity);
        } else if (entity instanceof Boss) {
            boss = null;
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        clearScreen();

    batch.begin();
    
    player.render(batch);
    for (Enemy enemy : enemies) {
        enemy.render(batch);
    }
    for (Bullet bullet : playerBullets) {
        bullet.render(batch);
    }
    for (Bullet bullet : enemyBullets) {
        bullet.render(batch);
    }
    for (Bullet bullet : bossBullets) { // Dedicated boss bullet rendering
        bullet.render(batch);
    }
    if (boss != null) {
        boss.render(batch);
    }

    batch.end();
}

    public void changeScreen() {
        if (InputController.isEscPressed()) {
            Main.getInstance().setScreen(new PauseScreen());
        }
    }

    @Override
    public void dispose() {
    batch.dispose();
    player.dispose();

    for (Enemy enemy : enemies) {
        enemy.dispose();
    }

    for (Bullet bullet : playerBullets) {
        bullet.dispose();
    }

    for (Bullet bullet : enemyBullets) {
        bullet.dispose();
    }
    for (Bullet bullet : bossBullets) {
        bullet.dispose();
    }

    if (boss != null) {
        boss.dispose();
    }
    }

    
}
