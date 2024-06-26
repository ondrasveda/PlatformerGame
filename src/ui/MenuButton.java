package ui;

import gamestates.Gamestate;
import utilities.Constants;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton {
    private int xPos;
    private int yPos;
    private int xOffset = Constants.Ui.MenuButton.buttonWidth/2;

    private BufferedImage[] buttonImages;
    private int rowIndex;
    private int index;

    private boolean mouseOver;
    private boolean mousePressed;

    private Rectangle buttonHitbox;
    private Gamestate gamestate;


    /**
     * Constructs a MenuButton with the specified parameters.
     */
    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate gamestate) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.gamestate = gamestate;
        loadImages();
        initializeMouseHitbox();
    }
    /**
     * Initializes the hitbox of the button for mouse interaction.
     */
    private void initializeMouseHitbox() {
        buttonHitbox = new Rectangle(xPos - xOffset, yPos, Constants.Ui.MenuButton.buttonWidth, Constants.Ui.MenuButton.buttonHeight);
    }

    /**
     * Loads images for the button from the sprite sheet.
     */
    private void loadImages() {
        buttonImages = new BufferedImage[3];
        BufferedImage tempSave = Load.getImages(Load.menuButtons);
        for(int i = 0; i < buttonImages.length; i++){
            buttonImages[i] = tempSave.getSubimage(i * Constants.Ui.MenuButton.buttonDefaultWidth, rowIndex * Constants.Ui.MenuButton.buttonDefaultHeight,
                    Constants.Ui.MenuButton.buttonDefaultWidth, Constants.Ui.MenuButton.buttonDefaultHeight);
        }
    }
    /**
     * Draws the button on the screen.
     */
    public void draw(Graphics graphics){
        graphics.drawImage(buttonImages[index], xPos - xOffset, yPos, Constants.Ui.MenuButton.buttonWidth, Constants.Ui.MenuButton.buttonHeight, null);
    }
    /**
     * Updates the button's state.
     */
    public void update(){
        index = 0;
        if(mouseOver){
            index = 1;
        }else if(mousePressed){
            index = 2;
        }
    }
    /**
     * Sets the gamestate based on the button.
     */
    public void setGamestate(){
        Gamestate.gamestate = gamestate;
    }
    /**
     * Resets the mouse over and mouse pressed booleans for the button.
     */
    public void resetBooleans(){
        mouseOver = false;
        mousePressed = false;
    }

    /**
     * getters/setters
     */
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
