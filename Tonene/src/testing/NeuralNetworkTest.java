package testing;

import brain.NeuralNetwork;
import gui.Point;

public class NeuralNetworkTest {

	public static void main(String[] args) {
		
		NeuralNetwork brain = new NeuralNetwork(2, 2, 1);
		float[] input = {3,2};
		float[] output = brain.guess(input);
		System.out.println(output[0]);

	}

}
