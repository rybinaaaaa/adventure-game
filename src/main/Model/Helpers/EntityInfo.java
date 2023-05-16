package main.Model.Helpers;

import main.Model.Entity.Entity;

import java.io.Serializable;

public class EntityInfo implements Serializable {
    public int x = 0;
    public int y = 0;
    public double health = 0;

    public EntityInfo(Entity entity) {
        setEntityInfo(entity);
    }

    public void setEntityInfo(Entity entity) {
        this.x = entity.getX();
        this.y = entity.getY();
        this.health = entity.getHealth();
    }
}
