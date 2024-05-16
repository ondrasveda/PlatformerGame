package utilities;

import main.Game;

import java.awt.geom.Rectangle2D;

public class OtherMethods {

    public static boolean canMove(float x, float y, float width, float height, int[][] levelData) {

        if (!isSolid(x, y, levelData) && !isSolid(x + width, y + height, levelData) && !isSolid(x + width, y, levelData) && !isSolid(x, y + height, levelData)) {
            return true;
        }
        return false;
    }

    private static boolean isSolid(float x, float y, int[][] levelData) {
        if (x < 0 || x >= Game.gameWidth) {
            return true;
        }
        if (y < 0 || y >= Game.gameHeight) {
            return true;
        }
        float xIndex = x / Game.tileSize;
        float yIndex = y / Game.tileSize;

        int value = levelData[(int) yIndex][(int) xIndex];

        if (value >= 48 || value < 0 || value != 11) {
            return true;
        } else {
            return false;
        }
    }

    public static float getNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / Game.tileSize);

        if (xSpeed > 0) {
            int tileXPosition = currentTile * Game.tileSize;
            int offset = (int) (Game.tileSize - hitbox.width);
            return tileXPosition + offset - 1;
        } else {
            return currentTile * Game.tileSize;
        }
    }


    public static float getNextToFloorOrRoof(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Game.tileSize);
        if (airSpeed > 0) {
            int tileYposition = currentTile * Game.tileSize;
            int offset = (int) (Game.tileSize - hitbox.height);
            return tileYposition + offset - 1;
        } else {
            return currentTile * Game.tileSize;
        }
    }

    public static boolean playerIsOnTheFloor(Rectangle2D.Float hitbox, int[][] levelData) {
        if (!isSolid(hitbox.x, +hitbox.y + hitbox.height + 1, levelData) && !isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelData)) {
            return false;
        }
        return true;
    }
}
