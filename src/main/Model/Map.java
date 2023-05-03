package main.Model;

import main.Model.Tiles.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Map {
    private Tile mapMatrix[][];
    Tile[][] map;

    private String background;
    int offsetX = 0;
    int offsetY = 0;
    int maxOffsetX = 0;
    int maxOffsetY = 0;

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
//        setMap();
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
//        setMap();
    }

    private static class Level {
        String mapSrc;
        String bacgroundSrc;

        public String getMapSrc() {
            return mapSrc;
        }

        public String getBackgroundSrc() {
            return bacgroundSrc;
        }

        public Level(String mapSrc, String bacgroundSrc) {
            this.mapSrc = mapSrc;
            this.bacgroundSrc = bacgroundSrc;
        }
    }

    Level[] levels = new Level[]{
            new Level("/res/maps/map1.txt", "/res/backgrounds/Background1.png")
    };

    int maxScreenRow, maxScreenColumn, totalScreenColumn, totalScreenRow;

    public Tile getTileOption(int option) {
        switch (option) {
            case 0:
                return new Air();
            case 1:
                return new RockyRoad();
            case 2:
                return new Magma();
            case 3:
                return new VinelikePattern();
            default:
                return new RockyRoad();
        }
    }

    ;


    private void loadMap(String src) {
        try {
            InputStream is = getClass().getResourceAsStream(src);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int option;

            for (int i = 0; i < totalScreenRow; i++) {

                String row = br.readLine();
                String[] numbers = row.split(" ");
                for (int j = 0; j < totalScreenColumn; j++) {
                    option = Integer.parseInt(numbers[j]);
                    mapMatrix[i][j] = getTileOption(option);
                    mapMatrix[i][j].setX(48 * j);
                    mapMatrix[i][j].setY(48 * i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return maxScreenColumn * 48;
    }

    public int getHeight() {
        return maxScreenRow * 48;
    }

    public Tile[][] getMapMatrix() {
        return mapMatrix;
    }

//    public Tile[][] getMap() {
//        return map;
//    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

//    public void setMap() {
//        for (int i = 0; i < maxScreenRow; i++) {
//            for (int j = 0; j < maxScreenColumn; j++) {
//                map[i][j] = mapMatrix[i + offsetY][j + offsetX];
//                if (map[i][j] == null) continue;
//                map[i][j].setX(48 * j);
//                map[i][j].setY(48 * i);
//            }
//        }
//    }

    public void setMapMatrix(int index) {
        try {
            loadMap(levels[index - 1].getMapSrc());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return getMapMatrix();
    }

    public Tile getTile(int x, int y) {
        return mapMatrix[(y + offsetY) / 48][(x + offsetX) / 48];
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(int index) {
        this.background = levels[index - 1].getBackgroundSrc();
    }

    public int getMaxOffsetX() {
        return maxOffsetX;
    }

    public int getMaxOffsetY() {
        return maxOffsetY;
    }

    public Map(int maxScreenRow, int maxScreenColumn, int totalScreenColumn, int totalScreenRow) {
        this.maxScreenColumn = maxScreenColumn;
        this.maxScreenRow = maxScreenRow;
        this.totalScreenColumn = totalScreenColumn;
        this.totalScreenRow = totalScreenRow;
        this.mapMatrix = new Tile[totalScreenRow][totalScreenColumn];
        this.maxOffsetX = (totalScreenColumn - maxScreenColumn) * 48;
        this.maxOffsetY = (totalScreenRow - maxScreenRow) * 48;
        setMapMatrix(1);
        setBackground(1);
    }
}
