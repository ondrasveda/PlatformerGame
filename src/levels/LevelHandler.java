package levels;

import main.Game;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelHandler {

    private Game game;
    private BufferedImage[] levelTextures;
    private Level level;

    /**
     * Constructs for the LevelHandler object with the specified game.

     */
    public LevelHandler(Game game) {
        this.game = game;
        importLevelTextures();
        level = new Level(Load.getLevelData());
    }
    /**
     * Imports level textures for building the level.
     */
    private void importLevelTextures() {
        BufferedImage image = Load.getImages(Load.levelTextures);
        levelTextures = new BufferedImage[48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelTextures[index] = image.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }
    /**
     * Draws the current level on the screen.
     */
    public void draw(Graphics graphics, int levelOffset) {
        for (int j = 0; j < Game.windowTileHeight; j++) {
            for (int i = 0; i < level.getLevelData()[0].length; i++) {
                int index = level.getImageIndex(i, j);
                graphics.drawImage(levelTextures[index],  Game.tileSize *i - levelOffset , j * Game.tileSize, null);
            }
        }

    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return level;
    }

}
