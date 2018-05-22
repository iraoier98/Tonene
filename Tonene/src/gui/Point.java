package gui;

import java.util.Random;

public class Point {
	
	private float x, y;
	private int outcome;
	
	
	public Point() {
		Random r = new Random();
		x = 2 * r.nextFloat() - 1;
		y = 2 * r.nextFloat() - 1;
		if (y >= 0 && x >= 0) {
			outcome = 0;
		}else if (y >= 0 && x < 0){
			outcome = 1;
		}else if (y < 0 && x < 0) {
			outcome = 2;
		}else {
			outcome = 3;
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


	public int getOutcome() {
		return outcome;
	}
	
	public float[] toArray() {
		float[] result = {x, y};
		return result;
	}
	
	

}
