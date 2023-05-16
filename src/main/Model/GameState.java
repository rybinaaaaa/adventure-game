package main.Model;

import main.Controller.Controller;
import main.Controller.GameController;
import main.Controller.KeyHandler;
import main.Controller.MenuController;
import main.Model.Entity.Monster.Monster;
import main.Model.Entity.Player;
import main.Model.Helpers.EntityInfo;
import main.Model.Helpers.LevelInfo;
import main.Model.Levels.Levels;
import main.Model.Levels.Map;
import main.Model.Potion.Potion;

import java.io.*;
import java.lang.reflect.Array;

public class GameState implements Serializable {
    Player player;
    Levels levels;
    Menu menu;
    Controller gameController;
    Controller menuController;
    Controller currentController;
    KeyHandler keyH;

//    EntityInfo playerInfo;
//    EntityInfo[] monstersInfo;

    String currentState = "menu";

    String fileStoragePlayer = "/Users/rybina/CTU/witch1000/src/res/storage/player.txt";
    String fileStorageMonsters = "/Users/rybina/CTU/witch1000/src/res/storage/monsters.txt";
    String fileStoragePotions = "/Users/rybina/CTU/witch1000/src/res/storage/potions.txt";
    String fileStorageLevel = "/Users/rybina/CTU/witch1000/src/res/storage/level.txt";

    public GameState(KeyHandler keyH) {
        System.out.println("work");
        this.keyH = keyH;
        setDefaultValues();
//        saveChanges();
//
        loadChanges();
        setCurrentState("menu");
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
        this.gameController = new GameController(player, keyH, levels);
        this.menu = new Menu();
        this.menuController = new MenuController(keyH, menu, this);
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

        EntityInfo playerInfo = (EntityInfo) loadObject(fileStoragePlayer);
        if (playerInfo != null) {
            if (playerInfo.health == 0) {
                setDefaultValues();
                return;
            }
            player.setX(playerInfo.x);
            player.setY(playerInfo.y);
            player.setHealth(playerInfo.health);
        }

        EntityInfo[] monstersInfo = convertArray(loadObjectArray(fileStorageMonsters), EntityInfo.class);
        Potion[] potions = convertArray(loadObjectArray(fileStoragePotions), Potion.class);
        LevelInfo levelInfo = (LevelInfo) loadObject(fileStorageLevel);
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
    }

    public Object loadObject(String fileName) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        Object object = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            if (fileInputStream.getChannel().size() != 0) {
                objectInputStream = new ObjectInputStream(fileInputStream);
                object = objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
            }
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    public Object[] loadObjectArray(String fileName) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        Object[] objects = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            if (fileInputStream.getChannel().size() != 0) {
                objectInputStream = new ObjectInputStream(fileInputStream);
                int objectCount = objectInputStream.readInt();
                objects = new Object[objectCount];
                for (int i = 0; i < objectCount; i++) {
                    objects[i] = objectInputStream.readObject();
                }
                objectInputStream.close();
                fileInputStream.close();
            }

        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    public void saveObject(Object object, String fileName) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveObjectsArray(Object[] objects, String fileName) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeInt(objects.length);
            for (Object object : objects) {
                objectOutputStream.writeObject(object);
            }
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> T[] convertArray(Object[] objects, Class<T> targetType) {
        if (objects == null) return null;
        T[] convertedArray = (T[]) Array.newInstance(targetType, objects.length);
        for (int i = 0; i < objects.length; i++) {
            convertedArray[i] = targetType.cast(objects[i]);
        }
        return convertedArray;
    }
}
