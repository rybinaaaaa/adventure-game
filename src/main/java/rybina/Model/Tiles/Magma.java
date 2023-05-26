package rybina.Model.Tiles;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Magma extends Tile {
    public Magma() throws IOException {
        this.imgSrc = "/tyles/Magma_Floor.png";
        this.image = ImageIO.read(getClass().getResourceAsStream(imgSrc));
        this.collision = false;
        this.damaging = 0.5;
    }
}
