package gamestates;

import entities.Player;
import levels.LevelHandler;
import main.Game;
import object.ObjectHandler;
import ui.LevelEnd;
import ui.PauseMenu;
import utilities.Load_Save;

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
    private int leftBorder = (int) (0.25 * Game.gameWidth);
    private int rightBorder = (int) (0.75 * Game.gameWidth);
    private int levelTileSize = Load_Save.getLevelData()[0].length;
    private int maxTileOffset = levelTileSize - Game.gameTileWidth;
    private int maxPixelOffset = maxTileOffset * Game.tileSize;
    private BufferedImage background;
    private boolean levelEndBoolean = false;


    public Playing(Game game) {
        super(game);
        initializeClasses();
        background = Load_Save.getImages(Load_Save.levelBackground);
    }

    private void initializeClasses() {
        levelHandler = new LevelHandler(game);
        player = new Player(200, 150, (int) (64 * Game.tileScale), (int) (40 * Game.tileScale));
        player.loadLevelData(levelHandler.getCurrentLevel().getLevelData());
        pauseMenu = new PauseMenu(this);
        levelEnd = new LevelEnd(this);

    }


    public Player getPlayer() {
        return player;
    }

    @Override
    public void update() {
        if (!paused && !levelEndBoolean) {
            levelHandler.update();
            player.update();
            checkIfPlayerNearBorder();
        } else if(paused && !levelEndBoolean){
            pauseMenu.update();
        }else if(!paused && levelEndBoolean){
            levelEnd.update();
        }
    }

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
        }
    }
    public void checkSpikesTouched(Player p) {
        objectHandler.checkSpikesTouched(p);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(background, 0, 0, Game.gameWidth, Game.gameHeight, null);

        levelHandler.draw(graphics, levelOffset);
        player.render(graphics, levelOffset);
        if (paused) {
            pauseMenu.draw(graphics);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused) {
            pauseMenu.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused) {
            pauseMenu.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused) {
            pauseMenu.mouseMoved(e);
        }
    }

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

    public void unpauseGame() {
        paused = false;
    }

    public boolean isPaused() {
        return paused;
    }

    public void reset() {
        paused = false;
        levelEndBoolean = false;
        player.resetAll();
    }
}
