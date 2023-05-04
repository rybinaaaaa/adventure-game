package main.Model.Entity;

import main.Controller.KeyHandler;
import main.View.Screen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    KeyHandler keyH;
    Screen screen;

    private NodeImage[] runAnimation;
    private NodeImage[] atackAnimation;
    private NodeImage[] deathAnimation;

    public Player(KeyHandler keyH) {
        this.keyH = keyH;

        this.runAnimation = loadAnimation("/res/player/run/B_witch_run.png", 32, 48, 8, 1, 0, 0);
        setDefaultValues();
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
//    private BufferedImage[] running;

    public void setAnimationType(String option) {
        switch (option) {
            case "run":
                animationPresent = runAnimation;
        }
    }


    public void setDefaultValues() {
        super.setDefaultValues();
        setAnimationType("run");
        image = animationPresent[0];
    }
}
