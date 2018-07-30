package brain;

import java.util.Random;

public class Perceptron {
	
	private float w1, w2;
	private float bias;
	
	private float lr;
	
	
	public Perceptron() {
		Random r = new Random();
		
		w1 = r.nextFloat();
		w2 = r.nextFloat();
		bias = r.nextFloat();
		
		lr = 0.1f;
	}
	
	public float guess(float x, float y) {
		float partial = x * w1 + y * w2 + bias;
		return Math.signum(partial);
	}
	
	
	public boolean train(float x, float y, float desiredOutcome) {
		float outcome = guess(x, y);
		float error = desiredOutcome - outcome;
		
		w1 += error * x * lr;
		w2 += error * y * lr;
		bias += error * lr;
		
		return error == 0;
	}
	
	
	
	
	
	private float sigmoid(float value) {
		return (float) (1 / (1 + Math.exp(-value)));
	}

}
