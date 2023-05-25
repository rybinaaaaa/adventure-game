package rybina.Model.Potion;

public class PotionAttack extends Potion {

    public PotionAttack(int x, int y) {
        super(x, y, "/potions/potionAttack.png", "atack");
    }

    public PotionAttack() {
        this(0, 0);
    }
}
