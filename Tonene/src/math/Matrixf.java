package math;

import java.util.Random;

public class Matrixf {
	
	//	Attributes:
	
	private float[][] data;
	private int height, width;
	
	
	
	
	//	Constructors:
	
	public Matrixf(int height, int width) {
		this.height = height;
		this.width = width;
		this.data = new float[height][width];
	}
	

	public Matrixf(float[][] values) {
		this.height = values.length;
		this.width = values[0].length;
		this.data = values;
	}
	

	public Matrixf(Vectorf diagonalValues) {
		this.height = diagonalValues.length();
		this.width = diagonalValues.length();
		this.data = new float[this.height][this.width];
		
		for (int i = 0; i < this.width; i++) {
			data[i][i] = diagonalValues.getValueAt(i);
		}
	}
	
	
	
	
	//	Getters:
	
	
	
	
	
	
	
	//	Math:

	public Matrixf negative() {
		
		float[][] result = new float[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				result[i][j] = -data[i][j];
			}
		}
		
		return new Matrixf(result);
	}
	
	
	public Matrixf transpose() {
		Matrixf result = new Matrixf(width, height);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				result.data[j][i] = this.data[i][j];
			}
		}
		return result;
	}
	
	
	public Matrixf add(Matrixf other) {
		if (this.width != other.width || this.height != other.height) {
			//TODO: error
		}
		
		float[][] result = new float[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				result[i][j] = this.data[i][j] + other.data[i][j];
			}
		}
		return new Matrixf(result);
	}
	
	
	public Matrixf substract(Matrixf other) {
		if (this.width != other.width || this.height != other.height) {
			//TODO: error
		}
		
		Matrixf negative = other.negative();
		return add(negative);
	}
	

//	public Matrixf multiply(Matrixf other) {
//		
//		if (width != other.height) {
//			//TODO: error
//		}
//		
//		float[][] result = new float[height][other.width];
//		float sum;
//		for (int row = 0; row < height; row++) {
//			for (int col = 0; col < width; col++) {
//
//				sum = 0;
//				for (int zi = 0; zi < width; zi++) {
////					System.out.println("Calculating A[" + row + ","+ zi + "] * B[" + zi + ","+ col + "]...");
//					sum += data[row][zi] * other.data[zi][col];
//				}
//				result[row][col] = sum;
//			}
//		}
//		
//		return new Matrixf(result);
//	}

	
	public Vectorf multiply(Vectorf vector) {
		if (width != vector.length()) {
			//TODO: error
		}
		
		float[] result = new float[height];
		float sum;
		for (int row = 0; row < height; row++) {
			sum = 0;
			for (int col = 0; col < width; col++) {
//				System.out.println("Calculating m[" + row + ","+ col + "] * v[" + col + "]...");
				sum += data[row][col] * vector.getValueAt(col);
			}
			result[row] = sum;
		}
		
		return new Vectorf(result);
		
	}
	
	public Matrixf multiply(float scalar) {		
		float[][] result = new float[height][width];
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				result[row][col] += data[row][col] * scalar;
			}
		}
		
		return new Matrixf(result);
		
	}
	
	
	public Matrixf cross(Matrixf other) {
		
		if (width != other.height) {
			//TODO: error
		}
		
		float[][] result = new float[height][other.width];
		float sum;
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < other.width; col++) {

				sum = 0;
				for (int zi = 0; zi < width; zi++) {
//					System.out.println("Calculating A[" + row + ","+ zi + "] * B[" + zi + ","+ col + "]...");
					sum += data[row][zi] * other.data[zi][col];
				}
				result[row][col] = sum;
			}
		}
		
		return new Matrixf(result);
	}
	
	
	
	
	//	Others:
	
	public void setIdentity() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == j) {
					data[i][j] = 1;
				}
				else {
					data[i][j] = 0;
				}
			}
		}
	}

	
	public void randomize() {
		Random r = new Random();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				data[i][j] = r.nextFloat() * 2 - 1;
			}
		}
	}
	
	
	public void setValue(float value, int row, int col) {
		if (row < 0 || row >= height || col < 0 || col >= width) {
			//TODO: error
		}
		data[row][col] = value;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				result += data[i][j] + " ";
			}
			if (i < height - 1) {
				result += "\n";	
			}
		}
		return result;
	}
	
	
	
	public float columnProportion(int row, int col) {
		float value = data[row][col];
		float total = 0;
		for (int i = 0; i < height; i++) {
			total += data[i][col];
		}
		return value / total;
	}

	public float rowProportion(int row, int col) {
		float value = data[row][col];
		float total = 0;
		for (int i = 0; i < width; i++) {
			total += data[row][i];
		}
		return value / total;
	}
	
	
	
	public static void main(String[] args) {
		float[][] one = {{1,0},{0,1}};
		float[][] two = {{2,3},{1,1},{-1,2}};
		
		Matrixf A = new Matrixf(one);
		Matrixf B = new Matrixf(two);

		float[] v1 = {1,1};
		float[] v2 = {2,5};
		
		Vectorf a = new Vectorf(v1);
		Vectorf b = new Vectorf(v2);
		
		System.out.println(B + "\n");
		System.out.println(a + "\n");
		
		System.out.println("B x a\n" + B.multiply(a) + "\n");
		

	
	}
	
	

}
