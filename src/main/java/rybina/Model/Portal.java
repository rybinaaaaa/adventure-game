package rybina.Model;

import rybina.Helpers.Animation.Animation;

import java.awt.image.BufferedImage;

/**
 * The Portal class represents a portal in the game.
 * It holds the position, animation, and completion status of the portal.
 */
public class Portal {
    int x;
    int y;

    private Animation.NodeImage[] animation;

    private Animation.NodeImage image;

    private boolean isComplete = false;

    /**
     * Returns the array of animation frames for the portal.
     *
     * @return The array of animation frames.
     */
    public Animation.NodeImage[] getAnimation() {
        return animation;
    }

    /**
     * Sets the array of animation frames for the portal.
     *
     * @param animation The array of animation frames to set.
     */
    public void setAnimation(Animation.NodeImage[] animation) {
        this.animation = animation;
    }

    /**
     * Creates a new Portal object with the specified position.
     *
     * @param x The x-coordinate of the portal.
     * @param y The y-coordinate of the portal.
     */
    public Portal(int x, int y) {
        this.x = x;
        this.y = y;
        this.animation = Animation.loadAnimation("/portal/portal.png", 100, 100, 6, 7, 0, 0, getClass(), 41);
        image = animation[0];
    }

    /**
     * Returns the x-coordinate of the portal.
     *
     * @return The x-coordinate of the portal.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the portal.
     *
     * @param x The x-coordinate to set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y-coordinate of the portal.
     *
     * @return The y-coordinate of the portal.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the portal.
     *
     * @param y The y-coordinate to set.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Checks if the portal is complete.
     *
     * @return true if the portal is complete, false otherwise.
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Sets the completion status of the portal.
     *
     * @param complete The completion status to set.
     */
    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    /**
     * Sets the image of the portal to the next frame in the animation.
     */
    public void setImage() {
        this.image = image.nextNode;
    }

    /**
     * Returns the image of the portal.
     *
     * @return The image of the portal.
     */
    public BufferedImage getImage() {
        return image.image;
    }
}
