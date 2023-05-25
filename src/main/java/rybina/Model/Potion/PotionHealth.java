package rybina.Model.Potion;

public class PotionHealth extends Potion {
    public PotionHealth(int x, int y) {
        super(x, y, "/potions/potionHealth.png", "health");
    }

    public PotionHealth() {
        this(0, 0);
    }
}
