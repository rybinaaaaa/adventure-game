package tile.texture;

import tile.Tile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class GreenWallRock extends Tile {
    public GreenWallRock() throws IOException {
        image = ImageIO.read(getClass().getResourceAsStream("/tyles/Green_Wall_Rock.png"));
    }
}
