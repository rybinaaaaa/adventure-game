package tile;

import main.GamePanel;
import tile.texture.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    Tile magma;
    Tile rockyRoad;
    Tile venelikePattern;
    Tile greenWallRock;

    int mapTileMatrix[][];
//
//    enum TileCase {
//        MAGMA(0),
//        ROCKYROAD(1),
//        VINILIKEPATTERN(2);
//
//        private final int value;
//
//        TileCase(int value) {
//            this.value = value;
//        }
//    }

    public TileManager(GamePanel gp) {
        this.gp = gp;

//        tile = new Tile[gp.maxScreenRow * gp.maxScreenColumn];
        mapTileMatrix = new int[gp.maxScreenRow][gp.maxScreenColumn];

        getTileImage();
        loadMap();
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map1.txt");
            System.out.println(is);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int i = 0; i < gp.maxScreenRow; i++) {
                String row = br.readLine();
                String[] numbers = row.split(" ");
                for (int j = 0; j < gp.maxScreenColumn; j++) {
                    mapTileMatrix[i][j] = Integer.parseInt(numbers[j]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTileImage() {
        try {
            magma = new Magma();
            rockyRoad = new RockyRoad();
            venelikePattern = new VinelikePattern();
            greenWallRock = new GreenWallRock();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        Image block = null;
//        TileCase option = new TileCase();
        for (int i = 0; i < gp.maxScreenRow; i++) {
            for (int j = 0; j < gp.maxScreenColumn; j++) {
                switch (mapTileMatrix[i][j]) {
                    case 0:
                        block = greenWallRock.image;
                        break;
                    case 1:
                        block = rockyRoad.image;
                        break;
                    case 2:
                        block = magma.image;
                        break;
                    case 3:
                        block = venelikePattern.image;
                        break;
                }
                g2.drawImage(block, 48 * j, 48 * i, gp.tileSize, gp.tileSize, null);
            }
        }
    }
}
