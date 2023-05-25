package rybina.Model.Entity.Monster;

import rybina.Helpers.Animation.Animation;

import static rybina.Helpers.Animation.Animation.loadAnimation;

/**
 * The type of monster representing a Skeleton.
 * Extends the {@link Monster} class.
 */
public class Skeleton extends Monster {
    /**
     * The animation for the running state.
     */
    private Animation.NodeImage[] runAnimation = loadAnimation("/monsters/Skeleton/Walk.png", 48, 40, 1, 4, (150 - 48) / 2, (150 - 40) / 2, getClass());

    /**
     * The animation for the death state.
     */
    private Animation.NodeImage[] deathAnimation = loadAnimation("/monsters/Skeleton/Death.png", 48, 40, 1, 4, (150 - 48) / 2, (150 - 40) / 2, getClass());

    /**
     * The animation for the damage state.
     */
    private Animation.NodeImage[] damageAnimation = loadAnimation("/monsters/Skeleton/TakeHit.png", 48, 40, 1, 4, (150 - 48) / 2, (150 - 40) / 2, getClass());


    public Skeleton(int x, int y) {
        super(x, y, 5);
        setDefaultValues();
    }

    /**
     * Sets the animation type for the Skeleton.
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
     * Sets the default values for the Skeleton.
     * Overrides the method in the parent class.
     */
    @Override
    public void setDefaultValues() {
        super.setDefaultValues();
        setAnimationType(animationType);
        image = animationPresent[0];
    }
}
