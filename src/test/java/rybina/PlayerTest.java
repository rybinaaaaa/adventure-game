package rybina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rybina.Model.Entity.Monster.Goblin;
import rybina.Model.Entity.Monster.Monster;
import rybina.Model.Entity.Player;

public class PlayerTest {
    Player player;
    Monster monster;

    @BeforeEach
    public void init() {
        player = new Player();
        monster = new Goblin(0, 0);
    }

    @Test
    public void playerKilledByMonster() {
        for (int i = 0; i < 32; i++) {
            player.setHealth(player.getHealth() - monster.getDamage());
        }
        Assertions.assertTrue(player.isKilled());
        player.setDefaultValues();
        int counter = 0;
        while (!player.isKilled()) {
            counter++;
            player.setHealth(player.getHealth() - monster.getDamage());
        }
        player.setDefaultValues();
        Assertions.assertEquals(player.getHealth() / monster.getDamage(), counter);
    }
}
