package gamestates;

import entities.Player;
import levels.LevelHandler;
import main.Game;
import object.ObjectHandler;
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
    private int levelOffset;
    private int leftBorder = (int) (0.15 * Game.gameWidth);
    private int rightBorder = (int) (0.85 * Game.gameWidth);
    private int levelTileSize = Load_Save.getLevelData()[0].length;
    private int maxTileOffset = levelTileSize - Game.gameTileWidth;
    private int maxPixelOffset = maxTileOffset * Game.tileSize;
    private BufferedImage background;
    private boolean levelCompleted;
    private boolean gameOver;

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

    }


    public Player getPlayer() {
        return player;
    }

    @Override
    public void update() {
        if (!paused) {
            levelHandler.update();
            player.update();
            checkIfPlayerNearBorder();
        } else {
            pauseMenu.update();
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
    }
    public void checkSpikesTouched(Player p) {
        objectHandler.checkSpikesTouched(p);
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
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
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setPlayerAttacking(true);
        }
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
        gameOver = false;
        paused = false;
        levelCompleted = false;
        player.resetAll();
    }
}
