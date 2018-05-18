package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import brain.NeuralNetwork;

public class Loop extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private final static int WIDTH = 1000;
	private final static int HEIGHT = 1000;
	private final static int SCALE = 1;
	
	private Thread thread;
	private JFrame frame;
	private boolean running = false;
	
//	private static Mouse mouse;
	private static Keyboard keyboard;
	
	private static Point[] points;
	private static NeuralNetwork brain;
	private static int i = 0;
	
	
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
		keyboard = new Keyboard();
		addKeyListener(keyboard);
		

		brain = new NeuralNetwork();
		points = new Point[100];
		for (int i = 0; i < 100; i++) {
			Point p = new Point();
			points[i] = p;
		}
		
		
		
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
		while (brain.guess(points[i]) == points[i].getOutcome()) {		//gets stuck if all are correct, but hey, we got a correct answer at least xD
			i++;
			if (i == 100) {
				i = 0;
			}
		}
		brain.train(points[i]);
		i++;
		if (i == 100) {
			i = 0;
		}
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
		
		
		
		for (int i = 0; i < 100; i++) {
			
			g.drawLine(100, HEIGHT - 100, WIDTH - 100, 100);
			
			Point p = points[i];
			float x = p.getX();
			float y = p.getY();
			float px = map(x, -1, 1, 100, 900);
			float py = map(y, -1, 1, 900, 100);
			float diam = 15;
			
			if (p.getOutcome() == 1) {
				g.fillOval((int) (px - diam/2), (int) (py - diam/2), (int) diam, (int) diam);
			}else {
				g.drawOval((int) (px - diam/2), (int) (py - diam/2), (int) diam, (int) diam);
			}
			
			if (brain.guess(p) == p.getOutcome()) {
				g.setColor(Color.GREEN);
				g.fillOval((int) (px - diam/4), (int) (py - diam/4), (int) (diam/2), (int) (diam/2));
			}else {
				g.setColor(Color.RED);
				g.fillOval((int) (px - diam/4), (int) (py - diam/4), (int) (diam/2), (int) (diam/2));
			}
			g.setColor(Color.BLACK);
		}
		

		//	What the perceptron thinks
		g.setColor(Color.BLUE);
		float m = -brain.getW1() / brain.getW2();
		Point p1 = new Point(-1, m * -1);
		Point p2 = new Point(1, m * 1);
		int x1 = (int) map(p1.getX(), -1, 1, 100, 900);
		int x2 = (int) map(p1.getY(), -1, 1, 900, 100);
		int y1 = (int) map(p2.getX(), -1, 1, 100, 900);
		int y2 = (int) map(p2.getY(), -1, 1, 900, 100);
		g.drawLine(x1, x2, y1, y2);

		g.setColor(Color.BLACK);
		
		
		

		//	DO NOT MODIFY BELOW
		/**	###############################	*/
		g.dispose();
		bs.show();
	}
	
	private float f(float x) {
		return -x + HEIGHT;
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
