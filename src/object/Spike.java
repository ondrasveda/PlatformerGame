package object;

import main.Game;

public class Spike extends Object{

    /**
     * Constructs a Spike object with the specified coordinates and object type.
     */
    public Spike(int x, int y, int objType) {
        super(x, y, objType);

        initializeHitbox(32, 16);
        xDrawOffset = 0;
        yDrawOffset = (int)(Game.tileScale * 16);
        hitbox.y += yDrawOffset;

    }
}
