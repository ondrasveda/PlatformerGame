package ui;

import gamestates.Gamestate;
import utilities.Constants;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton {
    private int xPos;
    private int yPos;
    private int rowIndex;
    private int index;
    private int xOffset = Constants.Ui.MenuButton.buttonWidth/2;
    private boolean mouseOver;
    private boolean mousePressed;
    private Rectangle buttonHitbox;
    private Gamestate gamestate;
    private BufferedImage[] images;

    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate gamestate) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.gamestate = gamestate;
        loadImages();
        initializeMouseHitbox();
    }

    private void initializeMouseHitbox() {
        buttonHitbox = new Rectangle(xPos - xOffset, yPos, Constants.Ui.MenuButton.buttonWidth, Constants.Ui.MenuButton.buttonHeight);
    }

    private void loadImages() {
        images = new BufferedImage[3];
        BufferedImage tempSave = Load.getImages(Load.menuButtons);
        for(int i = 0; i < images.length; i++){
            images[i] = tempSave.getSubimage(i * Constants.Ui.MenuButton.buttonDefaultWidth, rowIndex * Constants.Ui.MenuButton.buttonDefaultHeight,
                    Constants.Ui.MenuButton.buttonDefaultWidth, Constants.Ui.MenuButton.buttonDefaultHeight);
        }
    }
    public void draw(Graphics graphics){
        graphics.drawImage(images[index], xPos - xOffset, yPos, Constants.Ui.MenuButton.buttonWidth, Constants.Ui.MenuButton.buttonHeight, null);
    }
    public void update(){
        index = 0;
        if(mouseOver){
            index = 1;
        }else if(mousePressed){
            index = 2;
        }
    }
    public void setGamestate(){
        Gamestate.gamestate = gamestate;
    }
    public void resetBooleans(){
        mouseOver = false;
        mousePressed = false;
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

    public Rectangle getButtonHitbox() {
        return buttonHitbox;
    }
}
