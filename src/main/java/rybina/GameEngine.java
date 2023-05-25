package rybina;

import rybina.Controller.KeyHandler;
import rybina.Model.GameState;
import rybina.View.Screen;

public class GameEngine implements Runnable {

//    MouseListener mouseL = new MouseListener();
//    Player player = new Player(keyH);
//    Levels levels = new Levels();
//    GameController gameController = new GameController(player, keyH, levels);

    GameState gameState = new GameState();
    public Screen screen = new Screen(gameState, gameState.getKeyH());
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
        if (gameState.getKeyH().escPressed && gameState.getCurrentState() == "game") {
            gameState.setCurrentState("menu");
        }
    }
}
