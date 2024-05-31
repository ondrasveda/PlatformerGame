package utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class Load {
    /**
     * File paths for all the game assets.
     */
    public static final String playerImage = "player.png"; //penzilla.itch.io/hooded-protagonist

    public static final String levelTextures = "level_textures.png"; //free-game-assets.itch.io/free-green-zone-tileset-pixel-art
    public static final String levelBackground = "level_background.png"; //free-game-assets.itch.io/free-green-zone-tileset-pixel-art
    public static final String level = "level.png";

    public static final String menuButtons = "menu_button_textures.png"; //pixelfrog-assets.itch.io/treasure-hunters
    public static final String menuBackgroundImage = "menu_background.png"; //pressstart.vip/images/uploads/assets/foggy.png

    public static final String pauseBackground = "pause_background.png"; //pixelfrog-assets.itch.io/treasure-hunters
    public static final String volumeButtons = "volume_buttons.png"; //pixelfrog-assets.itch.io/treasure-hunters
    public static final String unpauseRestartMenuButtons = "unpause_restart_menu_buttons.png"; //pixelfrog-assets.itch.io/treasure-hunters

    public static final String spikes = "spikes.png";



    /**
     * Loads an image from the specified file path.
     */
    public static BufferedImage getImages(String fileName) {
        BufferedImage image = null;
        InputStream inputStream = Load.class.getResourceAsStream("/" + fileName);
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    /**
     * Retrieves level data from the level image.
     */
    public static int[][] getLevelData() {
        BufferedImage image = getImages(level);
        int[][] levelData = new int[image.getHeight()][image.getWidth()];

        for (int j = 0; j < image.getHeight(); j++) {
            for (int i = 0; i < image.getWidth(); i++) {
                Color color = new Color(image.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48) {
                    value = 11;
                }
                levelData[j][i] = color.getRed();
            }
        }
        return levelData;
    }
}
