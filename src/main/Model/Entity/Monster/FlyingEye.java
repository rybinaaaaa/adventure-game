package main.Model.Entity.Monster;

public class FlyingEye extends Monster {
    private NodeImage[] flighting = loadAnimation("/res/monsters/FlyingEye/Flight.png", 48, 40, 1, 8, (150 - 48) / 2, (150 - 40) / 2);
    public FlyingEye(int x, int y, int damage) {
        super(x, y, damage);
        setDefaultValues();
    }

    public void setDefaultValues() {
        super.setDefaultValues();
        animationPresent = flighting;
        image = animationPresent[0];
//        distanceRange = 200;
    }
}
