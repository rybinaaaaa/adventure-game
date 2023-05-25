package rybina.Model.Tiles;

/**
 * The Tile class represents a tile in the game map.
 * It is an abstract class that serves as a base for different types of tiles.
 */
public abstract class Tile {
    String imgSrc;      // The image source of the tile
    boolean collision;  // Indicates if the tile is a collision tile
    double damaging;    // The amount of damage the tile inflicts

    private int x, y;   // The x and y coordinates of the tile

    /**
     * Returns the image source of the tile.
     *
     * @return The image source of the tile.
     */
    public String getImgSrc() {
        return imgSrc;
    }

    /**
     * Sets the image source of the tile.
     *
     * @param imgSrc The image source of the tile.
     */
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    /**
     * Returns whether the tile is a collision tile.
     *
     * @return true if the tile is a collision tile, false otherwise.
     */
    public boolean isCollision() {
        return collision;
    }

    /**
     * Sets whether the tile is a collision tile.
     *
     * @param collision true if the tile is a collision tile, false otherwise.
     */
    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    /**
     * Returns the amount of damage the tile inflicts.
     *
     * @return The amount of damage the tile inflicts.
     */
    public double getDamaging() {
        return damaging;
    }

    /**
     * Returns the x-coordinate of the tile.
     *
     * @return The x-coordinate of the tile.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the tile.
     *
     * @param x The x-coordinate of the tile.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y-coordinate of the tile.
     *
     * @return The y-coordinate of the tile.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the tile.
     *
     * @param y The y-coordinate of the tile.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns a string representation of the tile.
     *
     * @return A string representation of the tile.
     */
    @Override
    public String toString() {
        return String.format("type = %s; x = %d; y = %d", this.getClass().getName(), x, y);
    }
}
