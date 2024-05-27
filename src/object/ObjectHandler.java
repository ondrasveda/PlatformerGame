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
        private BufferedImage spikeImg;
        private ArrayList<Spike> spikes;

        public ObjectHandler(Playing playing) {
            this.playing = playing;
            loadImages();
        }

        public void checkSpikesTouched(Player player) {
            for (Spike s : spikes)
                if (s.getHitbox().intersects(player.getHitbox())){

                }

        }

        private void loadImages() {
            spikeImg = Load.getImages(Load.spikes);
        }

        private void drawTraps(Graphics g, int xLvlOffset) {
            for (Spike s : spikes)
                g.drawImage(spikeImg, (int) (s.getHitbox().x - xLvlOffset), (int) (s.getHitbox().y - s.getyDrawOffset()),
                        Constants.Ui.ObjectConstants.SpikeWidth, Constants.Ui.ObjectConstants.SpikeHeight, null);

        }
    }

