package rybina.Model;

import rybina.Controller.Controller;
import rybina.Controller.GameController;
import rybina.Controller.KeyHandler;
import rybina.Controller.MenuController;
import rybina.Model.Entity.Entity;
import rybina.Model.Entity.Monster.Monster;
import rybina.Model.Entity.Player;
import rybina.Model.Levels.Levels;
import rybina.Model.Levels.Map;
import rybina.Model.Potion.Potion;

import java.io.Serializable;
import java.util.logging.Logger;

import static rybina.Helpers.Serialization.Serializator.*;

public class GameState implements Serializable {
    Player player;
    Levels levels;
    Menu menu;
    Controller gameController;
    Controller menuController;
    Controller currentController;
    KeyHandler keyH;

    Logger logger = Logger.getLogger(getClass().getName());

    String currentState = "menu";

    String fileStoragePlayer = "/Users/rybina/CTU/witch_adventures/src/main/resources/storage/player.txt";
    //    /Users/rybina/CTU/witch_adventures/src/main/resources/storage/player.txt
    String fileStorageMonsters = "/Users/rybina/CTU/witch_adventures/src/main/resources/storage/monsters.txt";
    String fileStoragePotions = "/Users/rybina/CTU/witch_adventures/src/main/resources/storage/potions.txt";
    String fileStorageLevel = "/Users/rybina/CTU/witch_adventures/src/main/resources/storage/level.txt";

    public GameState() {
        this.keyH = new KeyHandler();
        this.menu = new Menu();
        this.menuController = new MenuController(keyH, menu, this);
        setDefaultValues();
//        saveChanges();
        loadChanges();

//        setCurrentState("menu");
    }

    public void setCurrentState(String state) {
        switch (state) {
            case "game":
                this.currentState = state;
                this.currentController = gameController;
                break;
            case "menu":
                this.currentState = state;
                this.currentController = menuController;
                break;
            case "finish":
            case "game over":
                this.currentState = state;
                break;
            default:
                setCurrentState("game");
                break;
        }
    }

    public Controller getCurrentController() {
        return currentController;
    }

    public Player getPlayer() {
        return player;
    }

    public Levels getLevels() {
        return levels;
    }

    public Map getCurrentLevel() {
        return levels.getCurrentLevel();
    }

    public Menu getMenu() {
        return menu;
    }

    public Controller getGameController() {
        return gameController;
    }

    public Controller getMenuController() {
        return menuController;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setDefaultValues() {
        this.player = new Player();
        this.levels = new Levels();
        this.gameController = new GameController(player, keyH, levels, this);
        setCurrentState("menu");
    }


    public void saveChanges() {
        saveObject(new EntityInfo(player), fileStoragePlayer);
        saveObject(new LevelInfo(levels), fileStorageLevel);
        int monstersCount = levels.getCurrentMonsters().length;
        EntityInfo[] monsters = new EntityInfo[monstersCount];
        for (int i = 0; i < monstersCount; i++) {
            monsters[i] = new EntityInfo(levels.getCurrentMonsters()[i]);
        }
        saveObjectsArray(monsters, fileStorageMonsters);
        saveObjectsArray(getCurrentLevel().getPotions(), fileStoragePotions);
    }

    public void loadChanges() {
        EntityInfo playerInfo = loadObject(fileStoragePlayer, EntityInfo.class);
        if (playerInfo != null) {
            if (playerInfo.health == 0) {
                setDefaultValues();
                return;
            }
            player.setX(playerInfo.x);
            player.setY(playerInfo.y);
            player.setHealth(playerInfo.health);
        }

        EntityInfo[] monstersInfo = loadObjectArray(fileStorageMonsters, EntityInfo.class);
        Potion[] potions = loadObjectArray(fileStoragePotions, Potion.class);
        LevelInfo levelInfo = loadObject(fileStorageLevel, LevelInfo.class);
        if (monstersInfo != null && potions != null && levelInfo != null) {
            levels.setCurrentLevel(levelInfo.levelNumber);
            System.out.println(levels.getCurrentLevel());
            getCurrentLevel().setOffsetX(levelInfo.offsetX);
            getCurrentLevel().setOffsetY(levelInfo.offsetY);
            Monster[] monsters = levels.getCurrentMonsters();
            if (monstersInfo.length == monsters.length) {
                for (int i = 0; i < monstersInfo.length; i++) {
                    monsters[i].setX(monstersInfo[i].x);
                    monsters[i].setY(monstersInfo[i].y);
                    monsters[i].setHealth(monstersInfo[i].health);
                    monsters[i].setKilled(monstersInfo[i].isKilled);
                }
            }
        }
        ((GameController) gameController).setPlayer(player);
        ((GameController) gameController).setMap(getCurrentLevel());
        ((GameController) gameController).setMonsters(getCurrentLevel().getMonsters());
    }

    public static class EntityInfo implements Serializable {
        public int x = 0;
        public int y = 0;
        public double health = 0;

        public boolean isKilled = false;

        public EntityInfo(Entity entity) {
            setEntityInfo(entity);
        }

        public void setEntityInfo(Entity entity) {
            this.x = entity.getX();
            this.y = entity.getY();
            this.health = entity.getHealth();
            this.isKilled = entity.isKilled();
        }
    }

    public static class LevelInfo implements Serializable {
        public int offsetX, offsetY;
        public int levelNumber;

        public LevelInfo(Levels levels) {
            this.offsetX = levels.getCurrentLevel().getOffsetX();
            this.offsetY = levels.getCurrentLevel().getOffsetY();
            this.levelNumber = levels.getCurrentLevelNumber();
        }
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public void setKeyH(KeyHandler keyH) {
        this.keyH = keyH;
    }
}
