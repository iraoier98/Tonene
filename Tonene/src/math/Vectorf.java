package math;

import java.util.Random;

public class Vectorf {
	
	//	Attributes:
	
	private float[] data;
	private int length;
	
	
	
	
	//	Constructors:
	
	public Vectorf(int length) {
		this.length = length;
		this.data = new float[length];
	}
	
	public Vectorf(float[] array) {
		this.length = array.length;
		this.data = array;
	}
	
	
	
	
	//	Getters:
	
	public float[] toArray() {
		return data;
	}
	
	
	public int length() {
		return length;
	}

	public float getValueAt(int index) {
		if (index >= length || index < 0) {
			//TODO: error
		}
		return data[index];
	}
	
	
	
	
	//	Math
	

	public Vectorf negative() {
		
		float[] result = new float[length];
		
		for (int i = 0; i < length; i++) {
			result[i] = -data[i];
		}
		
		return new Vectorf(result);
	}
	

	public Vectorf add(Vectorf other) {
		if (this.length != other.length) {
//			TODO: error
//			System.out.println(this.length);
//			System.out.println(other.length);
		}
		
		float[] result = new float[length];
		
		for (int i = 0; i < length; i++) {
			result[i] = data[i] + other.data[i];
		}
		
		return new Vectorf(result);
	}
	
	
	public Vectorf substract(Vectorf other) {
		if (this.length != other.length) {
//			TODO: error
//			System.out.println(this.length);
//			System.out.println(other.length);
		}
		
		other = other.negative();		
		return add(other);
	}
	
	
	public Vectorf multiply(Vectorf other) {
		if (this.length != other.length) {
			//TODO: error
		}
		float[] result = new float[length];
		
		for (int i = 0; i < length; i++) {
			result[i] = data[i] * other.data[i];
		}
		
		return new Vectorf(result);
	}
	

	public Vectorf multiply(float scalar) {
		float[] result = new float[length];
		
		for (int i = 0; i < length; i++) {
			result[i] = data[i] * scalar;
		}
		
		return new Vectorf(result);
	}
	
	
	public Vectorf applyFunction(Function function) {
		float[] result = new float[length];
		
		for (int i = 0; i < length; i++) {
			result[i] = Functions.applyFunction(function, data[i]);
		}
		
		return new Vectorf(result);
	}
	
	
	
	
	
	
	//	Others:
	

	public void randomize() {
		Random r = new Random();
		for (int i = 0; i < length; i++) {
			data[i] = r.nextFloat() * 2 - 1;
		}
	}
	
	
	public int indexMax() {
		int max = 0;
		for (int i = 1; i < length; i++) {
			if (data[max] < data[i]) {
				max = i;
			}
		}
		return max;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < length; i++) {
			result += data[i] + " ";
		}
		return result;
	}
	
	
	public Matrixf toMatrixf() {
		Matrixf result = new Matrixf(length, 1);
		
		for (int i = 0; i < length; i++) {
			result.setValue(data[i], i, 0);
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		float[] one = {0,1,2,3};
		float[] two = {2,5,2,1};
		
		Vectorf a = new Vectorf(one);
		Vectorf b = new Vectorf(two);
		
		System.out.println("A = " + a);
		System.out.println("B = " + b);
		
		
		System.out.println("A + B = " + a.add(b));
		System.out.println("B + A = " + b.add(a));

		System.out.println("A - B = " + a.substract(b));
		System.out.println("B - A = " + b.substract(a));

		System.out.println("A * B = " + a.multiply(b));
		System.out.println("B * A = " + b.multiply(a));

		System.out.println("A * 2.5 = " + a.multiply(2.5f));
		System.out.println("B * 2.5 = " + b.multiply(2.5f));
	}

}
