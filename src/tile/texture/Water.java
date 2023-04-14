package tile.texture;

import tile.Tile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Water extends Tile {
    public Water() throws IOException {
        image = ImageIO.read(getClass().getResourceAsStream("/tyles/Rocky_Road.png"));
    }
}
