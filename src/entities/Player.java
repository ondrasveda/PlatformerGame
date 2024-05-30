package entities;

import main.Game;
import utilities.Constants;
import utilities.Load;
import utilities.OtherMethods;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {


    private BufferedImage[][] animations;
    /**
     * The current image being displayed. This is a single frame from the animations array.
     */
    private BufferedImage image;
    /**
     * The tick counter for the animation. This increases every update cycle and is used to determine
     * when to advance to the next frame in the current animation.
     */
    private int animationTick;
    /**
     * The current index of the animation frame within the current action's animation.
     * This determines which frame of the animation is currently being displayed.
     */
    private int animationIndex;
    /**
     * The speed of the animation, indicating how many ticks should pass before advancing to the next frame.
     * A lower value means a faster animation.
     */
    private int animationSpeed = 10;
    /**
     * The current action of the player, which determines which row of the animations array is used.
     */
    private int playerAction = 0;

    /**
     * all player movement related variables
     */
    private boolean left;
    private boolean right;
    private boolean playerMoving = false;
    private float playerSpeed = 2.0f * Game.tileScale;
    private float xSpeed = 0;
    private boolean jump;
    private float airSpeed = 0f;
    private float gravity = 0.1f * Game.tileScale;
    private float jumpSpeed = -4f * Game.tileScale;
    private float fallSpeedAfterCollisionWithRoof = 0.5f * Game.tileScale;
    private boolean playerInAir = false;

    /**
     * 2D array used to store the data from level.png
     */
    private int[][] levelData;
    private float xHitboxOffset = 20 * Game.tileScale;
    private float yHitboxOffset = 11 * Game.tileScale;

    /**
     * constructor for the player object
     */
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimation();
        initializeHitbox(x, y, (int) (22 * Game.tileScale), (int) (27 * Game.tileScale));
    }

    /**
     * Updates the player's state, including position, animation tick, and current animation.
     */
    public void updatePlayer() {
        this.updatePlayerPosition();
        this.updateAnimationTick();
        this.setAnimation();

    }

    /**
     * Renders the player on the screen.
     */
    public void renderPlayer(Graphics graphics, int levelOffset) {
        graphics.drawImage(animations[playerAction][animationIndex],
                (int) (hitbox.x - xHitboxOffset) - levelOffset, (int) (hitbox.y - yHitboxOffset), hitboxWidth, hitboxHeight, null);
    }

    /**
     * Loads the animations for the player. This method initializes the animations array.
     */
    private void loadAnimation() {
        image = Load.getImages(Load.playerImage);
        this.animations = new BufferedImage[4][8];
        for (int j = 0; j < this.animations.length; j++) {
            for (int i = 0; i < this.animations[j].length; i++) {
                this.animations[j][i] = this.image.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    /**
     * Loads the level data for the player.
     */
    public void loadLevelData(int[][] levelData) {
        this.levelData = levelData;
        if (!OtherMethods.playerIsOnTheFloor(hitbox, levelData)) {
            playerInAir = true;
        }
    }

    /**
     * Updates the animation tick and updates to the next animation frame if necessary.
     */
    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= Constants.PlayerConstants.GetImagesAmount(playerAction)) {
                animationIndex = 0;
            }
        }
    }

    /**
     * Selects the current animation line based on the player's state.
     */
    private void setAnimation() {
        int startAnimation = playerAction;
        if (playerMoving) {
            playerAction = 1;
        } else {
            playerAction = 0;
        }
        if (playerInAir) {
            if (airSpeed < 0) {
                playerAction = 2;
            } else if (airSpeed > 0) {
                playerAction = 3;
            }
        }
        if (startAnimation != playerAction) {
            resetAnimationTick();
        }

    }


    /**
     * Resets the animation tick and index to zero.
     */
    private void resetAnimationTick() {
        this.animationTick = 0;
        this.animationIndex = 0;
    }


    /**
     * Updates the player's position based on inputs and environment.
     */
    private void updatePlayerPosition() {
        playerMoving = false;

        if (jump) {
            jump();
        }
        if (!left && !right && !playerInAir) {
            return;
        }
        if (left) {
            xSpeed = -playerSpeed;
        }
        if (right) {
            xSpeed = playerSpeed;
        }
        if (!playerInAir) {
            if (!OtherMethods.playerIsOnTheFloor(hitbox, levelData)) {
                playerInAir = true;
            }
        }
        if (playerInAir) {
            if (OtherMethods.canMove(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPosition();
            } else {
                hitbox.y = OtherMethods.getNextToFloorOrRoof(hitbox, airSpeed);
                if (airSpeed > 0) {
                    resetPlayerInAir();
                } else {
                    airSpeed = fallSpeedAfterCollisionWithRoof;
                }
                updateXPosition();
            }
        } else {
            updateXPosition();
        }
        playerMoving = true;
    }

    /**
     * Makes the player jump by setting the air speed.
     */
    private void jump() {
        if (playerInAir) {
            return;
        } else {
            playerInAir = true;
            airSpeed = jumpSpeed;
        }
    }

    /**
     * Resets the player's air state and air speed.
     */
    private void resetPlayerInAir() {
        playerInAir = false;
        airSpeed = 0;

    }

    /**
     * Updates the player's x-position based on the x-speed.
     */
    private void updateXPosition() {
        if (OtherMethods.canMove(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = OtherMethods.getNextToWall(hitbox, xSpeed);
        }
    }

    /**
     * Resets the player's movement directions to false.
     */
    public void resetDirection() {
        left = false;
        right = false;
    }

    /**
     * Resets all player states including position and movement.
     */
    public void resetAll() {
        resetDirection();
        playerInAir = false;
        playerMoving = false;


        hitbox.x = hitboxX;
        hitbox.y = hitboxY;

        if (!OtherMethods.playerIsOnTheFloor(hitbox, levelData)) {
            playerInAir = true;
        }
    }


    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
