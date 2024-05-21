package gamestates;

import main.Game;
import ui.MenuButton;
import utilities.Load_Save;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements StateMethods {

    private MenuButton[] buttons = new MenuButton[3];
    private int menuX;
    private int menuY;
    private int menuWidth;
    private int menuHeight;
    private BufferedImage menuBackground;


    public Menu(Game game) {
        super(game);
        loadButtons();
        menuBackground = Load_Save.getImages(Load_Save.menuBgImg);
    }


    private void loadButtons() {
        buttons[0] = new MenuButton(Game.gameWidth / 2, (int) (100 * Game.tileScale), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.gameWidth / 2, (int) (170 * Game.tileScale), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Game.gameWidth / 2, (int) (240 * Game.tileScale), 2, Gamestate.QUIT);
    }

    @Override
    public void update() {
        for (MenuButton menuButton : buttons) {
            menuButton.update();
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(menuBackground, 0, 0, Game.gameWidth, Game.gameHeight, null);
        for (MenuButton menuButton : buttons) {
            menuButton.draw(graphics);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton menuButton : buttons) {
            if (pressingOnButton(e, menuButton)) {
                menuButton.setMousePressed(true);
                break;
            }
        }
    }

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

    private void resetButtons() {
        for (MenuButton menuButton : buttons) {
            menuButton.resetBooleans();
        }
    }

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

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.gamestate = Gamestate.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
