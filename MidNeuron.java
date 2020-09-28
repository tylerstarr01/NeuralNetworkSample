/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class MidNeuron extends Neuron {

    public MidNeuron(double bias) {
        this.id = "MidNeuron " + getNumber();
        this.bias = bias;
        this.value = 0;
        this.backwardConnections = new ArrayList<>();
        this.forwardConnections = new ArrayList<>();
    }

    public ArrayList<Weight> getForwardConnections() {
        return this.forwardConnections;
    }

    public ArrayList<Weight> getBackwardConnections() {
        return this.backwardConnections;
    }

    public void setValue() {
        ArrayList<Weight> weights = this.getBackwardConnections();
        for (int i = 0; i < weights.size(); i++) {
            //this.value += (weights.get(i)*weights.get(i).getStartingNeuron());
        }
    }

    public double calculateValue() {
        double j = 0;
        for (int i = 0; i < this.backwardConnections.size(); i++) {
            InputWeight iw = (InputWeight) this.backwardConnections.get(i);
            j += (iw.getValue() * iw.getStartingNeuron().getValue());
        }
        j += this.bias;
        return sigmoid(j);
    }

}
