package testing;

import java.util.Random;
import java.util.Scanner;

import brain.NeuralNetwork;
import math.Vectorf;

public class NeuralNetworkTest {

	public static void main(String[] args) {
		
		NeuralNetwork brain = new NeuralNetwork(2, 2, 2);
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		while (sc.hasNext()) {
			//	TRAIN
			for (int i = 0; i < 1; i++) {
				float x = r.nextFloat() * 2 - 1;
				float y = r.nextFloat() * 2 - 1;
				float[] array = {x,y};
				Vectorf input = new Vectorf(array);
//				Vectorf target = new Vectorf(outcomeArray(getOutcome(x, y)));
				Vectorf target = new Vectorf(2);
				
				brain.train(input, target);
			}
			
			sc.nextLine();
			
			//	TEST
			float x = r.nextFloat() * 2 - 1;
			float y = r.nextFloat() * 2 - 1;
			float[] array = {x,y};
			Vectorf input = new Vectorf(array);
			System.out.println("Input = " + input + "  " + "Outcome = " + getOutcome(x, y));
			System.out.println("Guess " + brain.guess(input));
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		sc.close();

	}
	
	private static int getOutcome(float x, float y) {
		int outcome;
		if (y >= 0 && x >= 0) {
			outcome = 0;
		}else if (y >= 0 && x < 0){
			outcome = 1;
		}else if (y < 0 && x < 0) {
			outcome = 2;
		}else {
			outcome = 3;
		}
		return outcome;
	}
	
	private static float[] outcomeArray(int outcome) {
		float[] yieh = new float[4];
		yieh[outcome] = 1;
		return yieh;
	}

}
