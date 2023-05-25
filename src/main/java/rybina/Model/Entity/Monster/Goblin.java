package rybina.Model.Entity.Monster;

import rybina.Helpers.Animation.Animation;
import rybina.Model.Entity.Entity;

import static rybina.Helpers.Animation.Animation.loadAnimation;

/**
 * The type of monster
 * Extends the {@link Monster} class.
 */
public class Goblin extends Monster {
    /**
     * The animation for the running state.
     */
    private final Animation.NodeImage[] runAnimation = loadAnimation("/monsters/Goblin/Run.png", 48, 40, 1, 8, (150 - 48) / 2, (150 - 40) / 2, getClass());

    /**
     * The animation for the death state.
     */
    private final Animation.NodeImage[] deathAnimation = loadAnimation("/monsters/Goblin/Death.png", 48, 40, 1, 4, (150 - 48) / 2, (150 - 40) / 2, getClass());

    /**
     * The animation for the damage state.
     */
    private final Animation.NodeImage[] damageAnimation = loadAnimation("/monsters/Goblin/TakeHit.png", 48, 40, 1, 4, (150 - 48) / 2, (150 - 40) / 2, getClass());


    /**
     * Constructs a Goblin object with the specified coordinates.
     *
     * @param x The x-coordinate of the Goblin.
     * @param y The y-coordinate of the Goblin.
     */
    public Goblin(int x, int y) {
        super(x, y, 10);
        setDefaultValues();
    }

    /**
     * Sets the animation type for the Goblin.
     *
     * @param option The animation type to set. It should be one of the values defined in the {@link AnimationTypeSelect} enum.
     */
    public void setAnimationType(AnimationTypeSelect option) {
        int direction = Math.abs(speedX) / speedX;
        switch (option) {
            case RUN:
                animationPresent = runAnimation;
                this.width = 64 * direction;
                animationType = option;
                break;
            case DEATH:
                animationPresent = deathAnimation;
                this.width = 64 * direction;
                animationType = option;
                break;
            case CHARGE:
                animationPresent = damageAnimation;
                this.width = 64 * direction;
                animationType = option;
                break;
        }
        image = animationPresent[0];
    }

    /**
     * Sets the default values for the Goblin.
     * Overrides the method in the parent class.
     */
    @Override
    public void setDefaultValues() {
        super.setDefaultValues();
        setAnimationType(animationType);
        image = animationPresent[0];
    }
}
