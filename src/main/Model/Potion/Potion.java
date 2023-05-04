package main.Model.Potion;

public abstract class Potion {
    int x;
    int y;

    public String getType() {
        return type;
    }

    String imgSrc;
    String type;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
    };
}
