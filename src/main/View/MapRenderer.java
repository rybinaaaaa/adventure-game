package main.View;

import main.Model.Map;
import main.Model.Tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;

public class MapRenderer {
    Map map;
    String backgroundSrc;

    public MapRenderer(Map map) {
        this.map = map;
        this.backgroundSrc = map.getBackground();
    }

    public void draw(Graphics2D g2) {
        Image image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(backgroundSrc));
            g2.drawImage(image, AffineTransform.getRotateInstance(0), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Tile[] row : map.getMapMatrix()) {
            for (Tile tile : row) {
                if (tile.getImgSrc() == null) continue;
                try {
                    image = ImageIO.read(getClass().getResourceAsStream(tile.getImgSrc()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                g2.drawImage(image, tile.getX() - map.getOffsetX(), tile.getY(), 48, 48, null);
            }
        }
    }
}
