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
    private SpriteBatch batch;
    private Player player;
    private OrthographicCamera camera;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> playerBullets, enemyBullets, bossBullets;
    private ArrayList<HealthItem> HealthItems;
    private ArrayList<PowerItem> PowerItems;
    private Boss boss;
    private HUD hud;
    private float bossSpawnTimer = 0f;
    private boolean isSpawningBoss = false;

    public GameScreen() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        player = new Player();
        enemies = new ArrayList<>();
        playerBullets = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        bossBullets = new ArrayList<>();
        HealthItems = new ArrayList<>();
        PowerItems = new ArrayList<>();

        hud = new HUD(batch); // Initialize HUD
        Gdx.input.setInputProcessor(this);
    }

    private void update(float delta) {
        player.update(delta);
        for (Enemy enemy : enemies) enemy.update(delta);
        for (Bullet bullet : playerBullets) bullet.update(delta);
        for (Bullet bullet : enemyBullets) bullet.update(delta);
        for (Bullet bullet : bossBullets) bullet.update(delta);
        for (HealthItem item : HealthItems) item.update(delta);
        for (PowerItem item : PowerItems) item.update(delta);

        // Boss spawning logic
        if (GameUtil.shouldSpawnBoss(enemies, bossSpawnTimer, isSpawningBoss)) {
            spawnBoss();
            isSpawningBoss = true;
        } else {
            bossSpawnTimer += delta;
        }

        if (boss != null) boss.update(delta);

        GameUtil.checkCollisions(player, enemies, playerBullets, enemyBullets, bossBullets, boss, HealthItems, PowerItems);

        hud.update(delta); // Update HUD display
    }

    private void spawnBoss() {
        boss = new Boss(new Vector2(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - 50));
    }

    @Override
    public void render(float delta) {
        update(delta);
        clearScreen();

        batch.begin();
        player.render(batch);
        for (Enemy enemy : enemies) enemy.render(batch);
        for (Bullet bullet : playerBullets) bullet.render(batch);
        for (Bullet bullet : enemyBullets) bullet.render(batch);
        for (Bullet bullet : bossBullets) bullet.render(batch);
        for (HealthItem item : HealthItems) item.render(batch);
        for (PowerItem item : PowerItems) item.render(batch);
        if (boss != null) boss.render(batch);
        hud.render(); // Draw HUD on top
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        hud.dispose();
        player.dispose();
        for (Enemy enemy : enemies) enemy.dispose();
        for (Bullet bullet : playerBullets) bullet.dispose();
        for (Bullet bullet : enemyBullets) bullet.dispose();
        for (Bullet bullet : bossBullets) bullet.dispose();
        if (boss != null) boss.dispose();
    }

    public void removeEntity(BaseEntity entity) {
    if (entity instanceof Enemy) {
        enemies.remove(entity);
    } else if (entity instanceof Bullet) {
        BulletTask.removeBullet(this, (Bullet) entity);
    } else if (entity instanceof Boss) {
        boss = null;
    }

    entity.dispose();
}
    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }
    @Override
    public void show() {
        // Called when the screen is set as the current screen for a Game

    }

    @Override
    public void hide() {
        // Called when the screen is no longer the current screen for a Game
    }

    @Override
    public void pause() {
        // Called when the game is paused
    }

    @Override
    public void resume() {
        // Called when the game is resumed after being paused
    }
}
