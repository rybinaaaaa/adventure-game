package rybina.Model.Potion;

import java.io.Serializable;
/**
 * The Potion class represents a potion in the game. It is an abstract class that provides common properties and methods
 * for different types of potions.
 * Subclasses of Potion must implement their specific functionality.
 * This class implements the Serializable interface to support object serialization.
 * This class is meant to be subclassed and not directly instantiated.
 */
public abstract class Potion implements Serializable {
    int x;
    int y;
    static String imgSrc;
    String type;
    private boolean visible = true;

    /**
     * Constructs a Potion object with the specified position, image source, and type.
     *
     * @param x       The x-coordinate of the potion.
     * @param y       The y-coordinate of the potion.
     * @param imgSrc  The image source of the potion.
     * @param type    The type of the potion.
     */
    public Potion(int x, int y, String imgSrc, String type) {
        this.x = x;
        this.y = y;
        this.imgSrc = imgSrc;
        this.type = type;
    }

    /**
     * Gets the x-coordinate of the potion.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the potion.
     *
     * @param x The x-coordinate to set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the potion.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the potion.
     *
     * @param y The y-coordinate to set.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the image source of the potion.
     *
     * @return The image source.
     */
    public static String getImgSrc() {
        return imgSrc;
    }

    /**
     * Gets the type of the potion.
     *
     * @return The type of the potion.
     */
    public String getType() {
        return type;
    }
}
