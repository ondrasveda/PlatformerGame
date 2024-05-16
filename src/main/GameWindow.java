package main;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {
    private JFrame jframe = new JFrame();

    public GameWindow(GamePanel gamePanel) {
        this.jframe.setDefaultCloseOperation(3);
        this.jframe.add(gamePanel);
        this.jframe.setLocationRelativeTo((Component)null);
        this.jframe.setResizable(false);
        this.jframe.pack();
        this.jframe.setVisible(true);
        this.jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().focusLost();
            }
        });


        }
    }



