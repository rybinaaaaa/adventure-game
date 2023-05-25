package rybina.Model.Entity;

import rybina.Helpers.Animation.Animation.NodeImage;

import java.awt.image.BufferedImage;
import java.util.logging.Logger;

/**
 * The abstract class representing an entity in the game.
 * Entities can be monsters or player.
 */
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

    /**
     * The enumeration for selecting different animation types.
     */
    public enum AnimationTypeSelect {
        RUN,
        ATACK,
        DAMAGE,
        DEATH,
        CHARGE
    }

    private int realWidth = 48;
    private boolean isKilled = false;

    /**
     * Gets the jumping distance of the entity.
     *
     * @return The jumping distance.
     */
    public int getJumpingDistance() {
        return jumpingDistance;
    }

    /**
     * Sets the jumping distance of the entity.
     *
     * @param jumpingDistance The jumping distance to set.
     */
    public void setJumpingDistance(int jumpingDistance) {
        this.jumpingDistance = jumpingDistance;
    }

    /**
     * Gets the health of the entity.
     *
     * @return The health value.
     */
    public double getHealth() {
        return health;
    }

    Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Sets the health of the entity.
     * If the health exceeds the maximum health, it is set to the maximum health.
     * If the health is zero or below, the entity is marked as killed.
     *
     * @param health The health value to set.
     */
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

    /**
     * Gets the maximum health of the entity (initial).
     *
     * @return The maximum health value.
     */
    public double getMaxHealth() {
        return maxHealth;
    }

    /**
     * Gets the image of the entity.
     *
     * @return The image of the entity.
     */
    public BufferedImage getImage() {
        return image.image;
    }

    /**
     * Sets the image of the entity to the next frame in the animation.
     */
    public void setImage() {
        this.image = image.nextNode;
    }

    /**
     * Gets the x-coordinate of the entity.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the entity.
     *
     * @param x The x-coordinate to set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the entity.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the entity.
     *
     * @param y The y-coordinate to set.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the speed in the x-direction of the entity.
     *
     * @return The speed in the x-direction.
     */
    public int getSpeedX() {
        return speedX;
    }

    /**
     * Sets the speed in the x-direction of the entity.
     *
     * @param speedX The speed in the x-direction to set.
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    /**
     * Gets the speed in the y-direction of the entity.
     *
     * @return The speed in the y-direction.
     */
    public int getSpeedY() {
        return speedY;
    }

    /**
     * Sets the speed in the y-direction of the entity.
     *
     * @param speedY The speed in the y-direction to set.
     */
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }


    /**
     * Gets the width of picture the entity.
     *
     * @return The width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the entity.
     *
     * @return The height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Moves the entity to the right.
     * If the entity is facing left, its direction is reversed.
     * If the width is negative, it is reversed and the x-coordinate is adjusted.
     */
    public void moveRight() {
        if (speedX < 0) {
            speedX *= -1;
        }
        if (width < 0) {
            width *= -1;
            x -= width;
        }
    }

    /**
     * Moves the entity to the left.
     * If the entity is facing right, its direction is reversed.
     * If the width is positive, it is reversed and the x-coordinate is adjusted.
     */
    public void moveLeft() {
        if (speedX > 0) {
            speedX *= -1;
        }
        if (width > 0) {
            width *= -1;
            x -= width;
        }
    }

    /**
     * Sets the default values for the entity.
     */
    public void setDefaultValues() {
        x = 0;
        y = 0;
        speedX = 2;
        speedY = 3;
        jumpingDistance = 100;
        maxHealth = 300;
        health = maxHealth;
        width = 48;
        height = 64;
        damage = 20;
        isKilled = false;
    }

    /**
     * Gets the damage of the entity.
     *
     * @return The damage value.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets the damage of the entity.
     *
     * @param damage The damage value to set.
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Gets the real width of the entity.
     *
     * @return The real width.
     */
    public int getRealWidth() {
        return realWidth * Math.abs(width) / width;
    }

    /**
     * Sets the animation type for the entity.
     *
     * @param option The animation type to set. It should be one of the values defined in the {@link AnimationTypeSelect} enum.
     */
    public abstract void setAnimationType(AnimationTypeSelect option);

    /**
     * Checks if the entity is killed.
     *
     * @return {@code true} if the entity is killed, {@code false} otherwise.
     */
    public boolean isKilled() {
        return isKilled;
    }

    /**
     * Sets the killed status of the entity.
     * If the entity is marked as killed, a log message is printed.
     *
     * @param killed The killed status to set.
     */
    public void setKilled(boolean killed) {
        if (killed) {
            logger.info(getClass().getName() + " has been killed!");
        }
        isKilled = killed;
    }

    @Override
    public String toString() {
        return String.format("x = %d; y = %d; speedX = %d; speedy = %d", x, y, speedX, speedY);
    }
}
