package rybina.Model.Entity.Monster;

import rybina.Model.Entity.Entity;

/**
 * The abstract base class for all monster entities in the game.
 * Extends the {@link Entity} class.
 */
public abstract class Monster extends Entity {
    private int initialX;
    protected int distanceRange;

    protected AnimationTypeSelect animationType = AnimationTypeSelect.RUN;

    /**
     * Constructs a new instance of the {@link Monster} class with the specified position and damage.
     *
     * @param x      The x-coordinate of the monster's position.
     * @param y      The y-coordinate of the monster's position.
     * @param damage The damage inflicted by the monster.
     */
    public Monster(int x, int y, int damage) {
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.initialX = x;
    }

    public void setDefaultValues() {
        width = 64;
        height = 64;
        distanceRange = 60;
        speedX = 2;
        speedY = 2;
        maxHealth = 100;
        health = maxHealth;
    }

//    getters and setters

    /**
     * Sets the animation type for the monster.
     *
     * @param s The animation type to set.
     */
    public abstract void setAnimationType(AnimationTypeSelect s);

    /**
     * Gets the distance range of moving from side to side the monster.
     *
     * @return The distance range of moving the monster.
     */
    public int getDistanceRange() {
        return distanceRange;
    }

    /**
     * Gets the initial x-coordinate of the monster.
     *
     * @return The initial x-coordinate of the monster.
     */
    public int getInitialX() {
        return initialX;
    }
}
