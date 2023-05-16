package main.View;

import main.Controller.KeyHandler;
import main.Controller.MouseListener;
import main.Main;
import main.Model.Entity.Player;
import main.Model.GameState;
import main.Model.Levels.Map;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {
    //    SCREEEN SETTINGS

    public int tileSize;

    public int maxScreenColumn;
    public int maxScreenRow;
    int screenWidth; // 768 pixels
    int screenHeight; // 576 pixels
//    Player player;
    PlayerRenderer playerRenderer;
    MapRenderer mapRenderer;
    HealthBarRenderer playerHealth;

    MenuScreen menuScreen;
    GameState gameState;

    public Screen(GameState gameState, KeyHandler keyH) {

        this.addKeyListener(keyH);

//        this.originalTileSize = Main.Configure.originalTileSize;
//        this.scale = Main.Configure.scale;
        this.tileSize = Main.Configure.tileSize;
        this.maxScreenColumn = Main.Configure.maxScreenColumn;
        this.maxScreenRow = Main.Configure.maxScreenRow;
        this.gameState = gameState;

//        this.player = player;
//        gameState.getPlayer().setScreen(this);
        this.menuScreen = new MenuScreen(gameState.getMenu());

//        tileSize = originalTileSize * scale;

        screenWidth = tileSize * maxScreenColumn; // 768 pixels

        screenHeight = tileSize * maxScreenRow;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

//    Player player = new Player(this, keyH);

    public void drawGame(Graphics2D g2) {
        this.playerRenderer = new PlayerRenderer(gameState.getPlayer());
        this.playerHealth = new HealthBarRenderer(gameState.getPlayer());
        this.mapRenderer = new MapRenderer(gameState.getCurrentLevel());

        mapRenderer.draw(g2);

        playerRenderer.draw(g2);

        playerHealth.draw(g2);
    }
    public void drawMenu(Graphics2D g2) {
        menuScreen.draw(g2);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (gameState.getCurrentState() == "game") drawGame(g2);
        if (gameState.getCurrentState() == "menu") drawMenu(g2);

        g2.dispose();
    }
}
