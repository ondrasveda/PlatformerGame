package main;

import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;
import levels.LevelHandler;
import entities.Player;

import java.awt.*;


public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameLoop;
    private final int FPS = 120;
    private final int UPS = 100;
    private Player player;
    private LevelHandler levelHandler;
    private Playing playing;
    private Menu menu;

    public static final int defaultTileSize = 32;
    public static final float tileScale = 1.0f;
    public static final int gameTileWidth = 26; //40
    public static final int gameTileHeight = 14; //23
    public static final int tileSize = (int) (defaultTileSize * tileScale);
    public static final int gameWidth = tileSize * gameTileWidth;
    public static final int gameHeight = tileSize * gameTileHeight;

    public Game() {
        initializeClasses();


        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initializeClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop() {
        this.gameLoop = new Thread(this);
        this.gameLoop.start();
    }

    public void update() {
        switch (Gamestate.gamestate) {
            case MENU -> {
                menu.update();
            }
            case PLAYING -> {
                playing.update();
            }
            case OPTIONS -> {

            }
            case QUIT -> {
                System.exit(0);
            }
        }
    }

    public void render(Graphics graphics) {
        switch (Gamestate.gamestate) {
            case MENU -> {
                menu.draw(graphics);
            }
            case PLAYING -> {
                playing.draw(graphics);
            }
        }

    }


    public void run() {
        double timePerFrame = 8333333.333333333;
        double timePerUpdate = 10000000.0;


        long previousTime = System.nanoTime();
        double updateDelay = 0;
        double framesDelay = 0;

        int frames = 0;
        int updates = 0;
        long lastFrameCheck = System.currentTimeMillis();

        while (true) {
            long loopCurrTime = System.nanoTime();
            updateDelay += (double) (loopCurrTime - previousTime) / timePerUpdate;
            framesDelay += (double) (loopCurrTime - previousTime) / timePerFrame;
            previousTime = loopCurrTime;
            if (updateDelay >= 1.0) {
                update();
                updates++;
                updateDelay--;
            }

            if (framesDelay >= 1.0) {
                gamePanel.repaint();
                frames++;
                framesDelay--;
            }

            if (System.currentTimeMillis() - lastFrameCheck >= 1000L) {
                lastFrameCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public void focusLost() {
        if (Gamestate.gamestate == Gamestate.PLAYING) {
            playing.getPlayer().resetDirection();
        }
    }
}