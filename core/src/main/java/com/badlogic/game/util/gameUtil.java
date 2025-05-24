package com.badlogic.game.util;

import com.badlogic.game.collision.CollisionCheck;
import com.badlogic.game.model.Boss;
import com.badlogic.game.model.Bullet;
import com.badlogic.game.model.Enemy;
import com.badlogic.game.model.Player;

import java.util.ArrayList;

import static com.badlogic.game.model.BulletType.*;

public class gameUtil {
    public static void checkCollisions(Player player, ArrayList<Enemy> enemies,
                                       ArrayList<Bullet> playerBullets, ArrayList<Bullet> enemyBullets,
                                       ArrayList<Bullet> bossBullets, Boss boss) {
        for (Enemy enemy : enemies) {
            if ((new CollisionCheck(player.getBounds(), enemy.getBounds())).checkCollision()) {
                player.onDestroy();
            }
            for (Bullet bullet : playerBullets) {
                if ((new CollisionCheck(bullet.getBounds(), enemy.getBounds())).checkCollision()) {
                    enemy.takeDamage(player.getPowerLevel());
                    bullet.onDestroy();
                }
            }
        }

        for (Bullet bullet : playerBullets) {
            if ((new CollisionCheck(bullet.getBounds(), boss.getBounds())).checkCollision()) {
                boss.takeDamage(player.getPowerLevel());
                bullet.onDestroy();
            }
        }

        if ((new CollisionCheck(boss.getBounds(), player.getBounds())).checkCollision()) {
            player.onDestroy();
        }

        for (Bullet bullet : bossBullets) {
            if ((new CollisionCheck(bullet.getBounds(), boss.getBounds())).checkCollision()) {
                player.takeDamage(boss.getPowerLever());
                bullet.onDestroy();
            }
        }

        for (Bullet bullet : playerBullets) {
            if ((new CollisionCheck(bullet.getBounds(), boss.getBounds())).checkCollision()) {
                boss.takeDamage(player.getPowerLevel());
                bullet.onDestroy();
            }
        }

        for (Bullet bullet : bossBullets) {
            if ((new CollisionCheck(bullet.getBounds(), player.getBounds())).checkCollision()) {
                player.takeDamage(boss.getPowerLever());
                bullet.onDestroy();
            }
        }
    }

    public static boolean shouldSpawnBoss(ArrayList<Enemy> enemies,float bossSpawmTimer, boolean isSpawningBoss) {
            return true;
        }

     }
