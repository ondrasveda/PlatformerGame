package main;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame jframe = new JFrame();

    /**
     * The constructor initializes the JFrame object and sets its properties
     * It adds the game panel to the JFrame and sets the JFrame to be visible
     * Fits the window perfectly on the gamepanel
     */
    public GameWindow(GamePanel gamePanel) {
        jframe.setDefaultCloseOperation(3);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setVisible(true);
    }
}



