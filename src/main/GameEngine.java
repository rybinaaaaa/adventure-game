package main;

import main.Controller.GameController;
import main.Controller.KeyHandler;
import main.Controller.MouseListener;
import main.Model.Entity.Player;
import main.Model.GameState;
import main.Model.Levels.Levels;
import main.View.Screen;

public class GameEngine implements Runnable {

    KeyHandler keyH = new KeyHandler();
//    MouseListener mouseL = new MouseListener();
//    Player player = new Player(keyH);
//    Levels levels = new Levels();
//    GameController gameController = new GameController(player, keyH, levels);

    GameState gameState = new GameState(keyH);
    public Screen screen = new Screen(gameState, keyH);
    int FPS = 120;
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
//            screen.repaint();
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
//        gameController.update();
        gameState.getCurrentController().update();

        if (gameState.getCurrentState() == "game") {
            gameState.saveChanges();
        }
            if (keyH.escPressed && gameState.getCurrentState() == "game") {
            gameState.setCurrentState("menu");
        }
    }
}
