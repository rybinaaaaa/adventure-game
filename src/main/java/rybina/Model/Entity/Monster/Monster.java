package rybina.Model.Entity.Monster;

import rybina.Model.Entity.Entity;

import java.io.Serializable;

public abstract class Monster extends Entity implements Serializable {
    private int initialX;
    protected int distanceRange;
    public int getDistanceRange() {
        return distanceRange;
    }

    public int getInitialX() {
        return initialX;
    }

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

    public void setAnimationType(String s) {
    }
}
