package main.View;

import main.Model.Map;
import main.Model.entity.Player;
import main.Controller.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {
    //    SCREEEN SETTINGS
    int originalTileSize;
    //    16 x 16 tile (клетка)
    int scale;

    public int tileSize;

    public int maxScreenColumn;
    public int maxScreenRow;
    int screenWidth; // 768 pixels
    int screenHeight; // 576 pixels
    Player player;
    PlayerRenderer playerRenderer;

    MapRenderer mapRenderer;
    HealthBarRenderer playerHealth;


    public Screen(int originalTileSize, int scale, int maxScreenColumn, int maxScreenRow, Player player, KeyHandler keyH, Map map) {

        this.addKeyListener(keyH);

        this.originalTileSize = originalTileSize;
        this.scale = scale;
        this.maxScreenColumn = maxScreenColumn;
        this.maxScreenRow = maxScreenRow;

//        this.player = player;
        player.setScreen(this);
        playerRenderer = new PlayerRenderer(player);
        this.playerHealth = new HealthBarRenderer(player);

//        ????
        this.mapRenderer = new MapRenderer(map);

        tileSize = originalTileSize * scale;
        screenWidth = tileSize * maxScreenColumn; // 768 pixels
        screenHeight = tileSize * maxScreenRow;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

//    Player player = new Player(this, keyH);

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

//        tileM.draw(g2);
        mapRenderer.draw(g2);

        playerRenderer.draw(g2);

        playerHealth.draw(g2);

        g2.dispose();
    }
}
