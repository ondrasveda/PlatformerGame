package object;

import java.awt.geom.Rectangle2D;

import main.Game;

public class Object {

    protected int x, y, objType;
    protected Rectangle2D.Float hitbox;
    protected int xDrawOffset, yDrawOffset;

    public Object(int x, int y, int objType) {
        this.x = x;
        this.y = y;
        this.objType = objType;
    }



    protected void initializeHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.tileScale), (int) (height * Game.tileScale));
    }


    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public int getyDrawOffset() {
        return yDrawOffset;
    }

}