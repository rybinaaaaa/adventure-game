package rybina.Model.Entity.Monster;

import rybina.Helpers.Animation.Animation;

import static rybina.Helpers.Animation.Animation.loadAnimation;

public class Skeleton extends Monster {
    private Animation.NodeImage[] runAnimation = loadAnimation("/monsters/Skeleton/Walk.png", 48, 40, 1, 4, (150 - 48) / 2, (150 - 40) / 2, getClass());
    private Animation.NodeImage[] deathAnimation = loadAnimation("/monsters/Skeleton/Death.png", 48, 40, 1, 4, (150 - 48) / 2, (150 - 40) / 2, getClass());
    private Animation.NodeImage[] damageAnimation = loadAnimation("/monsters/Skeleton/TakeHit.png", 48, 40, 1, 4, (150 - 48) / 2, (150 - 40) / 2, getClass());

    private String animationType = "run";

    public Skeleton(int x, int y) {
        super(x, y, 5);
        setDefaultValues();
    }

    public void setAnimationType(String option) {
        int direction = Math.abs(speedX) / speedX;
        switch (option) {
            case "run":
                animationPresent = runAnimation;
                this.width = 64 * direction;
                animationType = option;
                break;
            case "death":
                animationPresent = deathAnimation;
                this.width = 64 * direction;
                animationType = option;
                break;
            case "damage":
                animationPresent = damageAnimation;
                this.width = 64 * direction;
                animationType = option;
                break;
        }
        image = animationPresent[0];
    }

    public void setDefaultValues() {
        super.setDefaultValues();
        setAnimationType(animationType);
        image = animationPresent[0];
    }
}
