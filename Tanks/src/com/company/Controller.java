package com.company;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

    private ArrayList<EntityA> ea = new ArrayList<EntityA>();
    private ArrayList<EntityB> eb = new ArrayList<EntityB>();

    EntityA enta;
    EntityB entb;

    Textures tex;
    Random r = new Random();
    private Game game;

    public Controller(Textures tex, Game game) {
        this.tex = tex;
        this.game = game;
    }

    public void createEnemy(int enemy_count) {
        for (int i = 0; i < enemy_count; i++) {
            addEntity(new Enemy(r.nextInt(640), -10, tex, this, game));
        }
    }

    public void tick(){
        for (int i = 0; i < ea.size(); i++) {
            enta = ea.get(i);

            enta.tick();
        }
        for (int i = 0; i < eb.size(); i++) {
            entb = eb.get(i);

            entb.tick();
        }
    }

    public void render (Graphics g){
        for (int i = 0; i < ea.size(); i++) {
            enta = ea.get(i);

            enta.render(g);
        }
        for (int i = 0; i < eb.size(); i++) {
            entb = eb.get(i);

            entb.render(g);
        }
    }

    public void addEntity(EntityA block) {
        ea.add(block);
    }
    public void removeEntity(EntityA block) {
        ea.remove(block);
    }
    public void addEntity(EntityB block) {
        eb.add(block);
    }
    public void removeEntity(EntityB block) {
        eb.remove(block);
    }

    public ArrayList<EntityA> getEntityA(){
        return ea;
    }
    public ArrayList<EntityB> getEntityB(){
        return eb;
    }
}
