package main.Model.Potion;

public class PotionHealth extends Potion {
    public PotionHealth(int x, int y) {
        this.type = "health";
        this.imgSrc = "/res/potions/potionHealth.png";
        this.x = x;
        this.y = y;
    }

    public PotionHealth() {
        this(0, 0);
    }
}
