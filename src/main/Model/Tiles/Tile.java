package main.Model.Tiles;

public abstract class Tile {
    String imgSrc;
    boolean collision;
    double damaging;

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

    private int x, y;

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

//    public boolean isDamaging() {
//        return damaging;
//    }

    public double getDamaging() {
        return damaging;
    }

//    public void setDamaging(boolean damaging) {
//        this.damaging = damaging;
//    }

    @Override
    public String toString() {
        return String.format("type =%s; x = %d; y = %d", this.getClass().getName(), x, y);
    }
}
