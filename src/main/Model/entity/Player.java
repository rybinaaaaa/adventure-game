package main.Model.entity;

import main.Controller.KeyHandler;
import main.View.Screen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Player extends Entity {

    Screen screen;
    KeyHandler keyH;

    public Player(KeyHandler keyH) {
        this.keyH = keyH;

        this.runAnimation = loadAnimation("/res/player/run/B_witch_run.png", 32, 48, 8);
        setDefaultValues();
    }

//    private BufferedImage[] running;

    private NodeImage[] runAnimation;
    private NodeImage[] atackAnimation;
    private NodeImage[] deathAnimation;
    private NodeImage[] animationPresent;

    public void setAnimationType(String option) {
        switch (option) {
            case "run":
                animationPresent = runAnimation;
        }
    }

    private NodeImage[] loadAnimation(String src, int width, int height, int count) {
        BufferedImage animation = null;
        NodeImage[] animationList = new NodeImage[count];
        try {
            animation = ImageIO.read(getClass().getResourceAsStream(src));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < count; i++) {
            assert animation != null;
            NodeImage node = new NodeImage(animation.getSubimage(0, i * height, width, height));
            animationList[i] = node;
        }

        for (int i = 0; i < count; i++) {
            if (i != 0) {
                animationList[i].prevNode = animationList[i - 1];
            } else {
                animationList[i].prevNode = animationList[count - 1];
            }
            if (i != count - 1) {
                animationList[i].nextNode = animationList[i + 1];
            } else {
                animationList[i].nextNode = animationList[0];
            }
        }
        return animationList;
    }


    public void setDefaultValues() {
        super.setDefaultValues();
        setAnimationType("run");
        image = animationPresent[0];
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public int getJumpingDistance() {
        return jumpingDistance;
    }



    public void setJumpingDistance(int jumpingDistance) {
        this.jumpingDistance = jumpingDistance;
    }
}
