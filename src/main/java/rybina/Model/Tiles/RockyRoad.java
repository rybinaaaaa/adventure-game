package rybina.Model.Tiles;

import javax.imageio.ImageIO;
import java.io.IOException;

public class RockyRoad extends Tile {
    public RockyRoad() throws IOException {
        this.imgSrc = "/tyles/Rocky_Road.png";
        this.image = ImageIO.read(getClass().getResourceAsStream(imgSrc));
        this.collision = true;
        this.damaging = 0;
    }
}