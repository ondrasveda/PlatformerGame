package utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class Load_Save {

    public static final String playerImage = "player.png";
    public static final String levelImage = "level_textures.png";
    public static final String levelOne = "level_one.png";
    public static final String levelOneWide = "level_one_wide.png";
    public static final String menuButtons = "menu_button_textures.png";
    public static final String pauseBackground = "pause_background.png";
    public static final String volumeButtons = "volume_buttons.png";
    public static final String unpauseRestartMenuButtons = "unpause_restart_menu_buttons.png";
    public static final String menuBgImg = "menu_bg_img.png";
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
        BufferedImage image = getImages(levelOneWide);
        int[][] levelData = new int[image.getHeight()][image.getWidth()];

        for (int j = 0; j < image.getHeight(); j++) {
            for (int i = 0; i < image.getWidth(); i++) {
                Color color = new Color(image.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48) {
                    value = 0;
                }
                levelData[j][i] = color.getRed();
            }
        }
        return levelData;
    }
}
