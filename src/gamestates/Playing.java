package gamestates;

import entities.Player;
import levels.LevelHandler;
import main.Game;
import ui.PauseMenu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods {

    private Player player;
    private LevelHandler levelHandler;
    private boolean paused = true;
    private PauseMenu pauseMenu;


    public Playing(Game game) {
        super(game);
        initializeClasses();
    }

    private void initializeClasses() {
        levelHandler = new LevelHandler(game);
        player = new Player(200, 200, (int) (64 * Game.tileScale), (int) (40 * Game.tileScale));
        player.loadLevelData(levelHandler.getCurrentLevel().getLevelData());
        pauseMenu = new PauseMenu(this);
    }


    public Player getPlayer() {
        return player;
    }

    public void focusLost() {
        player.resetDirection();
    }


    @Override
    public void update() {
        levelHandler.update();
        player.update();
        pauseMenu.update();
    }

    @Override
    public void draw(Graphics graphics) {
        levelHandler.draw(graphics);
        player.render(graphics);
        pauseMenu.draw(graphics);
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
    public void unpauseGame(){
        paused = false;
    }
}
