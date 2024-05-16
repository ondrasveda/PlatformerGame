package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs = new MouseInputs(this);
    private Game game;

    public GamePanel(Game game) {
        setPanelSize();
        this.game = game;
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension panelSize = new Dimension(Game.gameWidth, Game.gameHeight);
        setPreferredSize(panelSize);
        System.out.println("size: " + Game.gameWidth + "x" + Game.gameHeight);
    }

    public void updateGame() {

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        game.render(graphics);

    }

    public Game getGame() {
        return game;
    }
}
