package rybina.Model.Tiles;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Water extends Tile {
    public Water() throws IOException {
        this.imgSrc = "/tyles/Water.png";
        this.image = ImageIO.read(getClass().getResourceAsStream(imgSrc));
        this.collision = false;
        this.damaging = 0;
    }
}
