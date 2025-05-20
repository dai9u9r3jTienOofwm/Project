package com.badlogic.game.view;

public class Image {
     private Texture spriteSheet;
    private TextureRegion singleFrame;
    private SpriteBatch batch;
    private float x, y;

    public class Image {
    private Texture spriteSheet;
    private TextureRegion singleFrame;
    private SpriteBatch batch;
    private float x, y;
    private int frameWidth = 64;   // Assume frame size defined
    private int frameHeight = 64;  // Assume frame size defined

    // New constructor that accepts a texture path.
    public Image(SpriteBatch batch, float x, float y, int selectRow, int selectCol, String texturePath) {
        this.batch = batch;
        this.x = x;
        this.y = y;
        // Loads the texture based on the passed texturePath
        spriteSheet = new Texture(Gdx.files.internal(texturePath));

        // Split the sprite sheet into frames.
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, frameWidth, frameHeight);
        // Pick the desired frame.
        singleFrame = tmp[selectRow][selectCol];
    }

    // Player draw method as an example.
    public void playerDraw() {
        batch.draw(singleFrame, x, y);
    }
    
    // Example enemyDraw() method.
    public void enemyDraw(int selectRow, int selectCol, float enemyX, float enemyY) {
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, frameWidth, frameHeight);
        TextureRegion enemyFrame = tmp[selectRow][selectCol];
        batch.draw(enemyFrame, enemyX, enemyY);
    }
    
    // Example bulletDraw() method.
    public void bulletDraw(int selectRow, int selectCol, float bulletX, float bulletY) {
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, frameWidth, frameHeight);
        TextureRegion bulletFrame = tmp[selectRow][selectCol];
        batch.draw(bulletFrame, bulletX, bulletY);
    }

    public void dispose() {
        spriteSheet.dispose();
    }
}

}
