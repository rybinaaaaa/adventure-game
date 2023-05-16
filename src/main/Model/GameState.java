package main.Model;

import main.Controller.Controller;
import main.Controller.GameController;
import main.Controller.KeyHandler;
import main.Controller.MenuController;
import main.Model.Entity.Monster.Monster;
import main.Model.Entity.Player;
import main.Model.Helpers.EntityInfo;
import main.Model.Levels.Levels;
import main.Model.Levels.Map;
import main.Model.Potion.Potion;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class GameState implements Serializable {
//    public class EntityInfo implements Serializable {
//        public int x = 0;
//        public int y = 0;
//        public double health = 0;
//
//        public EntityInfo(Entity entity) {
//            setEntityInfo(entity);
//        }
//
//        public void setEntityInfo(Entity entity) {
//            this.x = entity.getX();
//            this.y = entity.getY();
//            this.health = entity.getHealth();
//        }
//    }

    private static class levelInfo implements Serializable {
        public Potion[] potions;
        public Monster[] monsters;
        public int levelNumber;
    }

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

    XMLEncoder encoder = null;
    XMLDecoder decoder = null;

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
//        playerInfo.x = player.getX();
//        playerInfo.y = player.getY();
//        playerInfo.health = player.getHealth();
//        player.setDefaultValues();
        this.levels = new Levels();
        this.gameController = new GameController(player, keyH, levels);
        this.menu = new Menu();
        this.menuController = new MenuController(keyH, menu, this);
    }


    public void saveChanges() {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileStoragePlayer);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(new EntityInfo(player));
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadChanges() {
//        FileInputStream fileInputStream = null;
//        ObjectInputStream objectInputStream = null;

//        try {
//            fileInputStream = new FileInputStream(fileStoragePlayer);
//            if (fileInputStream.getChannel().size() != 0) {
//                objectInputStream = new ObjectInputStream(fileInputStream);
//                this.levels = (Levels) objectInputStream.readObject();
//                objectInputStream.close();
//            }
//
//        } catch (ClassNotFoundException | IOException e) {
//            throw new RuntimeException(e);
//        }

        EntityInfo playerInfo = (EntityInfo) loadObject(fileStoragePlayer);
        if (playerInfo != null) {
            player.setX(playerInfo.x);
            player.setY(player.getY());
            player.setHealth(playerInfo.health);
        }
    }

    public Object loadObject(String fileName) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        Object object = null;
        try {
            fileInputStream = new FileInputStream(fileStoragePlayer);
            if (fileInputStream.getChannel().size() != 0) {
                objectInputStream = new ObjectInputStream(fileInputStream);
                object = objectInputStream.readObject();
                objectInputStream.close();
//                return object;
            }

        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    public Object loadObjectArray(String fileName) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        Object object = null;
        try {
            fileInputStream = new FileInputStream(fileStoragePlayer);
            if (fileInputStream.getChannel().size() != 0) {
                objectInputStream = new ObjectInputStream(fileInputStream);
                object = objectInputStream.readObject();
                objectInputStream.close();
//                return object;
            }

        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return object;
    }
}
