package main.Controller;

import main.Model.GameState;
import main.Model.Menu;

import java.util.Timer;
import java.util.TimerTask;

public class MenuController extends Controller {

    KeyHandler keyH;
    Menu menu;
    GameState gameState;

    Timer timer = new Timer();
    boolean setting = false;

    public MenuController(KeyHandler keyH, Menu menu, GameState gameState) {
        this.keyH = keyH;
        this.menu = menu;
        this.gameState = gameState;
//        getNextChoice();
//        initUserChoice();
    }

    public void setNextChoice() {
        System.out.println("bam");
        if (keyH.upPressed) {
            menu.setSelectedOption(-1);
        }
        if (keyH.downPressed) {
            menu.setSelectedOption(1);
        }

        if (keyH.enterPressed) {
            if (menu.getSelectedOption() == "new game") {
                gameState.setDefaultValues();
            }
            gameState.setCurrentState("game");
        }
    }

    public void unSetting() {
        setting = true;
        timer.schedule(new TimerTask() {
            public void run() {
             setting = false;
            }
        }, 70);
    }

    public void update() {
//        System.out.println("я работаю");
//        System.out.println("сеттинг = " + setting);
        if (!setting) {
            unSetting();
            setNextChoice();
        }
    }
}
