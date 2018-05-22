package brain;


import math.Matrixf;
import math.Vectorf;
import math.Function;

public class NeuralNetwork {
	
	private Matrixf weightsInputHidden;
	private Matrixf weightsHiddenOutput;
	
	private Vectorf biasHidden;
	private Vectorf biasOutput;
	
	private int inputSize;
	private int hiddenSize;
	private int outputSize;
	
	private Vectorf error;
	
	private final float learningRate = 0.01f;
	
	
	public NeuralNetwork(int inputSize, int hiddenNeuronAmount, int outputSize) {
		this.weightsInputHidden = new Matrixf(hiddenNeuronAmount, inputSize);
		this.weightsInputHidden.randomize();
		this.weightsHiddenOutput = new Matrixf(outputSize, hiddenNeuronAmount);
		this.weightsHiddenOutput.randomize();
		
		this.biasHidden = new Vectorf(hiddenNeuronAmount);
		this.biasHidden.randomize();
		this.biasOutput = new Vectorf(outputSize);
		this.biasOutput.randomize();
		
		this.inputSize = inputSize;
		this.hiddenSize = hiddenNeuronAmount;
		this.outputSize = outputSize;
		
	}
	

	public Vectorf guess(Vectorf input) {
		Vectorf hidden1 = weightsInputHidden.multiply(input);
		Vectorf hidden2 = hidden1.add(biasHidden);
		Vectorf hidden3 = hidden2.applyFunction(Function.SIGMOID);

		Vectorf output1 = weightsHiddenOutput.multiply(hidden3);
		Vectorf output2 = output1.add(biasOutput);
		Vectorf output3 = output2.applyFunction(Function.SIGMOID);
		
		return output3;
	}
	


	public void train(Vectorf input, Vectorf target) {
		
		//	Guess
		Vectorf hiddenValues = weightsInputHidden.multiply(input);
		Vectorf hiddenBiased = hiddenValues.add(biasHidden);
		Vectorf hiddenActivated = hiddenBiased.applyFunction(Function.SIGMOID);

		Vectorf outputValues = weightsHiddenOutput.multiply(hiddenActivated);
		Vectorf outputBiased = outputValues.add(biasOutput);
		Vectorf outputActivated = outputBiased.applyFunction(Function.SIGMOID);
		
		
		
		//	Output errors
		Vectorf errorOutput = target.substract(outputActivated);
		error = errorOutput;
		
		Vectorf outputGradient = outputBiased.applyFunction(Function.D_SIGMOID);
		outputGradient = outputGradient.multiply(errorOutput);
		outputGradient = outputGradient.multiply(learningRate);
		Matrixf deltaW_ho = outputGradient.toMatrixf().cross(hiddenActivated.toMatrixf().transpose());
		
		weightsHiddenOutput = weightsHiddenOutput.add(deltaW_ho);
		biasOutput = biasOutput.add(outputGradient);
		
		
		
		//	Hidden errors
		Vectorf errorHidden = weightsHiddenOutput.transpose().multiply(errorOutput);
		
		Vectorf hiddenGradient = hiddenBiased.applyFunction(Function.D_SIGMOID);
		hiddenGradient = hiddenGradient.multiply(errorHidden);
		hiddenGradient = hiddenGradient.multiply(learningRate);
		Matrixf deltaW_ih = hiddenGradient.toMatrixf().cross(input.toMatrixf().transpose());
		
		weightsInputHidden = weightsInputHidden.add(deltaW_ih);
		biasHidden = biasHidden.add(hiddenGradient);
		
	}
	
	public float getError() {
		float error = 0;
		for (int i = 0; i < outputSize; i++) {
			error += this.error.getValueAt(i) * this.error.getValueAt(i);
		}
		return (float) Math.sqrt(error);
	}


	


	
	

}
