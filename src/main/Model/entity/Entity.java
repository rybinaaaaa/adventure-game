package main.Model.entity;

import java.awt.image.BufferedImage;

public abstract class Entity {

    private int x;
    private int y;
    private int speedX;
    private int speedY;

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        if (health >= maxHealth) {
            this.health = maxHealth;
        } else if (health <= 0) {
            this.health = 0;
        } else {
            this.health = health;
        }
    }

    private double health;

    public int getMaxHealth() {
        return maxHealth;
    }

    private int maxHealth;

    private int width, height;
    NodeImage image;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;
    int jumpingDistance;

    protected class NodeImage {
        BufferedImage image;
        NodeImage prevNode;
        NodeImage nextNode;

        public NodeImage(BufferedImage image) {
            this.image = image;
        }
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
    }

    public void moveLeft() {
        if (speedX > 0) {
            speedX *= -1;
        }
        if (width > 0) {
            width *= -1;
            x -= width;
        }
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getSpriteNumber() {
        return spriteNumber;
    }

    public void setSpriteNumber(int spriteNumber) {
        this.spriteNumber = spriteNumber;
    }

    public void setDefaultValues() {
        width = 48;
        height = 64;
        x = 0;
        y = 0;
        speedX = 4;
        speedY = 3;
        direction = "down";
        jumpingDistance = 64;
        maxHealth = 100;
        health = maxHealth;
    }


    @Override
    public String toString() {
        return String.format("x = %d; y = %d; direction = %s; speedX = %d; speedy = %d", x, y, direction, speedX, speedY);
    }
}
