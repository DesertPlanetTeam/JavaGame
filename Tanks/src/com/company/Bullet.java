package com.company;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet implements Entity{

    private double x;
    private double y;

    private Textures tex;

    public Bullet (double x, double y, Textures tex){
        this.x = x;
        this.y = y;
        this.tex = tex;
    }

    public void tick(){
        y -= 10;

    }
    public void render (Graphics g){
        g.drawImage(tex.missle, (int)x, (int)y, null);
    }
    public double getY(){
        return y;
    }
    public double getX(){
        return x;
    }
}
