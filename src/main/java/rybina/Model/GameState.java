package rybina.Model;

import rybina.Controller.Controller;
import rybina.Controller.GameController;
import rybina.Controller.KeyHandler;
import rybina.Controller.MenuController;
import rybina.Helpers.Serialization.Serializator;
import rybina.Model.Entity.Entity;
import rybina.Model.Entity.Monster.Monster;
import rybina.Model.Entity.Player;
import rybina.Model.Levels.Levels;
import rybina.Model.Levels.Map;
import rybina.Model.Potion.Potion;

import java.io.Serializable;
import java.util.logging.Logger;

import static rybina.Helpers.Serialization.Serializator.*;

/**
 * The GameState class represents the state of the game.
 * It holds information about the player, levels, menu, controllers, and current state.
 */
public class GameState {
    Player player;                  // The player in the game
    Levels levels;                  // The levels in the game
    Menu menu;                      // The menu in the game
    Controller gameController;      // The game controller
    Controller menuController;      // The menu controller
    Controller currentController;   // The current active controller
    KeyHandler keyH;                // The key handler

    Logger logger = Logger.getLogger(getClass().getName());

    String currentState = "menu";   // The current state of the game

    String fileStoragePlayer = "/storage/player.txt";
    String fileStorageMonsters = "/storage/monsters.txt";
    String fileStoragePotions = "/storage/potions.txt";
    String fileStorageLevel = "/storage/level.txt";
    Serializator serializator = new Serializator();

    /**
     * Constructs a new GameState object.
     * Initializes the key handler, menu, and sets default values.
     * Loads the saved changes from storage.
     */
    public GameState() {
        this.keyH = new KeyHandler();
        this.menu = new Menu();
        this.menuController = new MenuController(keyH, menu, this);
        setDefaultValues();
        loadChanges();
    }

    /**
     * Sets the current state of the game.
     *
     * @param state The state to set. Valid values are "game", "menu", "finish", or "game over".
     */
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

    /**
     * Returns the current active controller.
     *
     * @return The current active controller.
     */
    public Controller getCurrentController() {
        return currentController;
    }

    /**
     * Returns the player in the game.
     *
     * @return The player in the game.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the levels in the game.
     *
     * @return The levels in the game.
     */
    public Levels getLevels() {
        return levels;
    }

    /**
     * Returns the current level in the game.
     *
     * @return The current level in the game.
     */
    public Map getCurrentLevel() {
        return levels.getCurrentLevel();
    }

    /**
     * Returns the menu in the game.
     *
     * @return The menu in the game.
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Returns the game controller.
     *
     * @return The game controller.
     */
    public Controller getGameController() {
        return gameController;
    }

    /**
     * Returns the menu controller.
     *
     * @return The menu controller.
     */
    public Controller getMenuController() {
        return menuController;
    }

    /**
     * Returns the current state of the game.
     *
     * @return The current state of the game.
     */
    public String getCurrentState() {
        return currentState;
    }

    /**
     * Sets the default values for the game.
     * Creates a new player, levels, and game controller.
     * Sets the current state to "menu".
     */
    public void setDefaultValues() {
        this.player = new Player();
        this.levels = new Levels();
        this.gameController = new GameController(player, keyH, this);
        setCurrentState("menu");
    }

    /**
     * Saves the changes in the game to storage.
     * It saves the player, level, monsters, and potions information.
     */
    public void saveChanges() {
        serializator.saveObject(new EntityInfo(player), fileStoragePlayer);
        serializator.saveObject(new LevelInfo(levels), fileStorageLevel);
        int monstersCount = levels.getCurrentMonsters().length;
        EntityInfo[] monsters = new EntityInfo[monstersCount];
        for (int i = 0; i < monstersCount; i++) {
            monsters[i] = new EntityInfo(levels.getCurrentMonsters()[i]);
        }
        serializator.saveObjectsArray(monsters, fileStorageMonsters);
        serializator.saveObjectsArray(getCurrentLevel().getPotions(), fileStoragePotions);
    }

    /**
     * Loads the saved changes from storage.
     * It loads the player, level, monsters, and potions information.
     */
    public void loadChanges() {
        EntityInfo playerInfo = serializator.loadObject(fileStoragePlayer, EntityInfo.class);
        if (playerInfo != null) {
            if (playerInfo.health == 0) {
                setDefaultValues();
                return;
            }
            player.setX(playerInfo.x);
            player.setY(playerInfo.y);
            player.setHealth(playerInfo.health);
        }

        EntityInfo[] monstersInfo = serializator.loadObjectArray(fileStorageMonsters, EntityInfo.class);
        Potion[] potions = serializator.loadObjectArray(fileStoragePotions, Potion.class);
        LevelInfo levelInfo = serializator.loadObject(fileStorageLevel, LevelInfo.class);
        if (monstersInfo != null && potions != null && levelInfo != null) {
            levels.setCurrentLevel(levelInfo.levelNumber);
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

    /**
     * The EntityInfo class represents the information about an entity.
     * It holds the x and y coordinates, health, and killed state.
     */
    public static class EntityInfo implements Serializable {
        public int x = 0;                // The x-coordinate
        public int y = 0;                // The y-coordinate
        public double health = 0;        // The health
        public boolean isKilled = false; // The killed state

        /**
         * Constructs a new EntityInfo object based on the given entity.
         *
         * @param entity The entity to get the information from.
         */
        public EntityInfo(Entity entity) {
            setEntityInfo(entity);
        }

        /**
         * Sets the entity information based on the given entity.
         *
         * @param entity The entity to get the information from.
         */
        public void setEntityInfo(Entity entity) {
            this.x = entity.getX();
            this.y = entity.getY();
            this.health = entity.getHealth();
            this.isKilled = entity.isKilled();
        }
    }

    /**
     * The LevelInfo class represents the information about a level.
     * It holds the offset x and y coordinates, and the level number.
     */
    public static class LevelInfo implements Serializable {
        public int offsetX;      // The offset x-coordinate
        public int offsetY;      // The offset y-coordinate
        public int levelNumber;  // The level number

        /**
         * Constructs a new LevelInfo object based on the given levels.
         *
         * @param levels The levels to get the information from.
         */
        public LevelInfo(Levels levels) {
            this.offsetX = levels.getCurrentLevel().getOffsetX();
            this.offsetY = levels.getCurrentLevel().getOffsetY();
            this.levelNumber = levels.getCurrentLevelNumber();
        }
    }

    /**
     * Returns the key handler.
     *
     * @return The key handler.
     */
    public KeyHandler getKeyH() {
        return keyH;
    }

    /**
     * Sets the key handler.
     *
     * @param keyH The key handler to set.
     */
    public void setKeyH(KeyHandler keyH) {
        this.keyH = keyH;
    }
}
