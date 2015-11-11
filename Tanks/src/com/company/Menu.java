package com.company;

import java.awt.*;

public class Menu {


    public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 150, 100, 50);
    public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120, 250, 100, 50);
    public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);

    public void render(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("Berlin Sans FB",Font.BOLD, 80);
        g.setFont(fnt0);
        g.setColor(Color.ORANGE);
        g.drawString("TANKS", Game.WIDTH / 2 + 30, 100);

        Font fnt1 = new Font("arial", Font.ITALIC, 40);
        g.setColor(Color.white);
        g.setFont(fnt1);
        g.drawString("Play", playButton.x + 12, playButton.y + 39);
        g2d.draw(playButton);
        g.drawString("Help", helpButton.x + 12, helpButton.y + 39);
        g2d.draw(helpButton);
        g.drawString("Quit", quitButton.x + 12, quitButton.y + 39);
        g2d.draw(quitButton);
    }
}
