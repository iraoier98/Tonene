package math;

public class Vectorf {
	
	public static float[] add(float[] v1, float[] v2) {
		int length = v1.length;
		float[] result = new float[length];
		
		for (int i = 0; i < length; i++) {
			result[i] = v1[i] + v2[i];
		}
		
		return result;
	}
	
	public static float[] applyFunction(float[] array, Function function) {
		int length = array.length;
		float[] result = new float[length];
		
		for (int i = 0; i < length; i++) {
			result[i] = Functions.applyFunction(function, array[i]);
		}
		
		return result;
	}

}
