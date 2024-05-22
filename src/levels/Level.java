package levels;

import main.Game;
import object.Spike;
import utilities.OtherMethods;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Level {

    private int[][] levelData;
    private ArrayList<Spike> spikes;
    private BufferedImage image;
    public Level(BufferedImage image) {
        this.image = image;
        createSpikes();
    }

    public Level(int[][] levelData){
        this.levelData = levelData;
    }

    public int getImageIndex(int x, int y){
        return levelData[y][x];
    }

    public int[][] getLevelData(){
        return levelData;
    }
    private void createSpikes() {
        spikes = OtherMethods.GetSpikes(image);
    }

    public ArrayList<Spike> getSpikes() {
        return spikes;
    }


}
