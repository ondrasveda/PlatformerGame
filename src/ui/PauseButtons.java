package ui;

import java.awt.*;

public class PauseButtons{

    protected int buttonXPosition;
    protected int buttonYPosition;
    protected int buttonWidth;
    protected int buttonHeight;
    protected Rectangle buttonBounds;

    /**
     * Constructs a PauseButtons object with the specified parameters.
     */
    public PauseButtons(int buttonXPosition, int buttonYPosition, int buttonWidth, int buttonHeight) {
        this.buttonXPosition = buttonXPosition;
        this.buttonYPosition = buttonYPosition;
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;
        createButtonBounds();
    }
    /**
     * Creates the bounding rectangle of the button based on its position and dimensions.
     */
    private void createButtonBounds() {
        buttonBounds = new Rectangle(buttonXPosition, buttonYPosition, buttonWidth, buttonHeight);
    }

    /**
     * getter for button bounds
     */
    public Rectangle getButtonBounds() {
        return buttonBounds;
    }

}
