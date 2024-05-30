package inputs;

import gamestates.Gamestate;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    /**
     * Constructor for the KeyboardInputs object.
     */
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Handles key released events, directing the event based on the current game state.
     */
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.gamestate){
            case MENU ->
                gamePanel.getGame().getMenu().keyReleased(e);
            case PLAYING ->
                gamePanel.getGame().getPlaying().keyReleased(e);
        }

    }
    /**
     * Handles key pressed events, directing the event based on the current game state.
     */
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.gamestate){
            case MENU ->
                gamePanel.getGame().getMenu().keyPressed(e);
            case PLAYING ->
                gamePanel.getGame().getPlaying().keyPressed(    e);
        }

    }
    public void keyTyped(KeyEvent e) {
    }
}