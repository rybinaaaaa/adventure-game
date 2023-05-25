package main.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, escPressed, enterPressed;
    boolean keyReleased = false;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyReleased = false;
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = true;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyReleased = true;
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
    }
}
