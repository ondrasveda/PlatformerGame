package gamestates;

import main.Game;
import ui.MenuButton;

import java.awt.event.MouseEvent;

public class State {
    protected Game game;
    public State(Game game){
        this.game = game;
    }
    public boolean pressingOnButton(MouseEvent e, MenuButton menuButton){
        return menuButton.getButtonHitbox().contains(e.getX(), e.getY());
    }

    public Game getGame() {
        return game;
    }
}
