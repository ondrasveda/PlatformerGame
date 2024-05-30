package inputs;

import gamestates.Gamestate;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    /**
     * Constructs a new MouseInputs object.
     */
    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    /**
     * Handles mouse clicked events. This method is currently not implemented.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    /**
     * Handles mouse pressed events, directing the event based on the current game state.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestate.gamestate){
            case MENU -> gamePanel.getGame().getMenu().mousePressed(e);
            case PLAYING -> gamePanel.getGame().getPlaying().mousePressed(e);
        }
    }
    /**
     * Handles mouse released events, directing the event based on the current game state.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.gamestate){
            case MENU -> gamePanel.getGame().getMenu().mouseReleased(e);
            case PLAYING -> gamePanel.getGame().getPlaying().mouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
    /**
     * Handles mouse moved events, directing the event to the appropriate
     * game state based on the current game state.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.gamestate){
            case MENU -> gamePanel.getGame().getMenu().mouseMoved(e);
            case PLAYING -> gamePanel.getGame().getPlaying().mouseMoved(e);
        }
    }
}
