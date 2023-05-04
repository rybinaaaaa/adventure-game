package main.Model.Potion;

public class PotionAttack extends Potion {
    public PotionAttack(int x, int y) {
        this.type = "atack";
        this.imgSrc = "/res/potions/potionAttack.png";
        this.x = x;
        this.y = y;
    }

    public PotionAttack() {
        this(0, 0);
    }
}
