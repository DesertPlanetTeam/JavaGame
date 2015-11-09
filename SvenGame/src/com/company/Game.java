package com.company;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "2D Sven Game";
	
	private boolean running = false;
	private Thread thread;
	
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
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1_000_000_000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		
		while (running) {
			long now = System.nanoTime();
			delta = delta + (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer = timer + 1000;
				System.out.println(updates + "Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	
	private void tick(){
		
	}
	
	private void render(){
		
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


	
}
