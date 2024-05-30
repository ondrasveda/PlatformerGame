package object;

import java.awt.geom.Rectangle2D;

import main.Game;

public class Object {

    protected int x;
    protected int y;
        protected int objectType;
    protected Rectangle2D.Float hitbox;
    protected int xDrawOffset;
    protected int yDrawOffset;

    /**
     * Constructor for an Object with the specified coordinates and object type.
     */
    public Object(int x, int y, int objectType) {
        this.x = x;
        this.y = y;
        this.objectType = objectType;
    }

    /**
     * Initializes the hitbox of the object with the specified width and height.
     */
    protected void initializeHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.tileScale), (int) (height * Game.tileScale));
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public int getYDrawOffset() {
        return yDrawOffset;
    }

}