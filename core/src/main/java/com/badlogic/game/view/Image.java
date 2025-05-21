package com.badlogic.game.view;

public class Image {
    private Texture spriteSheet;
    private TextureRegion singleFrame;
    private float x, y;
    private int frameWidth = 64, frameHeight = 64;

    public Image(float x, float y, int selectRow, int selectCol, String texturePath) {
        this.x = x;
        this.y = y;
        spriteSheet = new Texture(Gdx.files.internal(texturePath));

        // Split the sprite sheet into frames.
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, frameWidth, frameHeight);
        singleFrame = tmp[selectRow][selectCol];
    }

    public float getFrameRow() {
        return y / frameHeight;
    }

    public float getFrameCol() {
        return x / frameWidth;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    // Player draw method as an example.
    public void playerDraw(SpriteBatch batch) {
        batch.draw(singleFrame, x, y);
    }
    
    // Example enemyDraw() method.
    public void enemyDraw(int selectRow, int selectCol, float enemyX, float enemyY, SpriteBatch batch) {
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


