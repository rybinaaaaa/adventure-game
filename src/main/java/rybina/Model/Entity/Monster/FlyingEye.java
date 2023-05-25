package rybina.Model.Entity.Monster;

import rybina.Helpers.Animation.Animation.*;

import static rybina.Helpers.Animation.Animation.loadAnimation;

/**
 * The type of monster representing a Flying Eye.
 * Extends the {@link Monster} class.
 */
public class FlyingEye extends Monster {
    /**
     * The animation for the flight state.
     */
    private final NodeImage[] flightAnimation = loadAnimation("/monsters/FlyingEye/Flight.png", 48, 40, 1, 8, (150 - 48) / 2, (150 - 40) / 2, this.getClass());

    /**
     * The animation for the death state.
     */
    private final NodeImage[] deathAnimation = loadAnimation("/monsters/FlyingEye/Death.png", 48, 40, 1, 4, (150 - 48) / 2, (150 - 40) / 2, this.getClass());

    /**
     * The animation for the damage state.
     */
    private final NodeImage[] damageAnimation = loadAnimation("/monsters/FlyingEye/TakeHit.png", 48, 40, 1, 4, (150 - 48) / 2, (150 - 40) / 2, this.getClass());

    /**
     * Constructs a FlyingEye object with the specified coordinates.
     *
     * @param x The x-coordinate of the Flying Eye.
     * @param y The y-coordinate of the Flying Eye.
     */
    public FlyingEye(int x, int y) {
        super(x, y, 5);
        setDefaultValues();
    }

    /**
     * Sets the default values for the Flying Eye.
     * Overrides the method in the parent class.
     */
    @Override
    public void setDefaultValues() {
        super.setDefaultValues();
        setAnimationType(animationType);
        image = animationPresent[0];
    }

    /**
     * Sets the animation type for the Flying Eye.
     *
     * @param option The animation type to set. It should be one of the values defined in the {@link AnimationTypeSelect} enum.
     */
    public void setAnimationType(AnimationTypeSelect option) {
        int direction = Math.abs(speedX) / speedX;
        switch (option) {
            case RUN:
                animationPresent = flightAnimation;
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

    public AnimationTypeSelect getAnimationType() {
        return animationType;
    }
}
