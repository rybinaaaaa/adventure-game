package rybina.Controller;

import rybina.Model.Entity.Entity;
import rybina.Model.GameState;
import rybina.Model.Menu;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * The MenuController class handles the user input and changes game state.
 * Extends the {@link Controller} class.
 */
public class MenuController extends Controller {

    KeyHandler keyH;
    Menu menu;
    GameState gameState;

    Timer timer = new Timer();
    boolean setting = false;

    Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Constructs a MenuController object with the specified parameters.
     *
     * @param keyH      the KeyHandler object for user input
     * @param menu      the Menu object for displaying the menu options (model)
     * @param gameState the GameState object for managing the game state
     */
    public MenuController(KeyHandler keyH, Menu menu, GameState gameState) {
        this.keyH = keyH;
        this.menu = menu;
        this.gameState = gameState;
    }

    /**
     * Sets the next menu choice based on the user input.
     * Handles the actions to be performed when a menu option is selected.
     */
    public void setNextChoice() {
        if (keyH.isUpPressed()) {
            menu.setSelectedOption(-1);
        }
        if (keyH.isDownPressed()) {
            menu.setSelectedOption(1);
        }

        if (keyH.isEnterPressed()) {
            if (menu.getSelectedOption() == "new game") {
                logger.info("player has chose new game");
                gameState.setDefaultValues();
                logger.info("loading game");
                gameState.setCurrentState("game");
            }
            if (menu.getSelectedOption() == "load game") {
                gameState.setCurrentState("game");
                logger.info("player has chose load uncompleted game");
            }
            if (menu.getSelectedOption() == "exit") {
                System.exit(0);
            }
        }
    }

    /**
     * Sets the setting variable to true for a brief period to prevent rapid menu changes.
     */
    public void unSetting() {
        setting = true;
        timer.schedule(new TimerTask() {
            public void run() {
             setting = false;
            }
        }, 90);
    }

    /**
     * handles player's choice
     */
    public void update() {
        if (!setting) {
            setNextChoice();
            unSetting();
        }
    }
}
