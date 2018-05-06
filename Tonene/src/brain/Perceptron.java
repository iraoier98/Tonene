package brain;

import java.util.Random;

import gui.Point;

public class Perceptron {
	
	private float w1;
	private float w2;
	private final float learningRate = 0.05f;
	
	public Perceptron() {
		Random r = new Random();
		w1 = r.nextFloat();
		w2 = r.nextFloat();
	}
	
	public float guess(Point p) {
		float guess = p.getX() * w1 + p.getY() * w2;
		return activate(guess);
	}
	
	private float activate(float value) {
		if (value < 0) {
			return 0;
		}
		return 1;
	}
	
	
	
	public void train(Point p) {
		float guess = guess(p);
		float target = p.getOutcome();
		float error = target - guess;
		
		w1 += error * p.getX() * learningRate;
		w2 += error * p.getY() * learningRate;
	}

	public float getW1() {
		return w1;
	}

	public float getW2() {
		return w2;
	}
	
	

}
