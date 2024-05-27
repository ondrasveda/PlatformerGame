package entities;

import gamestates.Playing;
import main.Game;
import utilities.Constants;
import utilities.Load;
import utilities.OtherMethods;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {


    private BufferedImage[][] animations;
    private BufferedImage image;
    private int animationTick;
    private int animationIndex;
    private int animationSpeed = 10;
    private int playerAction = 0;
    private boolean left;
    private boolean right;
    private boolean up;


    private boolean playerMoving = false;
    private boolean playerAttacking = false;
    private float playerSpeed = 2.0f * Game.tileScale;
    private float xSpeed = 0;
    private int[][] levelData;
    private float xHitboxOffset = 20 * Game.tileScale;
    private float yHitboxOffset = 4 * Game.tileScale;

    private boolean jump;
    private float airSpeed = 0f;
    private float gravity = 0.1f * Game.tileScale;
    private float jumpSpeed = -4f * Game.tileScale;
    private float fallSpeedAfterCollisionWithRoof = 0.5f * Game.tileScale;
    private boolean playerInAir = false;
    private Playing playing;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimation();
        initializeHitbox(x, y, (int)(22 * Game.tileScale), (int)(27 * Game.tileScale));
    }

    public void update() {
        this.updatePosition();
        this.updateAnimationTick();
        this.setAnimation();

    }

    public void render(Graphics graphics, int levelOffset) {

        graphics.drawImage(animations[playerAction][animationIndex],
                (int) (hitbox.x - xHitboxOffset) - levelOffset, (int) (hitbox.y - yHitboxOffset), width, height, null);
        drawHitbox(graphics);
    }

    private void loadAnimation() {
        image = Load.getImages(Load.playerImage);
        this.animations = new BufferedImage[4][6];
        for (int j = 0; j < this.animations.length; j++) {
            for (int i = 0; i < this.animations[j].length; i++) {
                this.animations[j][i] = this.image.getSubimage(i * 64, j * 40, 64, 40);
            }
        }
    }

    public void loadLevelData(int[][] levelData) {
        this.levelData = levelData;
        if(!OtherMethods.playerIsOnTheFloor(hitbox, levelData)){
            playerInAir = true;
        }
    }

    private void updateAnimationTick() {
        this.animationTick++;
        if (this.animationTick >= this.animationSpeed) {
            this.animationTick = 0;
            this.animationIndex++;
            if (this.animationIndex >= Constants.PlayerConstants.GetImagesAmount(this.playerAction)) {
                this.animationIndex = 0;
                playerAttacking = false;
            }
        }
    }

    private void setAnimation() {
        int startAnimation = this.playerAction;
        if (playerMoving) {
            playerAction = 1;
        }else{
            playerAction = 0;
        }
        if (playerInAir){
                if(airSpeed < 0){
                    playerAction = 2;
                }else if(airSpeed > 0){
                    playerAction = 3;
                }
        }
        if (startAnimation != this.playerAction) {
            this.resetAnimationTick();
        }

    }

    private void resetAnimationTick() {
        this.animationTick = 0;
        this.animationIndex = 0;
    }

    private void updatePosition() {
        playerMoving = false;

        if(jump){
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
        if(!playerInAir){
            if(!OtherMethods.playerIsOnTheFloor(hitbox,levelData)){
                playerInAir = true;
            }
        }
        if (playerInAir) {
            if (OtherMethods.canMove(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPosition();
            }else{
                hitbox.y = OtherMethods.getNextToFloorOrRoof(hitbox, airSpeed);
                if(airSpeed > 0){
                    resetPlayerInAir();
                }else{
                    airSpeed = fallSpeedAfterCollisionWithRoof;
                }
                updateXPosition();
            }
        } else {
            updateXPosition();
        }

        playerMoving = true;
    }

    private void jump() {
        if(playerInAir){
            return;
        }else{
            playerInAir = true;
            airSpeed = jumpSpeed;

        }
    }

    private void resetPlayerInAir() {
        playerInAir = false;
        airSpeed = 0;

    }

    private void updateXPosition() {
        if (OtherMethods.canMove(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = OtherMethods.getNextToWall(hitbox, xSpeed);
        }
    }
    public void resetDirection() {
        left = false;
        right = false;
    }
    public void resetAll() {
        resetDirection();
        playerInAir = false;
        playerMoving = false;


        hitbox.x = x;
        hitbox.y = y;

        if (!OtherMethods.playerIsOnTheFloor(hitbox, levelData))
            playerInAir = true;
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
