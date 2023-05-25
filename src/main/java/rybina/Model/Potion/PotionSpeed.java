package rybina.Model.Potion;

public class PotionSpeed extends Potion {
    public PotionSpeed(int x, int y) {
        super(x, y, "/potions/potionSpeed.png", "speed");
    }

    public PotionSpeed() {
        this(0, 0);
    }
}
