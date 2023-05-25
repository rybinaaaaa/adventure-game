package rybina.View;

import rybina.Main;
import rybina.Model.Entity.Entity;

import java.awt.*;

public class PlayerRenderer {
    Entity entity;

    public PlayerRenderer(Entity entity) {
        this.entity = entity;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.drawOval(entity.getX() + entity.getRealWidth()/2, entity.getY() - 47, 20, 20);
//        g2.drawOval(entity.getX() + entity.getRealWidth() * 4 / 5, entity.getY() + Main.Configure.tileSize, 20, 20);
        g2.drawImage(entity.getImage(), entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight(), null);
    }
}
