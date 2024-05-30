package utilities;

import main.Game;
import object.Spike;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The OtherMethods class has various utility methods used in the game that i didnt know where to put.
 */
public class OtherMethods {

    /**
     * Checks if the player can move to the specified position based on the level data.
     */
    public static boolean canMove(float x, float y, float width, float height, int[][] levelData) {

        if (!isSolid(x, y, levelData) && !isSolid(x + width, y + height, levelData) && !isSolid(x + width, y, levelData) && !isSolid(x, y + height, levelData)) {
            return true;
        }
        return false;
    }
    /**
     * Checks if the specified position is solid (contains an obstacle) in the level.
     */
    private static boolean isSolid(float x, float y, int[][] levelData) {
        int levelWidth = levelData[0].length * Game.tileSize;
        int levelHeight = levelData.length * Game.tileSize;
        if (x < 0 || x >= levelWidth) {
            return true;
        }
        if (y < 0 || y >= levelHeight) {
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

    /**
     * Gets the x-coordinate of the next wall adjacent to the player's hitbox.
     */
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

    /**
     * Gets the y-coordinate of the next floor or roof adjacent to the player's hitbox.
     */
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

    /**
     * Checks if the player is on the floor.
     */
    public static boolean playerIsOnTheFloor(Rectangle2D.Float hitbox, int[][] levelData) {
        if (!isSolid(hitbox.x, +hitbox.y + hitbox.height + 1, levelData) && !isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelData)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the spawn point for the player from the given image.
     */
    public static Point GetPlayerSpawn(BufferedImage img) {
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getGreen();
                if (value == 100)
                    return new Point(i * Game.tileSize, j * Game.tileSize);
            }
        return new Point(Game.tileSize, Game.tileSize);
    }

    /**
     * Gets a list of spike objects from the given image.
     */
    public static ArrayList<Spike> GetSpikes(BufferedImage img) {
        ArrayList<Spike> list = new ArrayList<>();

        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getBlue();
                if (value ==  1)
                    list.add(new Spike(i * Game.tileSize, j * Game.tileSize, 1  ));
            }

        return list;
    }
    /**
     * Gets the level data from the given image.
     */
    public static int[][] GetLevelData(BufferedImage img) {
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48)
                    value = 0;
                lvlData[j][i] = value;
            }
        return lvlData;
    }
}
