package levels;


import object.Spike;
import utilities.OtherMethods;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Level {


    private int[][] levelData;
    private ArrayList<Spike> spikes;
    private BufferedImage levelImage;

    public Level(BufferedImage image) {
        this.levelImage = image;
        createSpikes();
    }

    /**
     * Constructor for the Level object from level data.
     */
    public Level(int[][] levelData){
        this.levelData = levelData;
    }

    /**
     * Gets the index of the image at the specified position.
     */
    public int getImageIndex(int x, int y){
        return levelData[y][x];
    }

    /**
     * Gets the level data.
     */
    public int[][] getLevelData(){
        return levelData;
    }

    /**
     * Creates spikes based on the level's image.
     */
    private void createSpikes() {
        spikes = OtherMethods.GetSpikes(levelImage);
    }

    /**
     * Gets the list of spikes in the level.
     */
    public ArrayList<Spike> getSpikes() {
        return spikes;
    }
}
