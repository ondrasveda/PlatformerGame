package gamestates;

import main.Game;
import ui.MenuButton;

import java.awt.event.MouseEvent;

public class State {
    protected Game game;
    /**
     * Constructor for the State object.
     */
    public State(Game game){
        this.game = game;
    }
    /**
     * Checks if a given mouse event is pressing on a specified menu button.
    */
    public boolean pressingOnButton(MouseEvent e, MenuButton menuButton){
        return menuButton.getButtonHitbox().contains(e.getX(), e.getY());
    }
    public Game getGame() {
        return game;
    }
}
