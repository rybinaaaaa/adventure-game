package rybina.View;

import rybina.Main;
import rybina.Model.Entity.Monster.Monster;
import rybina.Model.Levels.Map;
import rybina.Model.Portal;
import rybina.Model.Potion.Potion;
import rybina.Model.Tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;

public class MapRenderer {
    Map map;
    String backgroundSrc;
    Potion[] potions;
    Monster[] monsters;
    Portal portal;

    public MapRenderer(Map map) {
        this.map = map;
        this.backgroundSrc = map.getBackground();
        this.potions = map.getPotions();
        this.monsters = map.getMonsters();
        this.portal = map.getPortal();
    }

    public void draw(Graphics2D g2) {
        Image image = null;

//         render bg

        try {
            image = ImageIO.read(getClass().getResourceAsStream(backgroundSrc));
            g2.drawImage(image, AffineTransform.getRotateInstance(0), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        render map

        for (Tile[] row : map.getMapMatrix()) {
            for (Tile tile : row) {
                if (tile.getImgSrc() == null) continue;
                try {
                    image = ImageIO.read(getClass().getResourceAsStream(tile.getImgSrc()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                g2.drawImage(image, tile.getX() - map.getOffsetX(), tile.getY(), Main.Configure.tileSize, Main.Configure.tileSize, null);
            }
        }

//        render potions

        for (Potion potion : potions) {
            if (potion == null) continue;
            try {
                image = ImageIO.read(getClass().getResourceAsStream(potion.getImgSrc()));
                g2.drawImage(image, potion.getX() - map.getOffsetX(), potion.getY(), 32, 32, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//       render monsters

        for (Monster monster : monsters) {
            if (monster.isKilled()) continue;
            g2.drawImage(monster.getImage(), monster.getX() - map.getOffsetX(), monster.getY(), monster.getWidth(), monster.getHeight(), null);
        }

        g2.drawImage(portal.getImage(), portal.getX() - map.getOffsetX(), portal.getY(), Main.Configure.tileSize * 2, Main.Configure.tileSize * 2, null);
    }
}
