package rybina.Model.Entity;

import rybina.Helpers.Animation.Animation.NodeImage;
import rybina.Main;

import java.io.Serializable;

import static rybina.Helpers.Animation.Animation.loadAnimation;

/**
 * Represents the player entity in the game.
 */
public class Player extends Entity {

    /**
     * The animation for the running state.
     */
    private final NodeImage[] runAnimation = loadAnimation("/player/B_witch_run.png", 32, 48, 8, 1, 0, 0, getClass());

    /**
     * The animation for the charging state.
     */
    private final NodeImage[] chargeAnimation = loadAnimation("/player/B_witch_charge.png", 48, 48, 5, 1, 0, 0, getClass());;

    /**
     * The animation for the attack state.
     */
    private final NodeImage[] damageAnimation = loadAnimation("/player/B_witch_take_damage.png", 32, 48, 3, 1, 0, 0, getClass());;

    /**
     * The animation for the damage state.
     */
    private final NodeImage[] attackAnimation = loadAnimation("/player/B_witch_attack.png", 104, 46, 9, 1, 0, 0, getClass());;

    /**
     * The animation for the death state.
     */
    private final NodeImage[] deathAnimation = loadAnimation("/player/B_witch_death.png", 32, 40, 12, 1, 0, 0, getClass());

    private AnimationTypeSelect animationType = AnimationTypeSelect.RUN;

    private int atackDistance;

    public Player() {
        setDefaultValues();
    }

    /**
     * Gets the current animation type of the player.
     *
     * @return The animation type.
     */
    public AnimationTypeSelect getAnimationType() {
        return animationType;
    }

    /**
     * Sets the animation type for the player.
     *
     * @param option The animation type to set.
     */
    public void setAnimationType(AnimationTypeSelect option) {
        int direction = Math.abs(speedX) / speedX;
        switch (option) {
            case RUN:
                animationPresent = runAnimation;
                this.width = 48 * direction;
                animationType = option;
                break;
            case CHARGE:
                animationPresent = chargeAnimation;
                this.width = 64 * direction;
                animationType = option;
                break;
            case ATACK:
                animationPresent = attackAnimation;
                this.width = 114 * direction;
                animationType = option;
                break;
            case DEATH:
                animationPresent = deathAnimation;
                this.width = 40 * direction;
                animationType = option;
                break;
            case DAMAGE:
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

    /**
     * Gets the attack distance of the player.
     *
     * @return The attack distance.
     */
    public int getAttackDistance() {
        return atackDistance;
    }

    /**
     * Sets the attack distance for the player.
     *
     * @param attackDistance The attack distance to set.
     */
    public void setAttackDistance(int attackDistance) {
        this.atackDistance = attackDistance;
    }
}
