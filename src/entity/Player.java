package entity;

import java.awt.*;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    int jumpingDistance;
    int speedX, speedY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 0;
        y = 32 * 12;
        speed = 4;
        speedX = 4;
        speedY = 4;
        direction = "down";
        jumpingDistance = 56;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/run/B_witch_run_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/run/B_witch_run_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/run/B_witch_run_3.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/run/B_witch_run_4.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/run/B_witch_run_5.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/run/B_witch_run_6.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/run/B_witch_run_7.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/run/B_witch_run_8.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean jumping = false;
    int jumpingHeight = 0;
    int initialY;
    public void update() {

//        MOVING
        if (keyH.upPressed && !jumping) {
            direction = "up";
            jumping = true;
            initialY = y;
            jumpingHeight = y - jumpingDistance;
        } else if (keyH.leftPressed) {
            direction = "left";
            x -= speed;
        } else if (keyH.rightPressed) {
            direction = "right";
            x += speed;
        }

        if (jumping) {
            y -= speedY;
            if (y <= jumpingHeight) {
                speedY = -speedY;
            }
            if (y >= initialY) {
                y = initialY;
                jumping = false;
                speedY = -speedY;
            }
        }

        spriteCounter++;

        if (spriteCounter > 10) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) spriteNumber = 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                } else {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                } else {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                } else {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                } else {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
