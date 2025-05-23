package com.badlogic.game.model;
import com.badlogic.game.collision.CollisionCheck;
import com.badlogic.game.collision.GeometryRec;
import com.badlogic.game.util.Constant;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import static com.badlogic.game.model.BulletType.*;

public class Bullet extends BaseEntity {
    private Vector2 velocity;
    private int damage = 1; //default damage
    private BulletType bulletType;

    //to do
    //contructor (damage = 1, isAlive = true)
    public Bullet(float x, float y) {
        super(new Texture("bullet.png"), x, y);
    }

    public Bullet(float x, float y, float xSpeed, float ySpeed, int speed, BulletType bulletType) {
        super(new Texture("bullet.png"), x, y);
        this.velocity = new Vector2(xSpeed, ySpeed);
        this.damage = 1; // Default damage
        this.bounds = new GeometryRec(x, y, 10, 10); // Default bounds
        this.bulletType = bulletType;
    }

    //to do
    // getter/setter


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void setAlive(boolean alive) {
        super.setAlive(alive);
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void update(float deltaTime) {
        setX(velocity.x * deltaTime * Constant.BULLET_SPEED);
        setY(velocity.y * deltaTime * Constant.BULLET_SPEED);

        if (isOffScreen()) {
            alive = false;
        }

        if (bulletType == PLAYER_BULLET) {
            // Check collision with enemies
            for (Enemy enemy : enemies) {
                if ((new CollisionCheck(bounds, enemy.getBounds())).checkCollision()) {
                    enemy.takeDamage(damage);
                    onDestroy();
                }
            }
        } else if (bulletType == ENEMY_BULLET || bulletType == BOSS_BULLET) {
            // Check collision with player
            if ((new CollisionCheck(bounds, player.getBounds())).checkCollision()) {
                player.takeDamage(damage);
                onDestroy();
            }
        }
    }
    @Override
    public void render(SpriteBatch batch) {
        if (alive) {
            batch.draw(texture, x, y, width, height);
        }
    }

    public boolean isOffScreen() {
        return y < 0 || y > Constant.SCREEN_HEIGHT;
    }
    @Override
    public void onDestroy() {
        alive = false;
        GameScreen.removeEntity(this);
    }
    public void checkCollision() {
        if (bulletType == PLAYER_BULLET) {
            // Check collision with enemies
            for (Enemy enemy : enemies) {
                if ((new CollisionCheck(bounds, enemy.getBounds())).checkCollision()) {
                    enemy.takeDamage(damage);
                    onDestroy();
                }
            }
        } else if (bulletType == ENEMY_BULLET || bulletType == BOSS_BULLET) {
            // Check collision with player
            if ((new CollisionCheck(bounds, player.getBounds())).checkCollision()) {
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
