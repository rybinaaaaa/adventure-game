package rybina;

import rybina.Controller.KeyHandler;
import rybina.Model.GameState;
import rybina.View.Screen;

/**
 * The GameEngine class is responsible for running the game loop and managing the game state.
 * It implements the Runnable interface to be executed in a separate thread.
 */
public class GameEngine implements Runnable {

    GameState gameState = new GameState();
    public Screen screen = new Screen(gameState, gameState.getKeyH());
    int FPS = 120;
    Thread gameThread;

    /**
     * Starts the game thread.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = Math.pow(10, 9) / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        // Game loop
        while (gameThread != null) {
            screen.repaint();

            update();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= Math.pow(10, 6);

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

    /**
     * Updates the game state and performs necessary actions based on the current game state.
     */
    public void update() {
        gameState.getCurrentController().update();

        if (gameState.getCurrentState().equals("game")) {
            gameState.saveChanges();
        }
        if (gameState.getKeyH().escPressed && gameState.getCurrentState().equals("game")) {
            gameState.setCurrentState("menu");
        }
    }
}
