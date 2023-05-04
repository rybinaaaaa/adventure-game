package main.Model.Levels;

import main.Model.Potion.Potion;
import main.Model.Potion.PotionAttack;
import main.Model.Potion.PotionHealth;

public class Levels {

    private Potion[][] potions= new Potion[][]{
            new Potion[] {
                    new PotionHealth(300, 400),
                    new PotionAttack(20, 120),
                    new PotionAttack(0, 300)
            },
            new Potion[] {
                    new PotionAttack(),
                    new PotionAttack(),
                    new PotionAttack()
            }
    };
    private Map[] maps = new Map[] {
            new Map(32, 12, "/res/backgrounds/Background1.png", "/res/maps/map1.txt", potions[0])
    };

    private Map currentLevel;


    public Levels() {
        this.maps = maps;
        this.currentLevel = maps[0];
    };

    public Map getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int index) {
        this.currentLevel = maps[index];
    }
}
