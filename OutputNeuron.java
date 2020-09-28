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
public class OutputNeuron extends Neuron {

    public double target;

    public OutputNeuron(double bias, double target) {
        this.id = "OutNeuron " + getNumber();
        this.bias = bias;
        this.target = target;
        this.value = 0;
        this.backwardConnections = new ArrayList<>();
    }

    public double getTarget() {
        return this.target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double calculateValue() {
        double j = 0;
        for (int i = 0; i < this.backwardConnections.size(); i++) {
            OutputWeight iw = (OutputWeight) this.backwardConnections.get(i);
            j += (iw.getValue() * iw.getStartingNeuron().getValue());
        }
        j += this.bias;
        return sigmoid(j);
    }

}
