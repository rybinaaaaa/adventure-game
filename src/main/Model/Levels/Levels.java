package main.Model.Levels;

import main.Model.Entity.Monster.FlyingEye;
import main.Model.Entity.Monster.Goblin;
import main.Model.Entity.Monster.Monster;
import main.Model.Entity.Monster.Mushroom;
import main.Model.Potion.Potion;
import main.Model.Potion.PotionAttack;
import main.Model.Potion.PotionHealth;

import java.io.Serializable;

public class Levels {
    int currentLevelNumber = 0;
    private class Level {
        int totalScreenColumn, totalScreenRow;
        private Potion[] potions;
        private Map map;
        private Monster[] monsters;

        private String background, levelSrc;

        public Level(int totalScreenColumn, int totalScreenRow, Potion[] potions, Monster[] monsters, String background, String levelSrc) {
            this.totalScreenColumn = totalScreenColumn;
            this.totalScreenRow = totalScreenRow;
            this.potions = potions;
            this.monsters = monsters;
            this.background = background;
            this.levelSrc = levelSrc;
            this.map = new Map(totalScreenColumn, totalScreenRow, background, levelSrc, potions, monsters);
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
    }

    public Level[] levels = new Level[]{
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
                            new FlyingEye(200, 7 * 48),
                            new Mushroom(700, 9 * 48 - 16),
                            new Goblin(920, 9 * 48 - 16)
                    },
                    "/res/backgrounds/Background1.png",
                    "/res/maps/map1.txt")
    };

    private Map currentLevel;


    public Levels() {
        this.currentLevel = levels[currentLevelNumber].getMap();
    }

    public int getCurrentLevelNumber() {
        return currentLevelNumber;
    }

    public Map getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int index) {
        this.currentLevelNumber = index;
        this.currentLevel = levels[index].getMap();
    }
}
