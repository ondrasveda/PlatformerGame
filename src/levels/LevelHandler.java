package levels;

import main.Game;
import utilities.Load_Save;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.tileSize;


public class LevelHandler {

    private Game game;
    private BufferedImage[] levels;
    private Level level;

    public LevelHandler(Game game) {
        this.game = game;
        importLevelOutsides();
        level = new Level(Load_Save.getLevelData());
    }

    private void importLevelOutsides() {
        BufferedImage image = Load_Save.getImages(Load_Save.levelImage);
        levels = new BufferedImage[48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levels[index] = image.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics graphics, int levelOffset) {
        for (int j = 0; j < Game.gameTileHeight; j++) {
            for (int i = 0; i < level.getLevelData()[0].length; i++) {
                int index = level.getImageIndex(i, j);
                graphics.drawImage(levels[index],  tileSize *i - levelOffset , j * tileSize, null);
            }
        }

    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return level;
    }

}
