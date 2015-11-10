package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "Tanks";

    private boolean running = false;
    private Thread thread;

    private int enemy_count = 1;
    private int enemy_killed = 0;

    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;


    private boolean is_shooting = false;
    private Player p;
    private Controller c;
    private Textures tex;

    public void init(){
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();
        try{

            spriteSheet = loader.loadImage("/sprite_sheet.png");
            background = loader.loadImage("/background.png");
        }catch(IOException e){
            e.printStackTrace();
        }

        this.addKeyListener(new KeyInput(this));

        tex = new Textures(this);

        p = new Player(200, 200, tex);
        c = new Controller(tex);

        c.createEnemy(enemy_count);
    }

    private synchronized void start(){
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        if (!running) {
            return;
        }

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void run(){
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1_000_000_000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();


        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + "Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }


    private void tick(){
        p.tick();
        c.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        g.drawImage(background, 0, 0, this);

        p.render(g);
        c.render(g);

        g.dispose();
        bs.show();
    }

    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT){
            p.setVelX(5);
        } else if(key == KeyEvent.VK_LEFT){
            p.setVelX(-5);
        }else if(key == KeyEvent.VK_DOWN){
            p.setVelY(5);
        }else if(key == KeyEvent.VK_UP){
            p.setVelY(-5);
        }else if(key == KeyEvent.VK_SPACE && !is_shooting){
            is_shooting = true;
            c.addEntity(new Bullet(p.getX(), p.getY(), tex));
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT){
            p.setVelX(0);
        }else if(key == KeyEvent.VK_LEFT){
            p.setVelX(0);
        }else if(key == KeyEvent.VK_DOWN){
            p.setVelY(0);
        }else if(key == KeyEvent.VK_UP){
            p.setVelY(0);
        }else if(key == KeyEvent.VK_SPACE){
            is_shooting = false;
        }
    }

    public static void main(String args[]){
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }

    public int getEnemy_count() {
        return enemy_count;
    }

    public void setEnemy_count(int enemy_count) {
        this.enemy_count = enemy_count;
    }

    public int getEnemy_killed() {
        return enemy_killed;
    }

    public void setEnemy_killed(int enemy_killed) {
        this.enemy_killed = enemy_killed;
    }
}

