package tile.texture;

import tile.Tile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class RockyRoad extends Tile {
    public RockyRoad() throws IOException {
        image = ImageIO.read(getClass().getResourceAsStream("/tyles/Rocky_Road.png"));
    }
}
