package main.View;

import main.Model.Entity.Entity;

import java.awt.*;

public class EntityRenderer {
    Entity entity;

    public EntityRenderer(Entity entity) {
        this.entity = entity;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(entity.getImage(), entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight(), null);
    }
}
