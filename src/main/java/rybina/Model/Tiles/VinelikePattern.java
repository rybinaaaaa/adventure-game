package rybina.Model.Tiles;

import javax.imageio.ImageIO;
import java.io.IOException;

public class VinelikePattern extends Tile {
    public VinelikePattern() throws IOException {
        this.imgSrc = "/tyles/Vinelike_Pattern.png";
        this.image = ImageIO.read(getClass().getResourceAsStream(imgSrc));
        this.collision = true;
        this.damaging = 0;
    }
}