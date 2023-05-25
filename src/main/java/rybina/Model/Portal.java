package rybina.Model;

import rybina.Helpers.Animation.Animation;

import java.awt.image.BufferedImage;

import static rybina.Helpers.Animation.Animation.loadAnimation;

public class Portal {
    int x;
    int y;

    private Animation.NodeImage[] animation;

    private Animation.NodeImage image;

    private boolean isComplete = false;


    public Animation.NodeImage[] getAnimation() {
        return animation;
    }

    public void setAnimation(Animation.NodeImage[] animation) {
        this.animation = animation;
    }

    public Portal(int x, int y) {
        this.x = x;
        this.y = y;
        this.animation = loadAnimation("/portal/portal.png", 100, 100, 6, 7, 0, 0, getClass(), 41);
        image = animation[0];
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

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void setImage() {
        this.image = image.nextNode;
    }

    public BufferedImage getImage() {
        return image.image;
    }
}
