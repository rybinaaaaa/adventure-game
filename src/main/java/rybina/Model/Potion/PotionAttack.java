package rybina.Model.Potion;

/**
 * The PotionAttack class represents an attack potion in the game. It is a subclass of the Potion class.
 * Attack potions have an (x, y) coordinate position, an image source, and a type of "attack".
 * It increases the player damage twice
 */
public class PotionAttack extends Potion {

    public PotionAttack(int x, int y) {
        super(x, y, "/potions/potionAttack.png", "atack");
    }

    public PotionAttack() {
        this(0, 0);
    }
}
