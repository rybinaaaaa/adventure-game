package main;

import main.View.Screen;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");
//
//        GamePanel gamePanel = new GamePanel();
//
        GameEngine gameEngine = new GameEngine();
        window.add(gameEngine.screen);
//        window.add(gamePanel);
        window.pack();
//        Вызывает изменение размера этого окна в соответствии с предпочтительным размером
//        и расположению его субкомпонентов (=GamePanel).  и под setLocationRelativeTo

//        estabilishes the screen to the center
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gameEngine.startGameThread();
    }
}
