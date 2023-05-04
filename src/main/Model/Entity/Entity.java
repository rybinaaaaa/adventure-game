package main.Model.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int speedX;
    protected int speedY;
    private int jumpingDistance;

    private double health;
    private int maxHealth;

    protected int width;
    protected int height;
    protected NodeImage image;
//    public String direction;
    protected NodeImage[] animationPresent;

    public int getJumpingDistance() {
        return jumpingDistance;
    }

    public void setJumpingDistance(int jumpingDistance) {
        this.jumpingDistance = jumpingDistance;
    }

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

    public int getMaxHealth() {
        return maxHealth;
    }

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

    public void setDefaultValues() {
        x = 0;
        y = 0;
        speedX = 4;
        speedY = 3;
//        direction = "down";
        jumpingDistance = 64;
        maxHealth = 100;
        health = maxHealth;
        width = 48;
        height = 64;
    }

    protected NodeImage[] loadAnimation(String src, int width, int height, int row, int columns, int paddingX, int paddingY) {
        BufferedImage animation = null;
        int count = row * columns;
        NodeImage[] animationList = new NodeImage[count];
        try {
            animation = ImageIO.read(getClass().getResourceAsStream(src));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < row; i++) {
            assert animation != null;
            for (int j = 0; j < columns; j++) {
                NodeImage node = new NodeImage(animation.getSubimage(j * (width + paddingX * 2) + paddingX, i * (height + paddingY * 2) + paddingY, width, height));
                animationList[i * columns + j] = node;
            }
        }

        for (int i = 0; i < count; i++) {
            if (i != 0) {
                animationList[i].prevNode = animationList[i - 1];
            } else {
                animationList[i].prevNode = animationList[count - 1];
            }
            if (i != count - 1) {
                animationList[i].nextNode = animationList[i + 1];
            } else {
                animationList[i].nextNode = animationList[0];
            }
        }
        return animationList;
    }


    @Override
    public String toString() {
        return String.format("x = %d; y = %d; speedX = %d; speedy = %d", x, y, speedX, speedY);
    }
}
