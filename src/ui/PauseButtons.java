package ui;

import java.awt.*;

public class PauseButtons{

    protected int xPosition;
    protected int yPosition;
    protected int width;
    protected int height;
    protected Rectangle bounds;

    public PauseButtons(int xPosition, int yPosition, int width, int height) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        createBounds();
    }

    private void createBounds() {
        bounds = new Rectangle(xPosition,yPosition,width,height);
    }


    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
