package math;

public class Functions {
	
	public static float applyFunction(Function function, float value) {
		if (function.equals(Function.SIGMOID)) {
			return sigmoid(value);
		}
		return 0;
	}
	
	public static float sigmoid(float value) {
		return (float) (1 / (1 + Math.exp(-value)));
	}
	
	public static void main(String[] args) {
		
		for (float i = -5; i < 6; i++) {
			System.out.println(applyFunction(Function.SIGMOID, i));
		}
		
	}
	

}
