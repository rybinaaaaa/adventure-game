package main;

import main.Controller.KeyHandler;
import main.Controller.Controller;
import main.Controller.MouseListener;
import main.Main.Configure;
import main.Model.Entity.Player;
import main.Model.Levels.Levels;
import main.View.Screen;

public class GameEngine implements Runnable {

    KeyHandler keyH = new KeyHandler();
    MouseListener mouseL = new MouseListener();
    Player player = new Player(keyH);


    Levels levels = new Levels();

    Controller controller = new Controller(player, keyH, mouseL, levels);


    public Screen screen = new Screen(Configure.originalTileSize, Configure.scale, Configure.maxScreenColumn, Configure.maxScreenRow, player, keyH, mouseL, levels.getCurrentLevel());
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
        controller.update();
    }
}
