package main.View;
import main.Main;
import main.Model.Menu;

import java.awt.*;


public class MenuScreen {

    Menu menu;
    public MenuScreen(Menu menu) {
        this.menu = menu;
    }
    public void draw(Graphics2D g2) {
        int x = 200;
        int y = 200;
        g2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 28));
        g2.setColor(Color.white);
        for (String option : menu.getOptions()) {
            if (option == menu.getSelectedOption()) {
                g2.drawString(">", x - Main.Configure.tileSize, y);
            }

            g2.drawString(option, x, y);

            y += 48;
        }
    }
}
