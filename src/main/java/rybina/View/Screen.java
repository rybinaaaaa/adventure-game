package rybina.View;

import rybina.Controller.KeyHandler;
import rybina.Main;
import rybina.Model.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The Screen class represents the game screen where the game graphics are rendered.
 * It extends the JPanel class and handles the drawing of game elements based on the game state.
 */
public class Screen extends JPanel {
    // SCREEEN SETTINGS
    public int tileSize;
    public int maxScreenColumn;
    public int maxScreenRow;
    int screenWidth;
    int screenHeight;

    PlayerRenderer playerRenderer;
    MapRenderer mapRenderer;
    HealthBarRenderer playerHealth;

    MenuScreen menuScreen;
    GameState gameState;

    /**
     * Constructs a Screen object with the specified game state and key handler.
     *
     * @param gameState the game state containing the current game information
     * @param keyH      the key handler for handling user input
     */
    public Screen(GameState gameState, KeyHandler keyH) {
        this.addKeyListener(keyH);
        this.tileSize = Main.Configure.tileSize;
        this.maxScreenColumn = Main.Configure.maxScreenColumn;
        this.maxScreenRow = Main.Configure.maxScreenRow;
        this.gameState = gameState;
        this.menuScreen = new MenuScreen(gameState.getMenu());
        screenWidth = tileSize * maxScreenColumn;
        screenHeight = tileSize * maxScreenRow;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    /**
     * Draws the game elements on the screen using the specified Graphics2D object.
     *
     * @param g2 the Graphics2D object used for drawing
     */
    public void drawGame(Graphics2D g2) {
        this.playerRenderer = new PlayerRenderer(gameState.getPlayer());
        this.playerHealth = new HealthBarRenderer(gameState.getPlayer());
        this.mapRenderer = new MapRenderer(gameState.getCurrentLevel());

        mapRenderer.draw(g2);

        if (Objects.equals(gameState.getCurrentState(), "game over")) {
            g2.setColor(Color.white);
            g2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 130));
            g2.drawString("GAME OVER", 0, Main.Configure.maxScreenRow * Main.Configure.tileSize / 2);
            return;
        }

        playerRenderer.draw(g2);
        playerHealth.draw(g2);
    }

    /**
     * Draws the menu screen on the screen using the specified Graphics2D object.
     *
     * @param g2 the Graphics2D object used for drawing
     */
    public void drawMenu(Graphics2D g2) {
        menuScreen.draw(g2);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState.getCurrentState().equals("game")) {
            drawGame(g2);
        }
        if (gameState.getCurrentState().equals("game over")) {
            drawGame(g2);
        }
        if (gameState.getCurrentState().equals("menu")) {
            drawMenu(g2);
        }
        if (gameState.getCurrentState().equals("finish")) {
            g2.setColor(Color.white);
            g2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 100));
            g2.drawString("GAME FINISHED", 0, Main.Configure.maxScreenRow * Main.Configure.tileSize / 2);
        }

        g2.dispose();
    }
}
