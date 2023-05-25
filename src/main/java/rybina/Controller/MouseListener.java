package rybina.Controller;

import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {
    boolean isPressed = false;
    private int x = 0;

    public boolean isPressed() {
        return isPressed;
    }

    private int y = 0;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        isPressed = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isPressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
