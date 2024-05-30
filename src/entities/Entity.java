package entities;

import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float hitboxX;
    protected float hitboxY;
    protected int hitboxWidth;
    protected int hitboxHeight;
    protected Rectangle2D.Float hitbox;

    /**
     * The constructor initializes the position and size of the entity.
     */
    public Entity(float x, float y, int width, int height) {
        this.hitboxX = x;
        this.hitboxY = y;
        this.hitboxWidth = width;
        this.hitboxHeight = height;
    }

    /**
     * initializes entity hitbox
     */
    protected void initializeHitbox(float x, float y, int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);

    }

    /**
     * getter for hitbox
     */
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }
}
