package com.badlogic.game.view;

public class Renderer {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture background;
    
    // Assume we have some getter methods in GameScreen to get lists of game objects
    private GameScreen gameScreen;
    
    public Renderer(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        background = new Texture(Gdx.files.internal("background.png"));
    }
    
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Update camera and batch
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
        batch.begin();
        
        // Draw the background (for example, full screen)
        batch.draw(background, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        
        // Render player using its Image instance.
        // Assume the player has an associated Image object.
        gameScreen.getPlayer().getImage().playerDraw();
        
        // Render enemies.
        for (Enemy enemy : gameScreen.getEnemies()) {
            // Assume enemy stores its own x, y and frame selection info.
            // For example, enemy.getImage().enemyDraw(row, col, enemy.getX(), enemy.getY());
            enemy.getImage().enemyDraw(enemy.getFrameRow(), enemy.getFrameCol(), enemy.getX(), enemy.getY());
        }
        
        // Render player bullets.
        for (Bullet bullet : gameScreen.getPlayerBullets()) {
            bullet.getImage().bulletDraw(bullet.getFrameRow(), bullet.getFrameCol(), bullet.getX(), bullet.getY());
        }
        
        // Render enemy boss's bullets.
        for (Bullet bullet : gameScreen.getEnemyBullets()) {
            bullet.getImage().bulletDraw(bullet.getFrameRow(), bullet.getFrameCol(), bullet.getX(), bullet.getY());
        }
        
        // Render any additional entities (like bosses)
        if (gameScreen.getBoss() != null) {
            gameScreen.getBoss().getImage().playerDraw(); // or use a dedicated bossDraw() method
        }
        
        batch.end();
    }
    
    public void dispose() {
        batch.dispose();
        background.dispose();
        // Dispose any other textures if needed.
    }
}

