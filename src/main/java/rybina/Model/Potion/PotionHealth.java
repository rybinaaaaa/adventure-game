package rybina.Model.Potion;

/**
 * The PotionHealth class represents a health potion in the game. It is a subclass of the Potion class.
 * Health potions have an (x, y) coordinate position, an image source, and a type of "health".
 * It adds health to player
 */
public class PotionHealth extends Potion {
    public PotionHealth(int x, int y) {
        super(x, y, "/potions/potionHealth.png", "health");
    }

    public PotionHealth() {
        this(0, 0);
    }
}
