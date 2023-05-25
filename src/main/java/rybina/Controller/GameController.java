package rybina.Controller;

import rybina.Main;
import rybina.Model.Entity.Entity;
import rybina.Model.Entity.Monster.Monster;
import rybina.Model.Entity.Player;
import rybina.Model.GameState;
import rybina.Model.Levels.Levels;
import rybina.Model.Levels.Map;
import rybina.Model.Portal;
import rybina.Model.Potion.Potion;
import rybina.Model.Tiles.Tile;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * The GameController class is responsible for controlling the game logic and interactions between entities.
 * Extends the {@link Controller} class.
 */
public class GameController extends Controller {

    Timer timerPlayer = new Timer();
    Timer timerMonster = new Timer();
    Player player;
    KeyHandler keyH;
    Levels levels;
    Map map;
    Monster[] monsters;

    GameState gameState;

    Logger logger = Logger.getLogger(getClass().getName());

    private boolean jumping, atacking, deathing = false;

    boolean leanBack = false;
    private int jumpingHeight = 0;
    private boolean isCollisionTop = false,
            isCollisionBottom = false,
            isCollisionMove = false,
            isCollisionBack = false;

    /**
     * Initializes the game controller with the necessary entities and objects.
     *
     * @param player    The player entity.
     * @param keyH      The KeyHandler for user input.
     * @param gameState The current game state.
     */
    public GameController(Player player, KeyHandler keyH, GameState gameState) {
        this.player = player;
        this.keyH = keyH;
        this.levels = gameState.getLevels();
        this.map = levels.getCurrentLevel();
        initPlayerAnimation();
        this.monsters = map.getMonsters();
        this.gameState = gameState;
        initMonsterMoving();
        initPortalAnimation();
    }


    /**
     * Updates the collision status based on the player's position and the surrounding tiles.
     */
    private void updateCollision() {
        Tile top = map.getTile(player.getX() + player.getRealWidth() / 2, player.getY() - 5);
        Tile bottom = map.getTile(player.getX() + player.getRealWidth() / 2, player.getY() + player.getHeight());
        Tile present = map.getTile(player.getX() + player.getRealWidth() / 2, player.getY() + Main.Configure.tileSize);
        Tile next = map.getTile(player.getX() + player.getRealWidth() * 4 / 5, player.getY() + Main.Configure.tileSize);
        Tile back = map.getTile(player.getX() - player.getRealWidth() * 2 / 5, player.getY() + Main.Configure.tileSize);

//        System.out.println(back);
        isCollisionTop = top.isCollision();
        isCollisionBottom = bottom.isCollision();
        isCollisionMove = next.isCollision();
        isCollisionBack = back.isCollision();
        takeDamage(present.getDamaging());
    }

    /**
     * Initializes the portal animation.
     */
    private void initPortalAnimation() {
        timerPlayer.schedule(new TimerTask() {
            @Override
            public void run() {
                map.getPortal().setImage();
            }
        }, 0, 200);
    }

    /**
     * Handles the action of taking a potion and updates the player's attributes accordingly.
     *
     * @param potion The potion to be taken by the player.
     */
    private void takePotion(Potion potion) {
        player.setAnimationType(Entity.AnimationTypeSelect.CHARGE);
        timerPlayer.schedule(new TimerTask() {
            public void run() {
                player.setAnimationType(Entity.AnimationTypeSelect.RUN);
            }
        }, 1000);

        switch (potion.getType()) {
            case "attack":
                player.setDamage(player.getDamage() * 2 / 3);
                break;
            case "health":
                player.setHealth(player.getHealth() + player.getMaxHealth() / 5);
                break;
            case "speed":
                player.setSpeedX(player.getSpeedX() * 2);
                timerPlayer.schedule(new TimerTask() {
                    public void run() {
                        player.setSpeedX(player.getSpeedX() / 2);
                    }
                }, 5000);

        }

        logger.info("player took potion. Type of potion: " + potion.getType());
    }

    /**
     * Handles the collision between player and potion
     */
    private void updatePotion() {
        Potion potion = map.getPotion(player.getX() + player.getWidth() / 2, player.getY() + Main.Configure.tileSize);

        if (potion != null) {
            takePotion(potion);
            map.deletePotion(potion);
        }
    }

    /**
     * Updates the player's health and state based on the damage received from a damaging tile / monster.
     *
     * @param damage The amount of damage to be received.
     */
    private void takeDamage(double damage) {
        if (damage <= 0 || leanBack || deathing) return;
        player.setHealth(player.getHealth() - damage);
        if (player.isKilled()) {
//            logger.info("Player has died!");
            deathing = true;
            player.setAnimationType(Entity.AnimationTypeSelect.DEATH);
            timerPlayer.schedule(new TimerTask() {
                public void run() {
                    gameState.setCurrentState("game over");
                }
            }, 2000);
            timerPlayer.schedule(new TimerTask() {
                public void run() {
                    player.setKilled(true);
                    gameState.setDefaultValues();
                }
            }, 4000);
            return;
        }
        if (player.getAnimationType() == Entity.AnimationTypeSelect.DAMAGE) return;
        player.setAnimationType(Entity.AnimationTypeSelect.DAMAGE);
        timerPlayer.schedule(new TimerTask() {
            public void run() {
                if (deathing) return;
                player.setAnimationType(Entity.AnimationTypeSelect.RUN);
            }
        }, 1000);
    }

    /**
     * Handles the collision between monster and player
     */
    private void updateMonsterCollision() {
        if (deathing) return;
        leanBack();
        Monster monster = map.getMonster(player.getX() + player.getRealWidth() / 2, player.getY());
        if (monster != null && !leanBack) {
            logger.info("player move in a monster. Monster: " + monster.getClass().getName());
            isCollisionMove = false;
            takeDamage(monster.getDamage());
            leanBack = true;
            timerPlayer.schedule(new TimerTask() {
                public void run() {
                    leanBack = false;
                }
            }, 1000);
        }

    }

    /**
     * leaning back the player (with special logic)
     */
    private void leanBack() {
        if (leanBack && !isCollisionBack && player.getX() >= 0 && (player.getX() + map.getOffsetX() <= map.getMaxOffsetX())) {
            int offset = (player.getX() - map.getWidth() / 2);
//            если оффсет больше нуля и игрок двигается направо а также не в самом уонце карты то
//            если игрок в конце карты и его скорость меньше нуляб то при отбрасывании он не должен упираться назад
            if (offset > 0 && player.getSpeedX() < 0 && map.getMaxOffsetX() >= map.getOffsetX() - player.getSpeedX()) {
                map.setOffsetX(-player.getSpeedX());
            } else if ((map.getOffsetX() - player.getSpeedX()) >= 0 && player.getSpeedX() > 0 && offset < 0) {
                map.setOffsetX(-player.getSpeedX());
            } else if (player.getX() != map.getWidth() - Main.Configure.tileSize && player.getSpeedX() < 0 || player.getX() != Main.Configure.tileSize && player.getSpeedX() > 0) {
                player.setX(player.getX() - player.getSpeedX());
            }
        }
    }

    /**
     * handles the attack action from user
     */
    public void updateAttacking() {
        if (deathing) return;
        if (keyH.isSpacePressed()) {
            attack();
        }
    }


    /**
     * implies attack logic between monster and player (player attacks monster)
     */
    private void attack() {
        if (atacking || player.getAnimationType() == Entity.AnimationTypeSelect.DAMAGE) return;
        atacking = true;
        player.setAnimationType(Entity.AnimationTypeSelect.ATACK);
        timerPlayer.schedule(new TimerTask() {
            public void run() {
                atacking = false;
                player.setAnimationType(Entity.AnimationTypeSelect.RUN);
            }
        }, 1500);
        ArrayList<Monster> monsters = map.getMonsterOnRange(player.getAttackDistance(), player.getX(), player.getY());
        for (Monster monster : monsters) {
            monster.setHealth(monster.getHealth() - player.getDamage());
            if (monster.getHealth() == 0) {
                monster.setAnimationType(Entity.AnimationTypeSelect.DEATH);
                timerMonster.schedule(new TimerTask() {
                    public void run() {
                        monster.setKilled(true);
                    }
                }, 700);
            } else {
                monster.setAnimationType(Entity.AnimationTypeSelect.DAMAGE);
                timerMonster.schedule(new TimerTask() {
                    public void run() {
                        monster.setAnimationType(Entity.AnimationTypeSelect.RUN);
                    }
                }, 1000);
            }
        }
    }


    /**
     * Handles the position of player to the portal(end of level)
     * Do logic which responsible for complete level + complete game
     */
    public void updateLevelState() {
        Portal portal = gameState.getCurrentLevel().getPortal();
//        if ((player.getX() + map.getOffsetX()) >= portal.getX() && (player.getX() + map.getOffsetX()) <= portal.getX() + Main.Configure.tileSize * 2) {
        if ((player.getX() + map.getOffsetX()) >= portal.getX() && (player.getX() + map.getOffsetX()) <= portal.getX() + Main.Configure.tileSize && portal.getY() == player.getY()) {
            portal.setComplete(true);
            if (levels.isFinished()) {
                gameState.setCurrentState("finish");
                timerPlayer.schedule(new TimerTask() {
                    public void run() {
                        gameState.setDefaultValues();
                    }
                }, 4000);
            } else {
                player.setDefaultValues();
                levels.setToNextLevel();
                map = levels.getCurrentLevel();
                monsters = levels.getCurrentMonsters();
            }
        }
    }

    /**
     * init player's animation
     */
    public void initPlayerAnimation() {
        timerPlayer.schedule(new TimerTask() {
            public void run() {
                player.setImage();
            }
        }, 0, 200);
    }

    /**
     * init monster animation +  moving from side to side
     */
    public void initMonsterMoving() {
        timerMonster.schedule(new TimerTask() {
            public void run() {
                for (Monster monster : monsters) {
                    if (monster.isKilled()) {
                        continue;
                    }
                    monster.setImage();
                    monster.setX(monster.getX() + monster.getSpeedX());
                    if (monster.getInitialX() + monster.getDistanceRange() <= monster.getX()) {
                        monster.moveLeft();
                    } else if (monster.getX() <= monster.getInitialX()) {
                        monster.moveRight();
                    }
                }
            }
        }, 0, 100);
    }

    /**
     * Call all handlers
     */
    public void update() {
        updateMonsterCollision();
        updateCollision();
        updatePotion();
        moving();
        updateAttacking();
        updateLevelState();
    }

    /**
     * Handles the user's action to move
     */
    private void moving() {
        if (deathing) return;
        moveDown();
        if (keyH.isUpPressed() && !jumping && isCollisionBottom) {
//            player.setDirection("up");
            jumping = true;
            jumpingHeight = player.getY() - player.getJumpingDistance();
        } else if (keyH.isLeftPressed()) {
//            player.setX(player.getX() + player.getSpeedX());
            player.moveLeft();
            moveNext();
        } else if (keyH.isRightPressed()) {
            player.moveRight();
            moveNext();
        }

        if (jumping) {
            player.setY(player.getY() - player.getSpeedY());

            if (player.getY() <= jumpingHeight || isCollisionTop) {
                jumping = false;
            }
        }
    }

    /**
     * Releases the logic of player's moving ( + effect "map camera")
     */
    private void moveNext() {
        if (!isCollisionMove) {
            int offset = (player.getX() - map.getWidth() / 2);
            if (offset > 0 && player.getSpeedX() > 0 && map.getMaxOffsetX() >= map.getOffsetX() + player.getSpeedX()) {
                map.setOffsetX(player.getSpeedX());
            } else if ((map.getOffsetX() + player.getSpeedX()) >= 0 && player.getSpeedX() < 0 && offset < 0) {
                map.setOffsetX(player.getSpeedX());
            } else if (player.getX() != map.getWidth() - Main.Configure.tileSize && player.getSpeedX() > 0 || player.getX() != Main.Configure.tileSize && player.getSpeedX() < 0) {
                player.setX(player.getX() + player.getSpeedX());
            }
        }
    }

    /**
     * logic about player's falling
     */
    private void moveDown() {
        if (!jumping && !isCollisionBottom) {
            player.setY(player.getY() + player.getSpeedY());
        }
    }

//    getters, setters
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public void setKeyH(KeyHandler keyH) {
        this.keyH = keyH;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setMonsters(Monster[] monsters) {
        this.monsters = monsters;
    }
}
