package rybina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import rybina.Controller.GameController;
import rybina.Controller.KeyHandler;
import rybina.Model.Entity.Monster.Goblin;
import rybina.Model.Entity.Monster.Monster;
import rybina.Model.Entity.Player;
import rybina.Model.GameState;

class GameControllerTest {
    GameState gameState;
    GameController gameController;
    Player player;

    @BeforeEach
    void init() {
        gameState = new GameState();
// we need to reset it because the game state load last game
        gameState.setDefaultValues();
        gameController = (GameController) gameState.getGameController();
        player = gameState.getPlayer();
    }

    @Test
    void testMonsterCollision() {
        Monster goblin = new Goblin(player.getX(), player.getY());
        gameState.getCurrentLevel().setMonsters(new Monster[]{goblin});
        gameController.update();
        Assertions.assertEquals(player.getMaxHealth() - goblin.getDamage(), player.getHealth());
    }

    @Test
    void testPlayerGotPotionAttack() {
        KeyHandler keyHandlerMock = Mockito.mock(KeyHandler.class);
        Mockito.when(keyHandlerMock.isSpacePressed()).thenReturn(true);
        gameController.setKeyH(keyHandlerMock);

        Monster goblin = new Goblin(player.getAttackDistance() / 2, player.getY());
        gameState.getCurrentLevel().setMonsters(new Monster[]{goblin});
        gameController.updateAttacking();
        Assertions.assertEquals(goblin.getMaxHealth() - player.getDamage(), goblin.getHealth());
    }
}