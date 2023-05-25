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

    //     ONE LEVEL INFO
    private class Level {
        int totalScreenColumn, totalScreenRow;
        private Potion[] potions;
        private Map map;
        private Monster[] monsters;

        private Portal portal;

        private String background, levelSrc;

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

        public Potion[] getPotions() {
            return potions;
        }

        public Map getMap() {
            return map;
        }

        public Monster[] getMonsters() {
            return monsters;
        }

        public void setPotions(Potion[] potions) {
            this.potions = potions;
        }

        public void setMonsters(Monster[] monsters) {
            this.monsters = monsters;
        }

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


    public Levels() {
        this.currentLevel = levels[currentLevelNumber].getMap();
    }

    public int getCurrentLevelNumber() {
        return currentLevelNumber;
    }

    public void setCurrentPotions(Potion[] potions) {
        currentLevel.setPotions(potions);
    }

    public void setCurrentMonsters(Monster[] monsters) {
        currentLevel.setMonsters(monsters);
    }

    public Monster[] getCurrentMonsters() {
        return currentLevel.getMonsters();
    }

    public Map getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int index) {
        this.currentLevelNumber = index;
        this.currentLevel = levels[index].getMap();
    }

    public boolean isFinished() {
        return levels[levels.length - 1].getPortal().isComplete();
    }

    public void setToNextLevel() {
        setCurrentLevel((currentLevelNumber + 1) % levels.length);
    }
}
