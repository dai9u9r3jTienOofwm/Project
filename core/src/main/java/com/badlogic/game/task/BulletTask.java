package com.badlogic.game.task;

import java.util.Vector;

public class BulletTask extends TaskBase {
    float stateTime;
    Bullet bullet;

    public BulletTask(Bullet bullet) {
        this.bullet = bullet;
        this.stateTime = 5;
    }

    public void update(float delta) {
        lifetime -= delta;

        if (lifetime <= 0) {
            bullet.onDestroy();
        }
    }

    public static void spamBullet(Vector2 position, Vector2 direction, int amount, String type) {
    float xSpeed = direction.nor().x  * Constant.BULLET_SPEED;
    float ySpeed = direction.nor().y * Constant.BULLET_SPEED;

    if (type.equals(BulletType.PLAYER_BULLET)) {
        for (int i = 0; i < amount; i++) {
            Bullet bullet = new Bullet(position.x, position.y, xSpeed, ySpeed, BulletType.PLAYER_BULLET);
            bullet.setImage(new Image("player_bullet.png"));
            bullet.setBounds(new GeometryRec(position.x, position.y, 10, 10));
            GameScreen.addBullet(bullet); // Directly adds bullet to GameScreen
        }
    } else if (type.equals(BulletType.ENEMY_BULLET)) {
        for (int i = 0; i < amount; i++) {
            Bullet bullet = new Bullet(position.x, position.y, xSpeed, ySpeed, speed, BulletType.ENEMY_BULLET);
            bullet.setImage(new Image("enemy_bullet.png"));
            bullet.setBounds(new GeometryRec(position.x, position.y, 10, 10));
            GameScreen.addBullet(bullet); // Directly adds bullet to GameScreen
        }
    }
}   

public void changeCard(BossBulletPattern pattern, Vector2 position) {
    ArrayList<Bullet> bullets =  GameScreen.getBossBullets();
    bullets.clear();
    if (pattern == BossBulletPattern.PHRASE_ONE) {
        bullets.add(fireSimpleSpread(position));
    } else if (pattern == BossBulletPattern.PHRASE_TWO) {
        bullets.add(fireRadialWave(position));
    } else if (pattern == BossBulletPattern.PHRASE_THREE) {
        bullets.add(fireSpiralPattern(position));
    }
}

private static List<Bullet> fireRadialWave(Vector2 position) {
        List<Bullet> bullets = new ArrayList<>();
        int bulletCount = 12;
        float angleStep = 360f / bulletCount;

        for (int i = 0; i < bulletCount; i++) {
            float angle = i * angleStep;
            Vector2 direction = new Vector2(MathUtils.cosDeg(angle), MathUtils.sinDeg(angle));
            bullets.add(new Bullet(position, direction.scl(Constants.BULLET_SPEED), BulletType.BOSS_BULLET));
        }

        return bullets;
    }

    private static List<Bullet> fireSimpleSpread(Vector2 position) {
        List<Bullet> bullets = new ArrayList<>();
        int bulletCount = 5;
        float angleStep = 15f;

        for (int i = -bulletCount / 2; i <= bulletCount / 2; i++) {
            float angle = i * angleStep;
            Vector2 direction = new Vector2(MathUtils.cosDeg(angle), MathUtils.sinDeg(angle));
            bullets.add(new Bullet(position, direction.scl(Constants.BULLET_SPEED), BulletType.BOSS_BULLET));
        }

        return bullets;
    }

    private static List<Bullet> fireSpiralPattern(Vector2 position) {
        List<Bullet> bullets = new ArrayList<>();
        int bulletCount = 20;
        float angleStep = 360f / bulletCount;

        for (int i = 0; i < bulletCount; i++) {
            float angle = i * angleStep;
            Vector2 direction = new Vector2(MathUtils.cosDeg(angle), MathUtils.sinDeg(angle));
            bullets.add(new Bullet(position, direction.scl(Constants.BULLET_SPEED), BulletType.BOSS_BULLET));
        }

        return bullets;
    }

    public static void removeBullet(GameScreen screen, Bullet bullet) {
        switch (bullet.getBulletType()) {
            case PLAYER_BULLET:
                screen.getPlayerBullets().remove(bullet);
                break;
            case ENEMY_BULLET:
                screen.getEnemyBullets().remove(bullet);
                break;
            case BOSS_BULLET:
                screen.getBossBullets().remove(bullet);
                break;
        }
        bullet.dispose(); // Proper memory cleanup
    }

    @Override
    public void onComplete() {
        // Handle task completion
        bullet.onDestroy();
    }
}
