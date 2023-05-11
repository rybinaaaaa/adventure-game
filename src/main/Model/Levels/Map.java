package main.Model.Levels;

import main.Main;
import main.Model.Entity.Monster.Monster;
import main.Model.Potion.Potion;
import main.Model.Tiles.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Map {
    private Monster[] monsters;
    private Tile mapMatrix[][];
    int maxScreenColumn = Main.Configure.maxScreenColumn;
    int maxScreenRow = Main.Configure.maxScreenRow;
    int totalScreenColumn, totalScreenRow;

    private Potion[] potions;
//    Tile[][] map;

    private String backgroundSrc, levelSrc;
    int offsetX = 0;
    int offsetY = 0;
    int maxOffsetX = 0;
    int maxOffsetY = 0;

    public Potion[] getPotions() {
        return potions;
    }

    public void deletePotion(Potion potion) {
        for (int i = 0; i < potions.length; i++) {
            if (potions[i] == potion) potions[i] = null;
        }
    }


    public Potion getPotion(int x, int y) {
        for (Potion potion : potions) {
            if (potion == null) continue;
            if (potion.getX() - 32 <= (x + offsetX) && potion.getX() + 32 >= (x + offsetX) && potion.getY() + 32 >= (y + offsetY) && potion.getY() - 32 <= (y + offsetY)) {
                return potion;
            }
        }
        return null;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX += offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }
    public Monster[] getMonsters() {
        return monsters;
    }
    public Monster getMonster(int x, int y) {
        for (Monster monster : monsters) {
            if (monster == null || monster.isKilled()) continue;
            if (monster.getWidth() < 0) {
                if (monster.getX() + monster.getWidth() <= (x + offsetX) && monster.getX() + monster.getWidth() / 7 >= (x + offsetX) && monster.getY() + 48 >= (y + offsetY) && monster.getY() <= (y + offsetY)) {
                    return monster;
                }
            } else {
                if (monster.getX() + monster.getWidth() >= (x + offsetX) && monster.getX() + monster.getWidth() / 7 <= (x + offsetX) && monster.getY() + 48 >= (y + offsetY) && monster.getY() <= (y + offsetY)) {
                    return monster;
                }
            }
        }
        return null;
    }

    public ArrayList<Monster> getMonsterOnRange(int range, int initialPointX, int initialPointY) {
        ArrayList<Monster> monsters = new ArrayList<>();
        int currentPoint = initialPointX;
        Monster currentMonster;
        Monster lastMonster = null;
        while (initialPointX + range != currentPoint) {
            currentPoint += (range) / Math.abs(range);
            currentMonster = getMonster(currentPoint, initialPointY);
            if (currentMonster != null && currentMonster != lastMonster) {
                monsters.add(currentMonster);
                lastMonster = currentMonster;
            }
        }
        return monsters;
    }
    private Tile getTileOption(int option) {
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


    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public Tile getTile(int x, int y) {
        if ((x + offsetX ) / 48 >= totalScreenColumn || ( y + offsetY) / 48 >= totalScreenRow) {
            return new Air();
        }
        return mapMatrix[(y + offsetY) / 48][(x + offsetX) / 48];
    }

    public String getBackground() {
        return backgroundSrc;
    }

//    public void setBackground(int index) {
//        this.background = levels[index - 1].getBackgroundSrc();
//    }

    public int getMaxOffsetX() {
        return maxOffsetX;
    }

    public int getMaxOffsetY() {
        return maxOffsetY;
    }

    public Map(int totalScreenColumn, int totalScreenRow, String backgroundSrc, String levelSrc, Potion[] potions, Monster[] monsters) {
        this.totalScreenColumn = totalScreenColumn;
        this.totalScreenRow = totalScreenRow;
        this.mapMatrix = new Tile[totalScreenRow][totalScreenColumn];
        this.maxOffsetX = (totalScreenColumn - maxScreenColumn) * 48;
        this.maxOffsetY = (totalScreenRow - maxScreenRow) * 48;
        this.backgroundSrc = backgroundSrc;
        this.levelSrc = levelSrc;
        loadMap(levelSrc);
        this.potions = potions;
        this.monsters = monsters;
//        setMapMatrix(1);
//        setBackground(1);
    }
}
