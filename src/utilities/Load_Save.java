package utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class Load_Save {

    public static final String playerImage = "player.png";
    public static final String levelTextures = "level_textures.png"; //free-game-assets.itch.io/free-green-zone-tileset-pixel-art
    public static final String level = "level.png";
    public static final String menuButtons = "menu_button_textures.png";
    public static final String pauseBackground = "pause_background.png";
    public static final String volumeButtons = "volume_buttons.png";
    public static final String unpauseRestartMenuButtons = "unpause_restart_menu_buttons.png";
    public static final String menuBgImg = "menu_background.png";
    public static final String levelBackground = "level_background.png";
    public static final String spikes = "spikes.png";


    public static BufferedImage getImages(String fileName) {
        BufferedImage image = null;
        InputStream inputStream = Load_Save.class.getResourceAsStream("/" + fileName);
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
