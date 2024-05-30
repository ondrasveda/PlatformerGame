package gamestates;

import main.Game;
import ui.MenuButton;
import utilities.Load;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements StateMethods {

    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage menuBackground;

    /**
     * constructor for the menu object.
     */
    public Menu(Game game) {
        super(game);
        loadButtons();
        menuBackground = Load.getImages(Load.menuBackgroundImage);
    }

    /**
     * Loads the buttons for the menu.
     * This method initializes the buttons array with specific positions and actions.
     */
    private void loadButtons() {
        buttons[0] = new MenuButton(Game.windowWidth / 2, (int) (100 * Game.tileScale), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.windowWidth / 2, (int) (170 * Game.tileScale), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Game.windowWidth / 2, (int) (240 * Game.tileScale), 2, Gamestate.QUIT);
    }

    /**
     * Updates the state of the menu buttons.
     */
    @Override
    public void update() {
        for (MenuButton menuButton : buttons) {
            menuButton.update();
        }
    }

    /**
     * Draws the menu on the screen.
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(menuBackground, 0, 0, Game.windowWidth, Game.windowHeight, null);
        for (MenuButton menuButton : buttons) {
            menuButton.draw(graphics);
        }
    }
    /**
     * Resets the state of all buttons.
     */
    private void resetButtons() {
        for (MenuButton menuButton : buttons) {
            menuButton.resetBooleans();
        }
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
        for (MenuButton menuButton : buttons) {
            if (pressingOnButton(e, menuButton)) {
                menuButton.setMousePressed(true);
                break;
            }
        }
    }
    /**
     * Handles mouse released events.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton menuButton : buttons) {
            if (pressingOnButton(e, menuButton)) {
                if (menuButton.isMousePressed()) {
                    menuButton.setGamestate();
                    break;
                }
            }
        }
        resetButtons();
    }
    /**
     * Handles mouse moved events.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton menuButton : buttons) {
            menuButton.setMouseOver(false);
        }
        for (MenuButton menuButton : buttons) {
            if (pressingOnButton(e, menuButton)) {
                menuButton.setMouseOver(true);
                break;
            }
        }
    }
    /**
     * Handles key pressed events.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.gamestate = Gamestate.PLAYING;
        }
    }
    /**
     * Handles key released events.
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
