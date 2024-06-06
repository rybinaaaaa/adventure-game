package rybina.View;
import rybina.Model.Entity.Player;

import java.awt.*;

public class PlayerRenderer {
    Player player;

    public PlayerRenderer(Player player) {
        this.player = player;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.drawImage(player.getImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), null);
    }
}
