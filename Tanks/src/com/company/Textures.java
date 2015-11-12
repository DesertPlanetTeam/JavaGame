package com.company;

import java.awt.image.BufferedImage;

public class Textures {

    public BufferedImage player, laser, enemy;

    private SpriteSheet ss;

    public Textures (Game game) {
        ss = new SpriteSheet(game.getSpriteSheet());

        getTextures();
    }

    private void getTextures() {
        player = ss.grabImage(1, 1, 32, 32);
        laser = ss.grabImage(2, 1, 32, 32);
        enemy = ss.grabImage(3, 1, 32, 32);
    }

    public void setPlayerTexturesLeftAndRight() {
        player = ss.grabImage(8, 2, 32, 32);
    }
    public void setPlayerTexturesFrontAndBack() {
        player = ss.grabImage(1, 1, 32, 32);
    }
}
