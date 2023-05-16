package main.View;

import main.Main;
import main.Model.Entity.Entity;

import java.awt.*;

public class PlayerRenderer {
    Entity entity;

    public PlayerRenderer(Entity entity) {
        this.entity = entity;
    }

    public void draw(Graphics2D g2) {
        if (entity.isKilled()) {
            g2.setColor(Color.white);
            g2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 130));

            g2.drawString("GAME OVER", 0, Main.Configure.maxScreenRow * Main.Configure.tileSize / 2);
            return;
        }
        g2.drawImage(entity.getImage(), entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight(), null);
    }
}
