package rybina.View;
import rybina.Model.Entity.Entity;
import rybina.Model.Entity.Player;

import java.awt.*;

public class PlayerRenderer {
    Player player;

    public PlayerRenderer(Entity entity) {
        this.player = new Player();
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.drawOval(player.getX() + player.getRealWidth()/2, player.getY() - 47, 20, 20);
        g2.drawImage(player.getImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), null);
    }
}
