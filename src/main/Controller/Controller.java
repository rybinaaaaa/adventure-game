package main.Controller;

import main.Model.Entity.Monster.Monster;
import main.Model.Entity.Player;
import main.Model.Levels.Levels;
import main.Model.Levels.Map;
import main.Model.Potion.Potion;
import main.Model.Tiles.Tile;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    Timer timerC = new Timer();
    Player player;
    KeyHandler keyH;

    Levels levels;
    Map map;
    Monster[] monsters;

    private boolean jumping = false;

    boolean leanBack = false;
    private int jumpingHeight = 0;
    private boolean isCollisionTop = false,
            isCollisionBottom = false,
            isCollisionMove = false,
            isCollisionBack = false;


    private void updateCollision() {
        Tile top = map.getTile(player.getX(), player.getY());
        Tile bottom = map.getTile(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight());
        Tile present = map.getTile(player.getX() + player.getWidth() / 2, player.getY() + 48);
        Tile next = map.getTile(player.getX() + player.getWidth() * 4 / 5, player.getY() + 48);
        Tile back = map.getTile(player.getX()  - player.getWidth() * 2 / 5, player.getY() + 48);
        System.out.println(back);
        isCollisionTop = top.isCollision();
        isCollisionBottom = bottom.isCollision();
        isCollisionMove = next.isCollision();
        isCollisionBack = back.isCollision();
        takeDamage(present.getDamaging());
    }

    private void takePotion(Potion potion) {
        player.setAnimationType("charge");
        timerC.schedule(new TimerTask() {
            public void run() {
                player.setAnimationType("run");
            }
        }, 1000);

        switch (potion.getType()) {
            case "atack":
                break;
            case "health":
                player.setHealth(player.getHealth() + 20);
                break;
            case "speed":
                player.setSpeedX(player.getSpeedX() * 2);
                timerC.schedule(new TimerTask() {
                    public void run() {
                        player.setSpeedX(player.getSpeedX() / 2);
                    }
                }, 5000);

        }
    }

    private void updatePotion() {
        Potion potion = map.getPotion(player.getX() + player.getWidth() / 2, player.getY() + 48);

        if (potion != null) {
            takePotion(potion);
            map.deletePotion(potion);
        }
    }

    private void takeDamage(double damage) {
        if (damage <= 0) return;
        player.setHealth(player.getHealth() - damage);
        if (player.getAnimationType() == "damage") return;
        player.setAnimationType("damage");
        timerC.schedule(new TimerTask() {
            public void run() {
                player.setAnimationType("run");
            }
        }, 1000);
    }

    private void updateMonsterCollision() {
        leanBack();
        System.out.println(leanBack);
        Monster monster = map.getMonster(player.getX() + player.getWidth() / 2, player.getY() + 48);
        if (monster != null) {
            isCollisionMove = false;
            leanBack = true;
            timerC.schedule(new TimerTask() {
                public void run() {
                    leanBack = false;
                }
            }, 1000);

            takeDamage(monster.getDamage());
        }

    }

    public void leanBack() {
        if (leanBack && !isCollisionBack) {
            int offset = (player.getX() - map.getWidth() / 2);
//            если оффсет больше нуля и игрок двигается направо а также не в самом уонце карты то
//            если игрок в конце карты и его скорость меньше нуляб то при отбрасывании он не должен упираться назад
            if (offset > 0 && player.getSpeedX() < 0 && map.getMaxOffsetX() >= map.getOffsetX() - player.getSpeedX()) {
                map.setOffsetX(-player.getSpeedX());
            } else if ((map.getOffsetX() - player.getSpeedX()) >= 0 && player.getSpeedX() > 0 && offset < 0) {
                map.setOffsetX(-player.getSpeedX());
            } else if (player.getX() != map.getWidth() - 48 && player.getSpeedX() < 0 || player.getX() != 48 && player.getSpeedX() > 0) {
                player.setX(player.getX() - player.getSpeedX());
            }
        }
    }

    public Controller(Player player, KeyHandler keyH, Levels levels) {
        this.player = player;
        this.keyH = keyH;
        this.levels = levels;
        this.map = levels.getCurrentLevel();
        initPlayerAnimation();
        this.monsters = map.getMonsters();
        initMonsterMoving();
    }

    public void initPlayerAnimation() {
        timerC.schedule(new TimerTask() {
            public void run() {
                player.setImage();
            }
        }, 0, 200);
    }

    public void initMonsterMoving() {
        timerC.schedule(new TimerTask() {
            public void run() {
                for (Monster monster : monsters) {
                    monster.setImage();
                    monster.setX(monster.getX() + monster.getSpeedX());
                    if (monster.getInitialX() + monster.getDistanceRange() <= monster.getX()) {
                        monster.moveLeft();
                    } else if (monster.getX() <= monster.getInitialX()) {
                        monster.moveRight();
                    }
                }
            }
        }, 0, 200);
    }

    public void update() {
        updateMonsterCollision();
        updateCollision();
        updatePotion();
        moving();
    }

    private void moving() {
        moveDown();
        if (keyH.upPressed && !jumping && isCollisionBottom) {
//            player.setDirection("up");
            jumping = true;
            jumpingHeight = player.getY() - player.getJumpingDistance();
        } else if (keyH.leftPressed) {
//            player.setX(player.getX() + player.getSpeedX());
            player.moveLeft();
            moveNext();
        } else if (keyH.rightPressed) {
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


    private void moveNext() {
        if (!isCollisionMove && !isCollisionTop) {
            int offset = (player.getX() - map.getWidth() / 2);
            if (offset > 0 && player.getSpeedX() > 0 && map.getMaxOffsetX() >= map.getOffsetX() + player.getSpeedX()) {
                map.setOffsetX(player.getSpeedX());
            } else if ((map.getOffsetX() + player.getSpeedX()) >= 0 && player.getSpeedX() < 0 && offset < 0) {
                map.setOffsetX(player.getSpeedX());
            } else if (player.getX() != map.getWidth() - 48 && player.getSpeedX() > 0 || player.getX() != 48 && player.getSpeedX() < 0) {
                player.setX(player.getX() + player.getSpeedX());
            }
        }
    }

    private void moveDown() {
        if (!jumping && !isCollisionBottom) {
            player.setY(player.getY() + player.getSpeedY());
        }
    }
}
