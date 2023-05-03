package main;

import main.Controller.KeyHandler;
import main.Model.Map;
import main.Model.entity.Player;
import main.Controller.PlayerController;
import main.View.Screen;

public class GameEngine implements Runnable {

    final static int originalTileSize = 16;
    //    16 x 16 tile (клетка)
    final static int scale = 3;

    final static public int maxScreenColumn = 16;
    final static public int maxScreenRow = 12;

    KeyHandler keyH = new KeyHandler();
    Player player = new Player(keyH);

    //    player.setX
    Map map = new Map(maxScreenRow, maxScreenColumn, 32, 12);

    PlayerController playerController = new PlayerController(player, keyH, map);


    public Screen screen = new Screen(originalTileSize, scale, maxScreenColumn, maxScreenRow, player, keyH, map);
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
