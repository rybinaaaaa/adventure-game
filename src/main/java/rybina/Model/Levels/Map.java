package rybina.Model.Levels;

import rybina.Main;
import rybina.Model.Entity.Monster.Monster;
import rybina.Model.Portal;
import rybina.Model.Potion.Potion;
import rybina.Model.Tiles.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Represents the map in the game.
 */
public class Map {
    private Monster[] monsters;
    private Tile mapMatrix[][];
    int maxScreenColumn = Main.Configure.maxScreenColumn;
    int maxScreenRow = Main.Configure.maxScreenRow;
    int totalScreenColumn, totalScreenRow;
    private Potion[] potions;
    private Portal portal;
    private String backgroundSrc, levelSrc;
    int offsetX = 0;
    int offsetY = 0;
    int maxOffsetX = 0;
    int maxOffsetY = 0;

    /**
     * Gets the potions in the map.
     *
     * @return The potions.
     */
    public Potion[] getPotions() {
        return potions;
    }

    /**
     * Deletes a potion from the map.
     *
     * @param potion The potion to delete.
     */
    public void deletePotion(Potion potion) {
        for (int i = 0; i < potions.length; i++) {
            if (potions[i] == potion) potions[i] = null;
        }
    }

    /**
     * Gets the potion at the specified coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The potion at the coordinates, or null if not found.
     */
    public Potion getPotion(int x, int y) {
        for (Potion potion : potions) {
            if (potion == null) continue;
            if (potion.getX() - 32 <= (x + offsetX) && potion.getX() + 32 >= (x + offsetX) && potion.getY() + 32 >= (y + offsetY) && potion.getY() - 32 <= (y + offsetY)) {
                return potion;
            }
        }
        return null;
    }

    /**
     * Sets the x-offset of the map.
     *
     * @param offsetX The x-offset to set.
     */
    public void setOffsetX(int offsetX) {
        this.offsetX += offsetX;
    }


    /**
     * Sets the y-offset of the map.
     *
     * @param offsetY The y-offset to set.
     */
    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    /**
     * Gets the monsters in the map.
     *
     * @return The monsters.
     */
    public Monster[] getMonsters() {
        return monsters;
    }

    /**
     * Gets the monster at the specified coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The monster at the coordinates, or null if not found.
     */
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

    /**
     * Gets the monsters within a certain range of coordinates.
     *
     * @param range         The range to check.
     * @param initialPointX The initial x-coordinate.
     * @param initialPointY The initial y-coordinate.
     * @return The monsters within the range.
     */
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

    private Tile getTileOption(int option) throws IOException {
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

    /**
     * Loads the map from the specified source.
     *
     * @param src The source file path.
     */
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

    /**
     * Gets the width of the map.
     *
     * @return The width of the map.
     */
    public int getWidth() {
        return maxScreenColumn * Main.Configure.tileSize;
    }

    /**
     * Gets the height of the map.
     *
     * @return The height of the map.
     */
    public int getHeight() {
        return maxScreenRow * Main.Configure.tileSize;
    }

    /**
     * Gets the matrix representation of the map.
     *
     * @return The map matrix.
     */
    public Tile[][] getMapMatrix() {
        return mapMatrix;
    }

    /**
     * Gets the x-offset of the map.
     *
     * @return The x-offset of the map.
     */
    public int getOffsetX() {
        return offsetX;
    }

    /**
     * Gets the y-offset of the map.
     *
     * @return The y-offset of the map.
     */
    public int getOffsetY() {
        return offsetY;
    }

    /**
     * Gets the tile at the specified coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The tile at the coordinates.
     */
    public Tile getTile(int x, int y) {
        if ((x + offsetX) / Main.Configure.tileSize >= totalScreenColumn || (y + offsetY) / Main.Configure.tileSize >= totalScreenRow) {
            return new Air();
        }
        return mapMatrix[(y + offsetY) / Main.Configure.tileSize][(x + offsetX) / Main.Configure.tileSize];
    }

    /**
     * Gets the background source of the map.
     *
     * @return The background source.
     */
    public String getBackground() {
        return backgroundSrc;
    }

    /**
     * Gets the maximum x-offset of the map.
     *
     * @return The maximum x-offset.
     */
    public int getMaxOffsetX() {
        return maxOffsetX;
    }

    /**
     * Gets the maximum y-offset of the map.
     *
     * @return The maximum y-offset.
     */
    public int getMaxOffsetY() {
        return maxOffsetY;
    }

    /**
     * Constructs a new Map instance.
     *
     * @param totalScreenColumn The total number of columns in the map.
     * @param totalScreenRow    The total number of rows in the map.
     * @param backgroundSrc     The background image source.
     * @param levelSrc          The level source file path.
     * @param potions           The array of potions in the map.
     * @param monsters          The array of monsters in the map.
     * @param portal            The portal object in the map
     */
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
        this.portal = portal;
        loadMap(levelSrc);
    }
    /**
     * Sets the monsters in the map.
     *
     * @param monsters The monsters to set.
     */
    public void setMonsters(Monster[] monsters) {
        this.monsters = monsters;
    }

    /**
     * Sets the potions in the map.
     *
     * @param potions The potions to set.
     */
    public void setPotions(Potion[] potions) {
        this.potions = potions;
    }

    /**
     * Gets the portal in the map.
     *
     * @return The portal object.
     */
    public Portal getPortal() {
        return portal;
    }

    public void setPortal(Portal portal) {
        this.portal = portal;
    }
}
