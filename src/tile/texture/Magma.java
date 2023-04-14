package tile.texture;

import tile.Tile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Magma extends Tile {
    public Magma() throws IOException {
        image = ImageIO.read(getClass().getResourceAsStream("/tyles/Magma_Floor.png"));
    }
}
