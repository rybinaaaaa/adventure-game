package rybina.Model.Levels;

import rybina.Main;
import rybina.Model.Entity.Monster.FlyingEye;
import rybina.Model.Entity.Monster.Goblin;
import rybina.Model.Entity.Monster.Monster;
import rybina.Model.Entity.Monster.Mushroom;
import rybina.Model.Portal;
import rybina.Model.Potion.Potion;
import rybina.Model.Potion.PotionAttack;
import rybina.Model.Potion.PotionHealth;

public class Levels {
    int currentLevelNumber = 0;

    // ONE LEVEL INFO
    private class Level {
        int totalScreenColumn, totalScreenRow;
        private Potion[] potions;
        private Map map;
        private Monster[] monsters;

        private Portal portal;

        private String background, levelSrc;

        /**
         * Constructs a new Level object.
         *
         * @param totalScreenColumn The total number of screen columns.
         * @param totalScreenRow    The total number of screen rows.
         * @param potions           The potions in the level.
         * @param monsters          The monsters in the level.
         * @param background        The background image for the level.
         * @param levelSrc          The level source.
         * @param portal            The portal in the level.
         */
        public Level(int totalScreenColumn, int totalScreenRow, Potion[] potions, Monster[] monsters, String background, String levelSrc, Portal portal) {
            this.totalScreenColumn = totalScreenColumn;
            this.totalScreenRow = totalScreenRow;
            this.potions = potions;
            this.monsters = monsters;
            this.background = background;
            this.levelSrc = levelSrc;
            this.portal = portal;
            this.map = new Map(totalScreenColumn, totalScreenRow, background, levelSrc, potions, monsters, portal);
        }

        /**
         * Gets the potions in the level.
         *
         * @return The potions.
         */
        public Potion[] getPotions() {
            return potions;
        }

        /**
         * Gets the map of the level.
         *
         * @return The map.
         */
        public Map getMap() {
            return map;
        }

        /**
         * Gets the monsters in the level.
         *
         * @return The monsters.
         */
        public Monster[] getMonsters() {
            return monsters;
        }

        /**
         * Sets the potions in the level.
         *
         * @param potions The potions to set.
         */
        public void setPotions(Potion[] potions) {
            this.potions = potions;
        }

        /**
         * Sets the monsters in the level.
         *
         * @param monsters The monsters to set.
         */
        public void setMonsters(Monster[] monsters) {
            this.monsters = monsters;
        }

        /**
         * Gets the portal in the level.
         *
         * @return The portal.
         */
        public Portal getPortal() {
            return portal;
        }
    }


    public Level[] levels = new Level[]{
            new Level(
                    64,
                    12,
                    new Potion[]{
                            new PotionAttack(200, 7 * Main.Configure.tileSize),
                            new PotionAttack(700, 9 * Main.Configure.tileSize - 16),
                            new PotionAttack(920, 9 * Main.Configure.tileSize - 16),
                    },
                    new Monster[]{
                    },
                    "/backgrounds/Background1.png",
                    "/maps/map2.txt",
                    new Portal(61 * Main.Configure.tileSize, 7 * Main.Configure.tileSize)
            ),
            new Level(
                    64,
                    12,
                    new Potion[]{
                            new PotionAttack(0, 400),
                            new PotionAttack(420, 200),
                            new PotionAttack(500, 300),
                            new PotionHealth(700, 300)
                    },
                    new Monster[]{
                            new FlyingEye(200, 7 * Main.Configure.tileSize),
                            new Mushroom(700, 9 * Main.Configure.tileSize - 16),
                            new Goblin(920, 9 * Main.Configure.tileSize - 16)
                    },
                    "/backgrounds/Background1.png",
                    "/maps/map1.txt",
                    new Portal(61 * Main.Configure.tileSize, 7 * Main.Configure.tileSize)
            )
    };

    private Map currentLevel;

    /**
     * initialize axtual level from all levels
     */
    public Levels() {
        this.currentLevel = levels[currentLevelNumber].getMap();
    }

    /**
     * Gets the current level number.
     *
     * @return The current level number.
     */
    public int getCurrentLevelNumber() {
        return currentLevelNumber;
    }

    /**
     * Sets the current potions in the level.
     *
     * @param potions The potions to set.
     */
    public void setCurrentPotions(Potion[] potions) {
        currentLevel.setPotions(potions);
    }
    /**
     * Sets the current monsters in the level.
     *
     * @param monsters The monsters to set.
     */
    public void setCurrentMonsters(Monster[] monsters) {
        currentLevel.setMonsters(monsters);
    }

    /**
     * Gets the current monsters in the level.
     *
     * @return The current monsters.
     */
    public Monster[] getCurrentMonsters() {
        return currentLevel.getMonsters();
    }

    /**
     * Gets the current level map.
     *
     * @return The current level map.
     */
    public Map getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Sets the current level to the specified index.
     *
     * @param index The index of the level.
     */
    public void setCurrentLevel(int index) {
        this.currentLevelNumber = index;
        this.currentLevel = levels[index].getMap();
    }

    /**
     * Checks if the game has finished all levels.
     *
     * @return true if all levels are finished, false otherwise.
     */
    public boolean isFinished() {
        return levels[levels.length - 1].getPortal().isComplete();
    }

    /**
     * Sets the current level to the next level.
     */
    public void setToNextLevel() {
        setCurrentLevel((currentLevelNumber + 1) % levels.length);
    }
}