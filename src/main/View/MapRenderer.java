package main.View;

import main.Model.Levels.Map;
import main.Model.Potion.Potion;
import main.Model.Tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;

public class MapRenderer {
    Map map;
    String backgroundSrc;
    Potion[] potions;

    public MapRenderer(Map map) {
        this.map = map;
        this.backgroundSrc = map.getBackground();
        this.potions = map.getPotions();
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

        for (Potion potion: potions) {
            if (potion == null) continue;
            try {
                image = ImageIO.read(getClass().getResourceAsStream(potion.getImgSrc()));
                g2.drawImage(image, potion.getX(), potion.getY(), 32, 32, null);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
