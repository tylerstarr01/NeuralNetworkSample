/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

/**
 *
 * @author user
 */
public class OutputWeight extends Weight {

    OutputNeuron on;
    MidNeuron mn;

    public OutputWeight(MidNeuron mid, OutputNeuron out, double weight) {
        this.on = out;
        this.mn = mid;
        this.weight = weight;
        this.mn.forwardConnections.add(this);
        this.on.backwardConnections.add(this);
    }

    public MidNeuron getStartingNeuron() {
        return this.mn;
    }

    public OutputNeuron getEndingNeuron() {
        return this.on;
    }
    
    @Override
    public double calcChange() {
        return -(this.getEndingNeuron().getTarget() - this.getEndingNeuron().getValue()) * this.getEndingNeuron().getValue() * (1 - this.getEndingNeuron().getValue()) * this.getStartingNeuron().getValue() * Teal.learningRate;
    }
}
