package object;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Player;
import gamestates.Playing;
import utilities.Constants;
import utilities.Load;



public class ObjectHandler {

        private Playing playing;
        private BufferedImage spikeImage;
        private ArrayList<Spike> spikes;

    /**
     * Constructor for an ObjectHandler with the specified Playing instance.
     */
        public ObjectHandler(Playing playing) {
            this.playing = playing;
            loadImages();
        }
    /**
     * Checks if the player has touched any spikes.
     */
        public void checkSpikesTouched(Player player) {
            for (Spike s : spikes)
                if (s.getHitbox().intersects(player.getHitbox())){

                }

        }
    /**
     * Loads images for spikes.
     */
        private void loadImages() {
            spikeImage = Load.getImages(Load.spikes);
        }
    /**
     * Draws spikes on the screen.
     */
        private void drawSpikes(Graphics graphics, int xLvlOffset) {
            for (Spike spike : spikes)
                graphics.drawImage(spikeImage, (int) (spike.getHitbox().x - xLvlOffset), (int) (spike.getHitbox().y - spike.getYDrawOffset()),
                        Constants.Ui.ObjectConstants.SpikeWidth, Constants.Ui.ObjectConstants.SpikeHeight, null);

        }
    }

