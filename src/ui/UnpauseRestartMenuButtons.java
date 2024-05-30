package ui;

import utilities.Constants;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UnpauseRestartMenuButtons extends PauseButtons {
    private BufferedImage[][] images;
    private int rowIndex;
    private int index;
    private boolean mouseOver;
    private boolean mousePressed;

    /**
     * Constructs an UnpauseRestartMenuButtons object with the specified position, width, height, and row index.
     */
    public UnpauseRestartMenuButtons(int xPosition, int yPosition, int width, int height, int rowIndex) {
        super(xPosition, yPosition, width, height);
        this.rowIndex = rowIndex;
        loadImages();
    }

    /**
     * Loads the images for different button states.
     */
    private void loadImages() {
        BufferedImage temporaryImage = Load.getImages(Load.unpauseRestartMenuButtons);
        images = new BufferedImage[3][3];
        for (int j = 0; j < images.length; j++) {
            for(int i = 0; i<images[j].length; i++) {

                images[j][i] = temporaryImage.getSubimage(j * 56, i * 56, 56, 56);
            }

        }
    }

    /**
     * Draws the button's current image based on its state.
     */
    public void draw(Graphics graphics) {
        graphics.drawImage(images[index][rowIndex], buttonXPosition, buttonYPosition,
                Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonSize,
                Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonSize, null);
    }

    /**
     * Updates the button's state based on mouse interaction.
     */
    public void update() {
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
        if (!mouseOver && !mousePressed) {
            index = 0;
        }
    }

    /**
     * Resets the button's mouseOver and mousePressed states to false.
     */
    public void resetButton() {
        mouseOver = false;
        mousePressed = false;
    }

    /**
     * getters/setters
     */
    public boolean isMouseOver() {
        return mouseOver;
    }
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    public boolean isMousePressed() {
        return mousePressed;
    }
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
