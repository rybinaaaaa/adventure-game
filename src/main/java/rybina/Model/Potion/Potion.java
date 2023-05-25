package rybina.Model.Potion;

import java.io.Serializable;

public abstract class Potion implements Serializable {
    int x;
    int y;

    public String getType() {
        return type;
    }

    String imgSrc;
    String type;

    public Potion(int x, int y, String imgSrc, String type) {
        this.x = x;
        this.y = y;
        this.imgSrc = imgSrc;
        this.type = type;
    }

    private boolean visible = true;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getImgSrc() {
        return imgSrc;
    }
}
