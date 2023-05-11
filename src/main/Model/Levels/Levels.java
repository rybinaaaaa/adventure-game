package main.Model.Levels;

import main.Model.Entity.Monster.*;
import main.Model.Potion.Potion;
import main.Model.Potion.PotionAttack;
import main.Model.Potion.PotionHealth;

public class Levels {
//    private class Level {
//        public Potion[] potions;
//        public Map map;
//        public Monster[] monsters;
//    }

    private Potion[][] potions = new Potion[][]{
            new Potion[]{
                    new PotionAttack(0, 400),
                    new PotionAttack(420, 200),
                    new PotionAttack(500, 300),
                    new PotionHealth(700, 300)
            }
    };

    private Monster[][] monsters = new Monster[][]{
            new Monster[]{
                    new FlyingEye(200, 7 * 48),
                    new Mushroom(700, 9 * 48 - 16),
                    new Goblin(920, 9 * 48 - 16)
            }
    };
    private Map[] maps = new Map[]{
            new Map(64, 12, "/res/backgrounds/Background1.png", "/res/maps/map1.txt", potions[0], monsters[0])
    };

    private Map currentLevel;


    public Levels() {
        this.currentLevel = maps[0];
    }

    public Map getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int index) {
        this.currentLevel = maps[index];
    }
}
