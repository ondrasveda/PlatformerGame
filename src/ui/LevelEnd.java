package ui;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilities.Constants;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class LevelEnd {
    private BufferedImage background;
    private int backgroundXPosition;
    private int backgroundYPosition;
    private int backgroundWidth;
    private int backgroundHeight;
    private UnpauseRestartMenuButtons menuButton;
    private UnpauseRestartMenuButtons restartButton;
    private Playing playing;

    public LevelEnd(Playing playing) {
        this.playing = playing;
        createRestartMenuButtons();
    }

    private void createRestartMenuButtons() {
        int menuX = 313 * (int) Game.tileScale;
        int restartX = 387 * (int) Game.tileScale;
        int yPos = 325 * (int) Game.tileScale;

        restartButton = new UnpauseRestartMenuButtons(restartX, yPos,
                Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonSize,
                Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonSize, 1);
        menuButton = new UnpauseRestartMenuButtons(menuX, yPos,
                Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonSize,
                Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonSize, 2);
    }


    public void update() {
        menuButton.update();
        restartButton.update();
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(background, backgroundXPosition, backgroundYPosition, backgroundWidth, backgroundHeight, null);

        menuButton.draw(graphics);
        restartButton.draw(graphics);
    }

    private boolean isIn(MouseEvent e, PauseButtons button) {
        if (button.getBounds().contains(e.getX(), e.getY())) {
            return true;
        } else {
            return false;
        }
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, menuButton)) {
            menuButton.setMousePressed(true);
        } else if (isIn(e, restartButton)) {
            restartButton.setMousePressed(true);
        }
    }


    public void mouseReleased(MouseEvent e) {
        if (isIn(e, menuButton)) {
            if (menuButton.isMousePressed()) {
                Gamestate.gamestate = Gamestate.MENU;
                if (playing.isPaused()) {
                    playing.unpauseGame();
                }
            }
        } else if (isIn(e, restartButton)) {
            if (restartButton.isMousePressed()) {
                playing.reset();
            }
        }

        menuButton.resetButton();
        restartButton.resetButton();
    }

    public void mouseMoved(MouseEvent e) {
        menuButton.setMouseOver(false);
        restartButton.setMouseOver(false);

        if (isIn(e, menuButton)) {
            menuButton.setMouseOver(true);
        } else if (isIn(e, restartButton)) {
            restartButton.setMouseOver(true);
        }
    }
}
