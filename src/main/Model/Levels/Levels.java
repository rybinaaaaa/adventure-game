package main.Model.Levels;

import main.Model.Entity.Monster.FlyingEye;
import main.Model.Entity.Monster.Goblin;
import main.Model.Entity.Monster.Monster;
import main.Model.Entity.Monster.Mushroom;
import main.Model.Potion.Potion;
import main.Model.Potion.PotionAttack;
import main.Model.Potion.PotionHealth;

public class Levels {
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

//    private Potion[][] potions = new Potion[][]{
//            new Potion[]{
//                    new PotionAttack(0, 400),
//                    new PotionAttack(420, 200),
//                    new PotionAttack(500, 300),
//                    new PotionHealth(700, 300)
//            }
//    };
//
//    private Monster[][] monsters = new Monster[][]{
//            new Monster[]{
//                    new FlyingEye(200, 7 * 48),
//                    new Mushroom(700, 9 * 48 - 16),
//                    new Goblin(920, 9 * 48 - 16)
//            }
//    };
//    private Map[] maps = new Map[]{
//            new Map(64, 12, , potions[0], monsters[0])
//    };

    private Map currentLevel;


    public Levels() {
        this.currentLevel = levels[0].getMap();
    }

    public Map getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int index) {
        this.currentLevel = levels[index].getMap();
    }
}
