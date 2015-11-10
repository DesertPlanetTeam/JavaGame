package com.company;

import java.awt.*;

public interface Entity {

    public void tick();
    public void render(Graphics g);

    public double getX();
    public double getY();

}
