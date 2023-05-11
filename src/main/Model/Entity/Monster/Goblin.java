package main.Model.Entity.Monster;

public class Goblin extends Monster {
    private NodeImage[] runAnimation = loadAnimation("/res/monsters/Goblin/Run.png", 48, 40, 1, 8, (150 - 48) / 2, (150 - 40) / 2);
    private NodeImage[] deathAnimation = loadAnimation("/res/monsters/Goblin/Death.png", 48, 40, 1, 4, (150 - 48) / 2, (150 - 40) / 2);
    private NodeImage[] damageAnimation = loadAnimation("/res/monsters/Goblin/TakeHit.png", 48, 40, 1, 4, (150 - 48) / 2, (150 - 40) / 2);

    private String animationType = "run";

    public Goblin(int x, int y) {
        super(x, y, 20);
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
