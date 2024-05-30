package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {

/**
 * variables out of classes
 * used to access properties and methods of those classes
 */
    private MouseInputs mouseInputs;
    private Game game;

    /**
     * constructor for GamePanel
     * calls for panelsize
     * initializes game, mouseinputs and keyinputs objects
     */
    public GamePanel(Game game) {
        setPanelSize();
        this.game = game;
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    /**
     * Sets the preferred size of the game panel
     */
    private void setPanelSize() {
        Dimension panelSize = new Dimension(Game.windowWidth, Game.windowHeight);
        setPreferredSize(panelSize);
        System.out.println("size: " + Game.windowWidth + "x" + Game.windowHeight);
    }

    /**
     * Paints the game panel by calling the render method of the game object.
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        game.render(graphics);
    }

    /**
     * getter for game
     * returng game object
     */
    public Game getGame() {
        return game;
    }
}
