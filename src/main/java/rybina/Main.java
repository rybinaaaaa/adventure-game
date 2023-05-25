package rybina;

import javax.swing.*;
import java.util.Objects;
import java.util.logging.LogManager;

public class Main {

    public static class Configure {
        public final static int originalTileSize = 16;
        //    16 x 16 tile (клетка)
        public final static int scale = 3;
        public final static int tileSize = 48;
        final static public int maxScreenColumn = 16;
        final static public int maxScreenRow = 12;
    }

    public static void main(String[] args) {
//        System.out.println(args[0]);
        if (args.length != 0 && Objects.equals(args[0], "LOG_OFF")) {
            LogManager.getLogManager().reset();
        }
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
//        gamePanel.startGameThread();
    }
}
