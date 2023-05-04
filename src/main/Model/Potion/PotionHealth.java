package main.Model.Potion;

public class PotionHealth extends Potion {
    public PotionHealth(int x, int y) {
        super(x, y, "/res/potions/potionHealth.png", "health");
    }

    public PotionHealth() {
        this(0, 0);
    }
}
