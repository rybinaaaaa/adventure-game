package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //    SCREEEN SETTINGS
    final int originalTileSize = 16;
    //    16 x 16 tile (клетка)
    final int scale = 3;

    final public int tileSize = originalTileSize * scale;

    final public int maxScreenColumn = 16;
    final public int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

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

            long currentTime = System.nanoTime();

//            1 update (about position of character)
            update();
//            2 drow
            repaint();

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
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();
    }
}
