package com.badlogic.game.model;

public class Boss extends BaseEntity {
    private Vector2 position;
    private int health;
    private int currentPhrase;
    private BossBulletPattern pattern;
    private float stateTime;
    private boolean isAlive;
    private Image image;
    private GeometryRec bound;

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
         bulletPattern.executeAttack(stateTime);

         if (health <= 75 && currentPhrase == 1 ) {
            transitionToSpellCard1();
         }

         if (health <= 5 && currentPhrase == 2) {
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
        bulletPattern.changeCard(BossBulletPattern.PHRASE_TWO);
    }
    private void transtitionToSpellCard2() {
        currentPhrase = 3;
        bulletPattern.changeCard(BossBulletPattern.PHRASE_THREE);
    }

    @Override
    public void onDestroy() {
        isAlive = false;
        GameScreen.removeEntity(this);
    }
}
