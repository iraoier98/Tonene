package testing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;

import brain.NeuralNetwork;
import math.Vectorf;

public class Loop extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private final static int WIDTH = 1000;
	private final static int HEIGHT = 1000;
	private final static int SCALE = 1;
	
	private Thread thread;
	private JFrame frame;
	private boolean running = false;
	
//	private static Mouse mouse;
//	private static Keyboard keyboard;
	
	private static NeuralNetwork brain;
	
	private static int[][] colors;
	
	
	public Loop() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Yieh");
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	//At start
	public synchronized void start() {
		
		
		//	Add mouse and keyboard input handling.
//		mouse = new Mouse();
//		addMouseListener(mouse);
//		keyboard = new Keyboard();
//		addKeyListener(keyboard);
		

		brain = new NeuralNetwork(2, 2, 4);
		
		colors = new int[80][80];
		
		
		//	Begin thread execution.
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	//At stop
	public synchronized void stop() {
		running = false;
		try{
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//At run
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
//				System.out.println(updates + " ups and " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}
	
	public void update() {
	}
	
//	public static void registerClick(int x, int y){
//	}
	
	public static void registerKey(char c){
	}
	

	
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		super.update(g);
		/**	#############################	*/
		//	DO NOT MODIFY ABOVE
		
		g.drawRect(100,	100, 800, 800);
		g.drawLine(500, HEIGHT - 100, 500, 100);
		g.drawLine(WIDTH - 100, 500, 100, 500);
		


		Random r = new Random();

		//	Guess
		for (int i = 0; i < 80; i++) {
			float x = map(i, 0, 80, -1, 1);
			for (int j = 0; j < 80; j++) {
				float y = map(j, 0, 80, 1, -1);
				float[] array = {x, y};
				Vectorf result = brain.guess(new Vectorf(array));
				int color = result.indexMax();
				if (color == 0) {
					g.setColor(Color.RED);
				}
				else if (color == 1) {
					g.setColor(Color.GREEN);
				}
				else if (color == 2) {
					g.setColor(Color.BLUE);
				}
				else {
					g.setColor(Color.YELLOW);
				}
				g.fillRect(100 + 10*i, 100 + 10*j, 10, 10);
			}
		}
		g.setColor(Color.BLACK);
		g.drawRect(100,	100, 800, 800);
		g.drawLine(500, HEIGHT - 100, 500, 100);
		g.drawLine(WIDTH - 100, 500, 100, 500);
		
		//	Train
		for (int loop = 0; loop < 50; loop++) {
			float gridI = r.nextFloat() * 2 - 1;
			float gridJ = r.nextFloat() * 2 - 1;
			float[] array = {gridI, gridJ};
			
			Vectorf input = new Vectorf(array);
			Vectorf target = new Vectorf(outcomeArray(getOutcome(gridI, gridJ)));
			brain.train(input, target);
		}
		
		g.fillOval(950, (int) (900 - brain.getError() * 800), 16, 16);
		
		
		
		
		
		
		

		//	DO NOT MODIFY BELOW
		/**	###############################	*/
		g.dispose();
		bs.show();
	}
	
	private static int getOutcome(float x, float y) {
		if (x >= 0 && y >= 0) {
			return 0;
		}
		if (x < 0 && y >= 0) {
			return 1;
		}
		if (x < 0 && y < 0) {
			return 2;
		}
		return 3;
		
	}
	
	private static float[] outcomeArray(int outcome) {
		float[] yieh = new float[4];
		yieh[outcome] = 1;
		return yieh;
	}
	

	
	//TODO: generalize
	private float map(float value, float x1, float x2, float y1, float y2) {
		float ydiff = y2 - y1;
		float xdiff = x2 - x1;
		return y1 + (ydiff / xdiff) * (value - x1);
	}

	//Frame settings
	public static void main(String[] args) {
		Loop loop = new Loop();
		loop.start();
	}

}
