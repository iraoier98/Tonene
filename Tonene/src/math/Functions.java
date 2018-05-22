package math;

public class Functions {
	
	public static float applyFunction(Function function, float value) {
		if (function.equals(Function.SIGMOID)) {
			return sigmoid(value);
		}
		else if (function.equals(Function.D_SIGMOID)) {
			return dsigmoid(value);
		}
		return 0;
	}
	
	public static float sigmoid(float value) {
		return (float) (1 / (1 + Math.exp(-value)));
	}
	
	public static float dsigmoid(float value) {
		return sigmoid(value) * (1 - sigmoid(value));
	}
	
	public static void main(String[] args) {
		
		for (float i = -5; i < 6; i++) {
			System.out.println("s'(" + i + ") = " + applyFunction(Function.D_SIGMOID, i));
		}
		
	}
	

}
