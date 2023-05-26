package rybina.Model.Tiles;

import javax.imageio.ImageIO;
import java.io.IOException;

public class GreenWallRock extends Tile {
    public GreenWallRock() throws IOException {
        this.imgSrc = "/tyles/Green_Wall_Rock.png";
        this.image = ImageIO.read(getClass().getResourceAsStream(imgSrc));
        this.collision = true;
        this.damaging = 0;
    }
}