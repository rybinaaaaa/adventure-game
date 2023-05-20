package main.Model.Entity.Monster;

import main.Model.Entity.Entity;
import main.View.Screen;

import java.io.Serializable;
import java.util.logging.Logger;

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
