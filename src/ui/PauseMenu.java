package ui;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilities.Constants;
import utilities.Load;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PauseMenu {
    private BufferedImage background;
    private int backgroundXPosition;
    private int backgroundYPosition;
    private int backgroundWidth;
    private int backgroundHeight;
    private SoundButtons musicOnOffButton;
    private SoundButtons sfxOnOffButton;
    private UnpauseRestartMenuButtons menuButton;
    private UnpauseRestartMenuButtons restartButton;
    private UnpauseRestartMenuButtons unpauseButton;
    private Playing playing;

    public PauseMenu(Playing playing) {
        this.playing = playing;
        loadBackground();
        drawPauseButtons();
        createUnpauseRestartMenuButtons();
    }

    private void createUnpauseRestartMenuButtons() {
        int menuX = 313 * (int) Game.tileScale;
        int restartX = 387 * (int) Game.tileScale;
        int unpauseX = 462 * (int) Game.tileScale;
        int yPos = 325 * (int) Game.tileScale;

        unpauseButton = new UnpauseRestartMenuButtons(unpauseX, yPos,
                Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonSize,
                Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonSize, 0);
        restartButton = new UnpauseRestartMenuButtons(restartX, yPos,
                Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonSize,
                Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonSize, 1 );
        menuButton = new UnpauseRestartMenuButtons(menuX, yPos,
                Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonSize,
                Constants.Ui.UnpauseRestartMenuButton.unpauseRestartMenuButtonSize, 2 );
    }

    private void drawPauseButtons() {
        int soundXPosition = 450 * (int) Game.tileScale;
        int musicYPosition = 165 * (int) Game.tileScale;
        int sfxYPosition = 211 * (int) Game.tileScale;
        musicOnOffButton = new SoundButtons(soundXPosition, musicYPosition,
                Constants.Ui.PauseButton.soundButtonSize, Constants.Ui.PauseButton.soundButtonSize);
        sfxOnOffButton = new SoundButtons(soundXPosition, sfxYPosition,
                Constants.Ui.PauseButton.soundButtonSize, Constants.Ui.PauseButton.soundButtonSize);
    }

    private void loadBackground() {
        background = Load.getImages(Load.pauseBackground);
        backgroundWidth = background.getWidth() * (int) Game.tileScale;
        backgroundHeight = background.getHeight() * (int) Game.tileScale;
        backgroundXPosition = Game.gameWidth / 2 - backgroundWidth / 2;
        backgroundYPosition = 50 * (int) Game.tileScale;
    }

    public void update() {
        musicOnOffButton.update();
        sfxOnOffButton.update();

        menuButton.update();
        restartButton.update();
        unpauseButton.update();
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(background, backgroundXPosition, backgroundYPosition, backgroundWidth, backgroundHeight, null);

        musicOnOffButton.draw(graphics);
        sfxOnOffButton.draw(graphics);

        menuButton.draw(graphics);
        restartButton.draw(graphics);
        unpauseButton.draw(graphics);
    }
    private boolean isIn(MouseEvent e, PauseButtons button){
        if(button.getBounds().contains(e.getX(),e.getY())){
            return true;
        }else{
            return false;
        }
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicOnOffButton)){
            musicOnOffButton.setMousePressed(true);
        }else if(isIn(e,sfxOnOffButton)){
            sfxOnOffButton.setMousePressed(true);
        }else if(isIn(e,menuButton)){
            menuButton.setMousePressed(true);
        }else if(isIn(e,restartButton)){
            restartButton.setMousePressed(true);
        }else if(isIn(e,unpauseButton)){
            unpauseButton.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e, musicOnOffButton)){
            if(musicOnOffButton.isMousePressed()){
                musicOnOffButton.setSoundOff(!musicOnOffButton.isSoundOff());
            }
        }else if(isIn(e,sfxOnOffButton)){
            if(sfxOnOffButton.isMousePressed()){
                sfxOnOffButton.setSoundOff(!sfxOnOffButton.isSoundOff());
            }
        }else if(isIn(e,menuButton)){
            if(menuButton.isMousePressed()){
                Gamestate.gamestate = Gamestate.MENU;
                if(playing.isPaused()){
                    playing.unpauseGame();
                }
            }
        }else if(isIn(e,restartButton)){
            if(restartButton.isMousePressed()){
                playing.reset();
            }
        }else if(isIn(e,unpauseButton)){
            if(unpauseButton.isMousePressed()){
                playing.unpauseGame();
            }
        }
        musicOnOffButton.resetButton();
        sfxOnOffButton.resetButton();
        menuButton.resetButton();
        restartButton.resetButton();
        unpauseButton.resetButton();
    }

    public void mouseMoved(MouseEvent e) {
        musicOnOffButton.setMouseOver(false);
        sfxOnOffButton.setMouseOver(false);
        menuButton.setMouseOver(false);
        restartButton.setMouseOver(false);
        unpauseButton.setMouseOver(false);

        if (isIn(e, musicOnOffButton)){
            musicOnOffButton.setMouseOver(true);
        }else if(isIn(e,sfxOnOffButton)){
            sfxOnOffButton.setMouseOver(true);
        }else if(isIn(e,menuButton)){
            menuButton.setMouseOver(true);
        }else if(isIn(e,restartButton)){
            restartButton.setMouseOver(true);
        }else if(isIn(e,unpauseButton)){
            unpauseButton.setMouseOver(true);
        }
    }

}
