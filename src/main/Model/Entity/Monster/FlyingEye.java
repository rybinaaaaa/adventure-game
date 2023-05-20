package main.Model.Entity.Monster;

public class FlyingEye extends Monster {
    private NodeImage[] flightAnimation = loadAnimation("/res/monsters/FlyingEye/Flight.png", 48, 40, 1, 8, (150 - 48) / 2, (150 - 40) / 2);
    private NodeImage[] deathAnimation = loadAnimation("/res/monsters/FlyingEye/Death.png", 48, 40, 1, 4, (150 - 48) / 2, (150 - 40) / 2);
    private NodeImage[] damageAnimation = loadAnimation("/res/monsters/FlyingEye/TakeHit.png", 48, 40, 1, 4, (150 - 48) / 2, (150 - 40) / 2);

    private String animationType = "flight";

    public FlyingEye(int x, int y) {
        super(x, y, 5);
        setDefaultValues();
        System.out.println(getHealth());
    }

    public void setDefaultValues() {
        super.setDefaultValues();
        setAnimationType(animationType);
        image = animationPresent[0];
    }

    public void setAnimationType(String option) {
        int direction = Math.abs(speedX) / speedX;
        switch (option) {
            case "flight":
                animationPresent = flightAnimation;
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

    public String getAnimationType() {
        return animationType;
    }
}
