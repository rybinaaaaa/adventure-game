package rybina.View;

import rybina.Model.Entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HealthBarRenderer {
    Entity entity;
    private int x, y;
    private int size = 32;
    private final int healthCount = 5;

    BufferedImage[] healthArray = new BufferedImage[healthCount];

    private void loadHealth() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/healthBar/healthBar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                assert image != null;
                if (j + 3 * i == healthCount) break;
//                because of source this image i decided to write it reversed for simple usage
                healthArray[healthCount - 1 - (j + 3 * i)] = image.getSubimage(j * size, i * size, size, size);
            }
        }

    }

    public HealthBarRenderer(Entity entity, int x, int y) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        loadHealth();
    }

    public HealthBarRenderer(Entity entity) {
        this(entity, 20, 20);
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        double healthRank = entity.getMaxHealth() / 4;
        image = healthArray[(int) entity.getHealth() / (int)healthRank];

        g2.drawImage(image, x, y, size * 3, size * 3, null);
    }
}
