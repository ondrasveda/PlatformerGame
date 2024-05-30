package gamestates;

import entities.Player;
import levels.LevelHandler;
import main.Game;
import object.ObjectHandler;
import ui.LevelEnd;
import ui.PauseMenu;
import utilities.Load;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Playing extends State implements StateMethods {

    private Player player;
    private LevelHandler levelHandler;
    private ObjectHandler objectHandler;
    private boolean paused = false;
    private PauseMenu pauseMenu;
    private LevelEnd levelEnd;
    private int levelOffset;
    private int leftBorder = (int) (0.25 * Game.windowWidth);
    private int rightBorder = (int) (0.75 * Game.windowWidth);
    private int levelTileSize = Load.getLevelData()[0].length;
    private int maxTileOffset = levelTileSize - Game.windowTileWidth;
    private int maxPixelOffset = maxTileOffset * Game.tileSize;
    private BufferedImage background;
    private boolean levelEndBoolean = false;

    /**
     * Constructs a new Playing object.
     */
    public Playing(Game game) {
        super(game);
        initializeClasses();
        background = Load.getImages(Load.levelBackground);
    }
    /**
     * Initializes all the classes used in the playing state.
     */
    private void initializeClasses() {
        levelHandler = new LevelHandler(game);
        player = new Player(200, 150, (int) (64 * Game.tileScale), (int) (40 * Game.tileScale));
        player.loadLevelData(levelHandler.getCurrentLevel().getLevelData());
        pauseMenu = new PauseMenu(this);
        levelEnd = new LevelEnd(this);

    }
    /**
     * Updates the state of the game.
     */
    @Override
    public void update() {
        if (!paused && !levelEndBoolean) {
            levelHandler.update();
            player.updatePlayer();
            checkIfPlayerNearBorder();
        } else if(paused && !levelEndBoolean){
            pauseMenu.update();
        }else if(!paused && levelEndBoolean){
            levelEnd.update();
        }
    }

    /**
     * Checks if the player is near the screen borders and adjusts the level offset accordingly.
     */
    private void checkIfPlayerNearBorder() {
        int playerXPosition = (int) player.getHitbox().x;
        int difference = playerXPosition - levelOffset;
        if (difference > rightBorder) {
            levelOffset += difference - rightBorder;
        } else if (difference < leftBorder) {
            levelOffset += difference - leftBorder;
        }

        if (levelOffset > maxPixelOffset) {
            levelOffset = maxPixelOffset;
        } else if (levelOffset < 0) {
            levelOffset = 0;
        }
        if(levelOffset == maxPixelOffset){
            levelEndBoolean = true;
            levelEnd.draw(background.getGraphics());
        }
    }
    /**
     * Checks if the player has touched spikes.
     */
    public void checkSpikesTouched(Player p) {
        objectHandler.checkSpikesTouched(p);
    }

    /**
     * Draws the playing gamestate on the screen.
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(background, 0, 0, Game.windowWidth, Game.windowHeight, null);

        levelHandler.draw(graphics, levelOffset);
        player.renderPlayer(graphics, levelOffset);
        if (paused) {
            pauseMenu.draw(graphics);
        }
    }
    /**
     * Unpauses the game.
     */
    public void unpauseGame() {
        paused = false;
    }
    /**
     * Checks if the game is paused and returns true/false accordingly.
     */
    public boolean isPaused() {
        return paused;
    }
    /**
     * Resets the game state.
     */
    public void reset() {
        paused = false;
        levelEndBoolean = false;
        player.resetAll();
    }
    /**
     * Handles mouse clicked events.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Handles mouse pressed events.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (paused) {
            pauseMenu.mousePressed(e);
        }
    }

    /**
     * Handles mouse released events.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused) {
            pauseMenu.mouseReleased(e);
        }
    }
    /**
     * Handles mouse moved events.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused) {
            pauseMenu.mouseMoved(e);
        }
    }

    /**
     * Handles key pressed events.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                if (!paused) {
                    paused = true;
                    break;
                } else if (paused) {
                    paused = false;
                    break;
                }
        }
    }

    /**
     * Handles key released events.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }
    }


}
