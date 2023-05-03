package main.View;

import main.Model.entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerRenderer {

    Player player;

    public PlayerRenderer(Player player) {
        this.player = player;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(player.getImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), null);
    }
}
