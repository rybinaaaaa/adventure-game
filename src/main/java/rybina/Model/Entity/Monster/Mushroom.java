package rybina.Model.Entity.Monster;

import rybina.Helpers.Animation.Animation;

import static rybina.Helpers.Animation.Animation.loadAnimation;

/**
 * The type of monster representing a Mushroom.
 * Extends the {@link Monster} class.
 */
public class Mushroom extends Monster {
    /**
     * The animation for the running state.
     */
    private final Animation.NodeImage[] runAnimation = loadAnimation("/monsters/Mushroom/Run.png", 48, 48, 1, 8, (150 - 48) / 2, (150 - 48) / 2, getClass());

    /**
     * The animation for the death state.
     */
    private final Animation.NodeImage[] deathAnimation = loadAnimation("/monsters/Mushroom/Death.png", 48, 48, 1, 4, (150 - 48) / 2, (150 - 48) / 2, getClass());

    /**
     * The animation for the damage state.
     */
    private final Animation.NodeImage[] damageAnimation = loadAnimation("/monsters/Mushroom/TakeHit.png", 48, 48, 1, 4, (150 - 48) / 2, (150 - 48) / 2, getClass());

    /**
     * Constructs a Mushroom object with the specified coordinates.
     *
     * @param x The x-coordinate of the Mushroom.
     * @param y The y-coordinate of the Mushroom.
     */
    public Mushroom(int x, int y) {
        super(x, y, 5);
        setDefaultValues();
    }

    /**
     * Sets the animation type for the Mushroom.
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
            case DAMAGE:
                animationPresent = damageAnimation;
                this.width = 64 * direction;
                animationType = option;
                break;
        }
        image = animationPresent[0];
    }

    /**
     * Sets the default values for the Mushroom.
     * Overrides the method in the parent class.
     */
    @Override
    public void setDefaultValues() {
        super.setDefaultValues();
        setAnimationType(animationType);
        image = animationPresent[0];
    }
}
