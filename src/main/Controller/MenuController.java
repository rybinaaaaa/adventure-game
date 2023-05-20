package main.Controller;

import main.Model.GameState;
import main.Model.Menu;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class MenuController extends Controller {

    KeyHandler keyH;
    Menu menu;
    GameState gameState;

    Timer timer = new Timer();
    boolean setting = false;

    Logger logger = Logger.getLogger(getClass().getName());

    public MenuController(KeyHandler keyH, Menu menu, GameState gameState) {
        this.keyH = keyH;
        this.menu = menu;
        this.gameState = gameState;
//        getNextChoice();
//        initUserChoice();
    }

    public void setNextChoice() {
        if (keyH.upPressed) {
            menu.setSelectedOption(-1);
        }
        if (keyH.downPressed) {
            menu.setSelectedOption(1);
        }

        if (keyH.enterPressed) {
            if (menu.getSelectedOption() == "new game") {
                logger.info("restarting game");
                gameState.setDefaultValues();
                logger.info("loading game");
                gameState.setCurrentState("game");
            }
            if (menu.getSelectedOption() == "load game") {
                gameState.setCurrentState("game");
                logger.info("loading game");
            }
            if (menu.getSelectedOption() == "exit") {
                System.exit(0);
            }
        }
    }

    public void unSetting() {
        setting = true;
        timer.schedule(new TimerTask() {
            public void run() {
             setting = false;
            }
        }, 90);
    }

    public void update() {
//        System.out.println("я работаю");
//        System.out.println("сеттинг = " + setting);
//        if (keyH.keyReleased) {
//            setting = true;
//           unSetting();
//        }
        if (!setting) {
            unSetting();
            setNextChoice();
        }
    }
}
