package main;

import main.Controller.KeyHandler;
import main.Model.Levels.Levels;
import main.Model.Entity.Player;
import main.Controller.PlayerController;
import main.View.Screen;
import main.Main.Configure;

public class GameEngine implements Runnable {

    KeyHandler keyH = new KeyHandler();
    Player player = new Player(keyH);


    Levels levels = new Levels();

    PlayerController playerController = new PlayerController(player, keyH, levels);


    public Screen screen = new Screen(Configure.originalTileSize, Configure.scale, Configure.maxScreenColumn, Configure.maxScreenRow, player, keyH, levels.getCurrentLevel());
    int FPS = 60;
    Thread gameThread;

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = Math.pow(10, 9) / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
//        game loop
        while (gameThread != null) {
            screen.repaint();

            update();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= Math.pow(10, 6);
//                для тредов надо переводить в МИЛЛИсекунды

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        playerController.update();
    }
}