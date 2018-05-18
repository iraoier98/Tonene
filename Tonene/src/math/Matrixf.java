package math;

import java.util.Random;

public class Matrixf {
	
	public static float[] matrixVector(float[][] matrix, float[] vector) {
		float[] result = new float[matrix.length];
		float sum;
		for (int row = 0; row < matrix.length; row++) {
			sum = 0;
			for (int col = 0; col < vector.length; col++) {
//				System.out.println("Calculating m[" + row + ","+ col + "] * v[" + col + "]..");
				sum += matrix[row][col] * vector[col];
			}
			result[row] = sum;
		}
		return result;
	}
	
	/**
	 * Returns a matrix filled with values ranging from -1 to 1
	 * @param rows
	 * @param cols
	 * @return
	 */
	public static float[][] randomMatrix(int rows, int cols){
		float[][] result = new float[rows][cols];
		Random r = new Random();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[i][j] = r.nextFloat() * 2 - 1;
			}
			
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		
		float[][] matrix = {{2,1},{3,-2},{0,1}};
		float[] vector = {2,2};
		float[] result = matrixVector(matrix, vector);
		
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
		
	}
	
	

}
