package main.Model.Helpers;

import main.Model.Entity.Monster.Monster;
import main.Model.Levels.Levels;
import main.Model.Potion.Potion;

import java.io.Serializable;

public class LevelInfo implements Serializable {
    public int offsetX, offsetY;
    public int levelNumber;

    public LevelInfo(Levels levels) {
        this.offsetX = levels.getCurrentLevel().getOffsetX();
        this.offsetY = levels.getCurrentLevel().getOffsetY();
        this.levelNumber =  levels.getCurrentLevelNumber();;
    }
}