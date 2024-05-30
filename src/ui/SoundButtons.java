package ui;

import utilities.Constants;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SoundButtons extends PauseButtons {

    private BufferedImage[][] buttonImages;
    private boolean mouseOver;
    private boolean mousePressed;
    private boolean soundOff;
    private int rowIndex;
    private int collumnIndex;

    /**
     * Constructs a SoundButtons object with the specified position and dimensions.
     */
    public SoundButtons(int xPosition, int yPosition, int width, int height) {
        super(xPosition, yPosition, width, height);
        loadButtonImages();
    }

    /**
     * Loads the images for the sound buttons from the resources.
     */
    private void loadButtonImages() {
        BufferedImage temporaryImage = Load.getImages(Load.volumeButtons);
        buttonImages = new BufferedImage[2][3];
        for (int j = 0; j < buttonImages.length; j++) {
            for (int i = 0; i < buttonImages[j].length; i++) {
                buttonImages[j][i] = temporaryImage.getSubimage(i * Constants.Ui.PauseButton.soundButtonDefaultSize, j * Constants.Ui.PauseButton.soundButtonDefaultSize,
                        Constants.Ui.PauseButton.soundButtonDefaultSize, Constants.Ui.PauseButton.soundButtonDefaultSize);

            }
        }
    }

    /**
     * Resets the mouse-over and mouse-pressed states of the button.
     */
    public void resetButton() {
        mouseOver = false;
        mousePressed = false;
    }

    /**
     * Updates the state of the button based on mouse interactions and sound settings.
     */
    public void update() {
        collumnIndex = 0;

        if (soundOff) {
            rowIndex = 1;
        } else {
            rowIndex = 0;
        }
        if (mouseOver) {
            collumnIndex = 1;
        }
        if (mousePressed) {
            collumnIndex = 2;
        }
    }
    /**
     * Draws the sound button using the current state and images.
     */
    public void draw(Graphics graphics) {
        graphics.drawImage(buttonImages[rowIndex][collumnIndex], buttonXPosition, buttonYPosition, buttonWidth, buttonHeight, null);
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

    public boolean isSoundOff() {
        return soundOff;
    }

    public void setSoundOff(boolean soundOff) {
        this.soundOff = soundOff;
    }
}
