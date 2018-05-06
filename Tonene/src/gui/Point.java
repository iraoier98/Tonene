package gui;

import java.util.Random;

public class Point {
	
	private float x, y;
	private float outcome;
	
	
	public Point() {
		Random r = new Random();
		x = 2 * r.nextFloat() - 1;
		y = 2 * r.nextFloat() - 1;
		if (y > x) {
			outcome = 1;
		}else {
			outcome = 0;
		}
	}

	
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}


	public float getX() {
		return x;
	}


	public float getY() {
		return y;
	}


	public float getOutcome() {
		return outcome;
	}
	
	

}
