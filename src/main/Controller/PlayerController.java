package main.Controller;

import main.Model.Levels.Levels;
import main.Model.Levels.Map;
import main.Model.Potion.Potion;
import main.Model.Tiles.Tile;
import main.Model.Entity.Player;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerController {

    Timer timerC = new Timer();
    Player player;
    KeyHandler keyH;

    Levels levels;
    Map map;
    private int jumpingHeight = 0;
    private int timer = 10;

    private boolean isCollisionTop = false,
            isCollisionBottom = false,
            isCollisionMove = false;


    private void updateMoveState() {
        Tile top = map.getTile(player.getX(), player.getY());
        Tile bottom = map.getTile(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight());
        Tile present = map.getTile(player.getX() + player.getWidth() / 2, player.getY() + 48);
        Tile next = map.getTile(player.getX() + player.getWidth() * 4 / 5, player.getY() + 48);
        isCollisionTop = top.isCollision();
        isCollisionBottom = bottom.isCollision();
        isCollisionMove = next.isCollision();
        player.setHealth(player.getHealth() - present.getDamaging());

        Potion potion = map.getPotion(player.getX() + player.getWidth() / 2, player.getY() + 48);

        if (potion != null) {
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
                    }, 500);

            }
            map.deletePotion(potion);
        }
    }

    public PlayerController(Player player, KeyHandler keyH, Levels levels) {
        this.player = player;
        this.keyH = keyH;
        this.levels = levels;
        this.map = levels.getCurrentLevel();
    }

    public void update() {
        moving();
    }

    private boolean jumping = false;

    private void moving() {
        updateMoveState();
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

        timer--;

        if (timer <= 0) {
            player.setImage();
            timer = 10;
        }
    }

    private void moveNext() {
        if (!isCollisionMove) {
            int offset = (player.getX() - map.getWidth() / 2);
            if (offset > 0 && player.getSpeedX() > 0 && map.getMaxOffsetX() != map.getOffsetX()) {
                map.setOffsetX(player.getSpeedX());
            } else if (map.getOffsetX() != 0 && player.getSpeedX() < 0 && offset < 0) {
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
