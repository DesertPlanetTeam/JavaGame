package com.company;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject implements EntityB {

    private Textures tex;
    Random r = new Random();
    private Game game;
    public Controller c;

    private int speed = r.nextInt(3) + 1;

    public Enemy(double x, double y, Textures tex, Controller c, Game game) {
        super(x, y);
        this.tex = tex;
        this.c = c;
        this.game = game;
    }

    public void tick() {
        y += speed;

        if(y > (Game.HEIGHT * Game.SCALE)) {
            speed = r.nextInt(3) + 1;
            x = r.nextInt(640);
            y = -10;
        }

        for (int i = 0; i < game.ea.size(); i++) {
            EntityA tempEnt = game.ea.get(i);

            if(Physics.Collision(this, tempEnt))
            {
                c.removeEntity(tempEnt);
                c.removeEntity(this);
                game.score += 5;
                game.setEnemy_killed(game.getEnemy_killed() + 1);
            }
        }
    }

    public void render(Graphics g) {
        g.drawImage(tex.enemy, (int)x, (int) y, null);
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
