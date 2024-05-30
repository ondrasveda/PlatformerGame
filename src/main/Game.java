package main;

import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;

import java.awt.*;


public class Game implements Runnable {
    /**
     * variables out of classes
     * used to access properties and methods of those classes
     */
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Playing playing;
    private Menu menu;

    private Thread gameLoop;

    public static final int windowTileWidth = 26;
    public static final int windowTileHeight = 14;

    public static final int defaultTileSize = 32;
    public static final float tileScale = 1.0f;
    public static final int tileSize = (int) (defaultTileSize * tileScale);
    public static final int windowWidth = tileSize * windowTileWidth;
    public static final int windowHeight = tileSize * windowTileHeight;

    /**
     * calls to create the gamewindow and panel
     * calls for menu and playing classes initialization
     * calls for the start of the gameloop
     */
    public Game() {
        initializeClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();

        startGameLoop();
    }

    /**
     * creates instances of menu and playing classes
     */
    private void initializeClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    /**
     * starts the gameloop
     */
    private void startGameLoop() {
        this.gameLoop = new Thread(this);
        this.gameLoop.start();
    }

    /**
     * Depending on the value of "Gamestate.gamestate", one of the four cases
     * is executed which updates the current game state by calling the appropriate update method of the corresponding object
     */
    public void update() {
        switch (Gamestate.gamestate) {
            case MENU -> menu.update();
            case PLAYING -> playing.update();
            case OPTIONS -> {

            }
            case QUIT -> System.exit(0);

        }
    }

    /**
     * Renders the current game state by calling the appropriate draw method of the corresponding object
     */

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

    /**
     * Runs the game loop that updates and renders the game at a fixed rate
     * The method uses a while loop to continuously update and render the game
     * It calculates the time elapsed since the last update and render, and updates and renders the game at a fixed rate.
     * It also calculates and prints the frames per second (FPS) and updates per second (UPS) every second
     */
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
                System.out.println("Frames per second (FPS): " + frames + " | Updates per second (UPS): " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    /**
     * getters for menu and playing
     * returns menu and playing objects
     */
    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
}