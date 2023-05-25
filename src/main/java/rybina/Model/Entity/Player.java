package rybina.Model.Entity;

import rybina.Helpers.Animation.Animation.NodeImage;
import rybina.Main;

import java.io.Serializable;

import static rybina.Helpers.Animation.Animation.loadAnimation;

public class Player extends Entity implements Serializable {
    private NodeImage[] runAnimation;
    private NodeImage[] chargeAnimation;
    private NodeImage[] attackAnimation;
    private NodeImage[] damageAnimation;
    private NodeImage[] deathAnimation;

    private String animationType = "run";

    private int atackDistance;

    public Player() {
        this.runAnimation = loadAnimation("/player/B_witch_run.png", 32, 48, 8, 1, 0, 0, getClass());
        this.chargeAnimation = loadAnimation("/player/B_witch_charge.png", 48, 48, 5, 1, 0, 0, getClass());
        this.damageAnimation = loadAnimation("/player/B_witch_take_damage.png", 32, 48, 3, 1, 0, 0, getClass());
        this.attackAnimation = loadAnimation("/player/B_witch_attack.png", 104, 46, 9, 1, 0, 0, getClass());
        this.deathAnimation = loadAnimation("/player/B_witch_death.png", 32, 40, 12, 1, 0, 0, getClass());
        setDefaultValues();
    }

    public String getAnimationType() {
        return animationType;
    }

    public void setAnimationType(String option) {
        int direction = Math.abs(speedX) / speedX;
        switch (option) {
            case "run":
                animationPresent = runAnimation;
                this.width = 48 * direction;
                animationType = option;
                break;
            case "charge":
                animationPresent = chargeAnimation;
                this.width = 64 * direction;
                animationType = option;
                break;
            case "attack":
                animationPresent = attackAnimation;
                this.width = 114 * direction;
                animationType = option;
                break;
            case "death":
                animationPresent = deathAnimation;
                this.width = 40 * direction;
                animationType = option;
                break;
            case "damage":
                this.width = Main.Configure.tileSize * direction;
                animationPresent = damageAnimation;
                animationType = option;
                break;
        }
        image = animationPresent[0];
    }


    public void setDefaultValues() {
        super.setDefaultValues();
        setAnimationType(animationType);
        image = animationPresent[0];
        atackDistance = Math.abs(this.getSpeedX()) / this.getSpeedX() * 120;
    }

    public int getAttackDistance() {
        return atackDistance;
    }

    public void setAttackDistance(int atackDistance) {
        this.atackDistance = atackDistance;
    }
}
