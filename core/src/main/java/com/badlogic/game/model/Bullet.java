package com.badlogic.game.model;

public class Bullet extends BaseEntity {
    private Vector2 position;
    private Vector2 velocity;
    private int damage;
    private GeometryRec bounds;
    private boolean isAlive;
    private BulletType bulletType;

    //to do 
    //contructor (damage = 1, isAlive = true)



    //to do
    // getter/setter


    @Override
    public void update(float deltaTime) {
        position.x = velocity.x * deltaTime * Constant.BULLET_SPEED;
        position.y = velocity.y * deltaTime * Constant.BULLET_SPEED;

        bounds.setPosition(position.x, position.y);
        if (isOffScreen) {
            isAlive = false;
        }

        if (bulletType == PLAYER_BULLET) {
            // Check collision with enemies
            for (Enemy enemy : enemies) {
                if (CollisionCheck.checkCollision(bounds, enemy.getBounds())) {
                    enemy.takeDamage(damage);
                    onDestroy();
                }
            }
        } else if (bulletType == ENEMY_BULLET || bulletType == BOSS_BULLET) {
            // Check collision with player
            if (CollisionCheck.checkCollision(bounds, player.getBounds())) {
                player.takeDamage(damage);
                onDestroy();
            }
        }
    }
    @Override
    public void render(SpriteBatch batch) {
        if (isAlive) {
            batch.draw(texture, position.x, position.y, bounds.width, bounds.height);
        }
    }

    @Override
    public boolean isOffScreen() {
        return position.y < 0 || position.y > Constants.SCREEN_HEIGHT;
    }
    @Override
    public void onDestroy() {
        isAlive = false;
        GameScreen.removeEntity(this);
    }
    public void checkCollision() {
        if (bulletType == PLAYER_BULLET) {
            // Check collision with enemies
            for (Enemy enemy : enemies) {
                if (CollisionCheck.checkCollision(bounds, enemy.getBounds())) {
                    enemy.takeDamage(damage);
                    onDestroy();
                }
            }
        } else if (bulletType == ENEMY_BULLET || bulletType == BOSS_BULLET) {
            // Check collision with player
            if (CollisionCheck.checkCollision(bounds, player.getBounds())) {
                player.takeDamage(damage);
                onDestroy();
            }
        }
    }

    @Override
    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }

    

}
