package ui;

import utilities.Constants;
import utilities.Load_Save;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SoundButtons extends PauseButtons {

    private BufferedImage[][] buttonImages;
    private boolean mouseOver;
    private boolean mousePressed;
    private boolean soundOff;
    private int rowIndex;
    private int collumnIndex;

    public SoundButtons(int xPosition, int yPosition, int width, int height) {
        super(xPosition, yPosition, width, height);
        loadButtonImages();
    }

    private void loadButtonImages() {
        BufferedImage temporaryImage = Load_Save.getImages(Load_Save.volumeButtons);
        buttonImages = new BufferedImage[2][3];
        for (int j = 0; j < buttonImages.length; j++) {
            for (int i = 0; i < buttonImages[j].length; i++) {
                buttonImages[j][i] = temporaryImage.getSubimage(i * Constants.Ui.PauseButton.soundButtonDefaultSize, j * Constants.Ui.PauseButton.soundButtonDefaultSize,
                        Constants.Ui.PauseButton.soundButtonDefaultSize, Constants.Ui.PauseButton.soundButtonDefaultSize);

            }
        }
    }

    public void resetButton() {
        mouseOver = false;
        mousePressed = false;
    }

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

    public void draw(Graphics graphics) {
        graphics.drawImage(buttonImages[rowIndex][collumnIndex], xPosition, yPosition, width, height, null);
    }

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
