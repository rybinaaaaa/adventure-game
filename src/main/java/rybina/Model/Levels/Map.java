package rybina.Model.Levels;

import rybina.Main;
import rybina.Model.Entity.Monster.Monster;
import rybina.Model.Portal;
import rybina.Model.Potion.Potion;
import rybina.Model.Tiles.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    private Monster[] monsters;
    private Tile mapMatrix[][];
    int maxScreenColumn = Main.Configure.maxScreenColumn;
    int maxScreenRow = Main.Configure.maxScreenRow;
    int totalScreenColumn, totalScreenRow;

    private Potion[] potions;
//    Tile[][] map;

    private Portal portal;
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
                if (monster.getX() + monster.getWidth() <= (x + offsetX) && monster.getX() + monster.getWidth() / 7 >= (x + offsetX) && monster.getY() + Main.Configure.tileSize >= (y + offsetY) && monster.getY() <= (y + offsetY)) {
                    return monster;
                }
            } else {
                if (monster.getX() + monster.getWidth() >= (x + offsetX) && monster.getX() + monster.getWidth() / 7 <= (x + offsetX) && monster.getY() + Main.Configure.tileSize >= (y + offsetY) && monster.getY() <= (y + offsetY)) {
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
                    mapMatrix[i][j].setX(Main.Configure.tileSize * j);
                    mapMatrix[i][j].setY(Main.Configure.tileSize * i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return maxScreenColumn * Main.Configure.tileSize;
    }

    public int getHeight() {
        return maxScreenRow * Main.Configure.tileSize;
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
        if ((x + offsetX ) / Main.Configure.tileSize >= totalScreenColumn || ( y + offsetY) / Main.Configure.tileSize >= totalScreenRow) {
            return new Air();
        }
        return mapMatrix[(y + offsetY) / Main.Configure.tileSize][(x + offsetX) / Main.Configure.tileSize];
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

    public Map(int totalScreenColumn, int totalScreenRow, String backgroundSrc, String levelSrc, Potion[] potions, Monster[] monsters, Portal portal) {
        this.totalScreenColumn = totalScreenColumn;
        this.totalScreenRow = totalScreenRow;
        this.mapMatrix = new Tile[totalScreenRow][totalScreenColumn];
        this.maxOffsetX = (totalScreenColumn - maxScreenColumn) * Main.Configure.tileSize;
        this.maxOffsetY = (totalScreenRow - maxScreenRow) * Main.Configure.tileSize;
        this.backgroundSrc = backgroundSrc;
        this.levelSrc = levelSrc;
        this.potions = potions;
        this.monsters = monsters;
        this.portal= portal;
        loadMap(levelSrc);
    }

    public void setMonsters(Monster[] monsters) {
        this.monsters = monsters;
    }

    public void setPotions(Potion[] potions) {
        this.potions = potions;
    }

    public Portal getPortal() {
        return portal;
    }

    @Override
    public String toString() {
        return "Map{" +
                ", levelSrc='" + levelSrc + '\'' +
                '}';
    }
}
