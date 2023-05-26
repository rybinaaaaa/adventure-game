package rybina.View;

import rybina.Main;
import rybina.Model.Entity.Monster.Monster;
import rybina.Model.Levels.Map;
import rybina.Model.Portal;
import rybina.Model.Potion.Potion;
import rybina.Model.Potion.PotionAttack;
import rybina.Model.Potion.PotionHealth;
import rybina.Model.Potion.PotionSpeed;
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

    Image potionAttack;
    Image potionSpeed;
    Image potionHealth;
    Image background;


    public MapRenderer(Map map) {
        this.map = map;
        this.backgroundSrc = map.getBackground();
        this.potions = map.getPotions();
        this.monsters = map.getMonsters();
        this.portal = map.getPortal();
        try {
            this.background = ImageIO.read(getClass().getResourceAsStream(backgroundSrc));
            this.potionAttack = ImageIO.read(getClass().getResourceAsStream(PotionAttack.getImgSrc()));
            this.potionSpeed = ImageIO.read(getClass().getResourceAsStream(PotionSpeed.getImgSrc()));
            this.potionHealth = ImageIO.read(getClass().getResourceAsStream(PotionHealth.getImgSrc()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        Image image = null;

//         render bg
        g2.drawImage(background, AffineTransform.getRotateInstance(0), null);

//        render map

        for (Tile[] row : map.getMapMatrix()) {
            for (Tile tile : row) {
                if (tile.getImgSrc() == null) continue;
                image = tile.getImage();
                g2.drawImage(image, tile.getX() - map.getOffsetX(), tile.getY(), Main.Configure.tileSize, Main.Configure.tileSize, null);
            }
        }

//        render potions

        for (Potion potion : potions) {
            if (potion == null) continue;
            switch (potion.getType()) {
                case "attack":
                    image = potionAttack;
                    break;
                case "speed":
                    image = potionSpeed;
                    break;
                case "health":
                    image = potionHealth;
                    break;
            }
            g2.drawImage(image, potion.getX() - map.getOffsetX(), potion.getY(), 32, 32, null);
        }

//       render monsters

        for (Monster monster : monsters) {
            if (monster.isKilled()) continue;
            g2.drawImage(monster.getImage(), monster.getX() - map.getOffsetX(), monster.getY(), monster.getWidth(), monster.getHeight(), null);
        }

        g2.drawImage(portal.getImage(), portal.getX() - map.getOffsetX(), portal.getY(), Main.Configure.tileSize * 2, Main.Configure.tileSize * 2, null);
    }
}
