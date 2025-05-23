package com.badlogic.game.model;

public class Bullet extends BaseEntity {
    private Vector2 position;
    private Vector2 velocity;
    private int damage;
    private GeometryRec bounds;
    private boolean isAlive;
    private BulletType bulletType;
    private Image image;

    //to do 
    //contructor (damage = 1, isAlive = true)

    /**
     * Constructs a new Bullet instance with the specified position, velocity, speed, and type.
     *
     * @param x          The initial x-coordinate of the bullet.
     * @param y          The initial y-coordinate of the bullet.
     * @param xSpeed     The horizontal component of the bullet's velocity.
     * @param ySpeed     The vertical component of the bullet's velocity.
     * @param bulletType The type of the bullet.
     */
    public Bullet(float x, float y, float xSpeed, float ySpeed, int speed, BulletType bulletType) {
        super(x, y, 0, 0);
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(xSpeed, ySpeed);
        this.damage = 1; // Default damage
        this.isAlive = true;
        this.bounds = new GeometryRec(x, y, 10, 10); // Default bounds
        this.bulletType = bulletType;
        this.speed = speed;
    }


    //to do
    // getter/setter


    public void getBulletType() {
        return bulletType;
    }

    @Override
    public void update(float deltaTime) {
        position.add(velocity.x * delta, velocity.y * delta);

        if (isOffScreen()) {
            isAlive = false;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        if (isAlive) {
             bullet.getImage().bulletDraw(bullet.getFrameRow(), bullet.getFrameCol(), bullet.getX(), bullet.getY());
        }
    }

    private boolean isOffScreen() {
        return position.y < 0 || position.y > Constants.SCREEN_HEIGHT;
    }
    @Override
    public void onDestroy() {
        isAlive = false;
        GameScreen.removeEntity(this);
    }
    
    

    @Override
    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }

    

}
