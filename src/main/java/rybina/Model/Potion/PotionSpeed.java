package rybina.Model.Potion;

/**
 * The PotionSpeed class represents a speed potion in the game. It is a subclass of the Potion class.
 * Speed potions have an (x, y) coordinate position, an image source, and a type of "speed".
 * It increases player's speed
 */
public class PotionSpeed extends Potion {
    public PotionSpeed(int x, int y) {
        super(x, y, "/potions/potionSpeed.png", "speed");
    }

    public PotionSpeed() {
        this(0, 0);
    }
}
