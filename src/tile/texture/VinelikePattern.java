package tile.texture;

import tile.Tile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class VinelikePattern extends Tile {
    public VinelikePattern() throws IOException {
        image = ImageIO.read(getClass().getResourceAsStream("/tyles/Vinelike_Pattern.png"));
    }
}
