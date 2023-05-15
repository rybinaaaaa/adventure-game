package main.Model;

import main.Controller.Controller;
import main.Controller.GameController;
import main.Controller.KeyHandler;
import main.Controller.MenuController;
import main.Model.Entity.Player;
import main.Model.Levels.Levels;
import main.Model.Levels.Map;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class GameState {
    Player player;
    Levels levels;
    Menu menu;
    Controller gameController;
    Controller menuController;
    Controller currentController;
    KeyHandler keyH;

    String currentState = "menu";

    String fileStorageName = "storage.xml";

    XMLEncoder encoder = null;

    public GameState(KeyHandler keyH) {
        this.keyH = keyH;
        setDefaultValues();
        saveChanges();

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
        this.player = new Player(keyH);
        this.levels = new Levels();
        this.gameController = new GameController(player, keyH, levels);
        this.menu = new Menu();
        this.menuController = new MenuController(keyH, menu, this);
    }


    public void saveChanges() {
        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileStorageName)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        encoder.writeObject(player);
    }
}
