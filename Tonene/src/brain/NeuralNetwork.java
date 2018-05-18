package brain;

import java.util.Random;

import gui.Point;
import math.Matrixf;
import math.Vectorf;
import math.Function;

public class NeuralNetwork {
	
	private float w1;
	private float w2;
	
	private float[][] weightsInputHidden;
	private float[][] weigthsHiddenOutput;
	
	private float[] biasHidden;
	private float[] biasOutput;
	
	private final float learningRate = 0.05f;
	
	public NeuralNetwork() {
		Random r = new Random();
		w1 = r.nextFloat();
		w2 = r.nextFloat();
	}
	
	public NeuralNetwork(int inputSize, int hiddenNeuronAmount, int outputSize) {
		weightsInputHidden = Matrixf.randomMatrix(hiddenNeuronAmount, inputSize);
		weigthsHiddenOutput = Matrixf.randomMatrix(outputSize, hiddenNeuronAmount);
		
		biasHidden = new float[hiddenNeuronAmount];
		biasOutput = new float[outputSize];
	}
	
	public float guess(Point p) {
		float guess = p.getX() * w1 + p.getY() * w2;
		return activate(guess);
	}
	
	public float[] guess(float[] input) {
		float[] hiddenValues = Vectorf.applyFunction(Vectorf.add(Matrixf.matrixVector(weightsInputHidden, input), biasHidden), Function.SIGMOID);
		float[] outputValues = Vectorf.applyFunction(Vectorf.add(Matrixf.matrixVector(weigthsHiddenOutput, hiddenValues), biasOutput), Function.SIGMOID);
		
		return outputValues;
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
