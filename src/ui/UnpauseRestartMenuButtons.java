package ui;

import utilities.Constants;
import utilities.Load_Save;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UnpauseRestartMenuButtons extends PauseButtons {
    private BufferedImage[] images;
    private int rowIndex;
    private int index;
    private boolean mouseOver;
    private boolean mousePressed;

    public UnpauseRestartMenuButtons(int xPosition, int yPosition, int width, int height, int rowIndex) {
        super(xPosition, yPosition, width, height);
        this.rowIndex = rowIndex;
        loadImages();
    }

    private void loadImages() {
        BufferedImage temporaryImage = Load_Save.getImages(Load_Save.unpauseRestartMenuButtons);
        images = new BufferedImage[3];
        for (int i = 0; i < images.length; i++) {
            images[i] = temporaryImage.getSubimage(i * Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonDefaultSize,
                    i * Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonDefaultSize,
                    Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonDefaultSize,
                    Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonDefaultSize);
        }
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(images[index], xPosition, yPosition,
                Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonSize,
                Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonSize, null);
    }

    public void update() {
        if(mouseOver){
            index = 1;
        }
        if (mousePressed){
            index = 2;
        }
    }
    public void resetButton(){
        mouseOver = false;
        mousePressed = false;
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
}
