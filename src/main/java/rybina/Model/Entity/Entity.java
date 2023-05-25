package rybina.Model.Entity;

import rybina.Helpers.Animation.Animation.*;

import java.awt.image.BufferedImage;
import java.util.logging.Logger;

public abstract class Entity {


    protected int x;
    protected int y;
    protected int speedX;
    protected int speedY;
    private int jumpingDistance;

    protected double health;
    protected double maxHealth;

    protected int damage;

    protected int width;
    protected int height;
    protected NodeImage image;
    //    public String direction;
    protected NodeImage[] animationPresent;

    private int realWidth = 48;

    public boolean isKilled() {
        return isKilled;
    }

    public void setKilled(boolean killed) {
        if (killed == true) {
            logger.info(getClass().getName() + " has been killed!");
        }
        isKilled = killed;
    }

    private boolean isKilled = false;

    public int getJumpingDistance() {
        return jumpingDistance;
    }

    public void setJumpingDistance(int jumpingDistance) {
        this.jumpingDistance = jumpingDistance;
    }

    public double getHealth() {
        return health;
    }

    Logger logger = Logger.getLogger(this.getClass().getName());


    public void setHealth(double health) {
        double initalHealth = this.health;
        if (health >= maxHealth) {
            this.health = maxHealth;
        } else if (health <= 0) {
            this.health = 0;
            setKilled(true);
        } else {
            this.health = health;
        }
        if (health != initalHealth) {
            this.logger.info(this.getClass().getName() + " initial health = " + initalHealth + "; " + "final health = " + this.health + ";");
        }
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public BufferedImage getImage() {
        return image.image;
    }

    public void setImage() {
        this.image = image.nextNode;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void moveRight() {
        if (speedX < 0) {
            speedX *= -1;
        }
        if (width < 0) {
            width *= -1;
            x -= width;
        }
//        if (this.getClass().getName().equals("Player")) {
//            logger.info("Player move right: speed = " + speedX + ", coordinats = " + x);
//        }
    }

    public void moveLeft() {
        if (speedX > 0) {
            speedX *= -1;
        }
        if (width > 0) {
            width *= -1;
            x -= width;
        }
//        if (this.getClass().getName().equals("Player")) {
//            logger.info("Player move left: speed = " + speedX + ", coordinats = " + x);
//        }
    }

    public void setDefaultValues() {
        x = 0;
        y = 0;
        speedX = 2;
        speedY = 3;
//        direction = "down";
        jumpingDistance = 100;
        maxHealth = 300;
        health = maxHealth;
        width = 48;
        height = 64;
        damage = 20;
        isKilled = false;
    }

//    protected NodeImage[] loadAnimation(String src, int width, int height, int row, int columns, int paddingX, int paddingY) {
//        BufferedImage animation = null;
//        int count = row * columns;
//        NodeImage[] animationList = new NodeImage[count];
//        try {
//            animation = ImageIO.read(getClass().getResourceAsStream(src));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < row; i++) {
//            assert animation != null;
//            for (int j = 0; j < columns; j++) {
//                NodeImage node = new NodeImage(animation.getSubimage(j * (width + paddingX * 2) + paddingX, i * (height + paddingY * 2) + paddingY, width, height));
//                animationList[i * columns + j] = node;
//            }
//        }
//
//        for (int i = 0; i < count; i++) {
//            if (i != 0) {
//                animationList[i].prevNode = animationList[i - 1];
//            } else {
//                animationList[i].prevNode = animationList[count - 1];
//            }
//            if (i != count - 1) {
//                animationList[i].nextNode = animationList[i + 1];
//            } else {
//                animationList[i].nextNode = animationList[0];
//            }
//        }
////        logger.info("Animations (src = "+ src +") for" + getClass().getName() + "has been loaded;");
//        return animationList;
//    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRealWidth() {
        return realWidth * Math.abs(width) / width;
    }

    @Override
    public String toString() {
        return String.format("x = %d; y = %d; speedX = %d; speedy = %d", x, y, speedX, speedY);
    }
}
