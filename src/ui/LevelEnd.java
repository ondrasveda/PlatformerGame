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

    /**
     * Constructs a LevelEnd object with the specified Playing instance.
     */
    public LevelEnd(Playing playing) {
        this.playing = playing;
        createRestartMenuButtons();
    }
    /**
     * Creates the restart and menu buttons for the level end screen.
     */
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

    /**
     * Updates the level end screen, including button states.
     */
    public void update() {
        menuButton.update();
        restartButton.update();
    }

    /**
     * Draws the level end screen, including buttons and background.
     */
    public void draw(Graphics graphics) {
        graphics.drawImage(background, backgroundXPosition, backgroundYPosition, backgroundWidth, backgroundHeight, null);

        menuButton.draw(graphics);
        restartButton.draw(graphics);
    }
    /**
     * Checks if the mouse event occurs within the specified button.
     */
    private boolean isInButton(MouseEvent e, PauseButtons button) {
        if (button.getButtonBounds().contains(e.getX(), e.getY())) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Handles mouse press events for the level end screen buttons.
     */
    public void mousePressed(MouseEvent e) {
        if (isInButton(e, menuButton)) {
            menuButton.setMousePressed(true);
        } else if (isInButton(e, restartButton)) {
            restartButton.setMousePressed(true);
        }
    }

    /**
     * Handles mouse release events for the level end screen buttons.
     */
    public void mouseReleased(MouseEvent e) {
        if (isInButton(e, menuButton)) {
            if (menuButton.isMousePressed()) {
                Gamestate.gamestate = Gamestate.MENU;
                if (playing.isPaused()) {
                    playing.unpauseGame();
                }
            }
        } else if (isInButton(e, restartButton)) {
            if (restartButton.isMousePressed()) {
                playing.reset();
            }
        }

        menuButton.resetButton();
        restartButton.resetButton();
    }
    /**
     * Handles mouse movement events for the level end screen buttons.
     */
    public void mouseMoved(MouseEvent e) {
        menuButton.setMouseOver(false);
        restartButton.setMouseOver(false);

        if (isInButton(e, menuButton)) {
            menuButton.setMouseOver(true);
        } else if (isInButton(e, restartButton)) {
            restartButton.setMouseOver(true);
        }
    }
}
