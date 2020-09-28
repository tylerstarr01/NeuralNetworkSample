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
public class InputWeight extends Weight {

    public InputNeuron in;
    public MidNeuron mn;

    public InputWeight(InputNeuron in, MidNeuron mn, double weight) {
        this.in = in;
        this.mn = mn;
        this.weight = weight;
        this.in.forwardConnections.add(this);
        this.mn.backwardConnections.add(this);

    }

    public InputNeuron getStartingNeuron() {
        return this.in;
    }

    public MidNeuron getEndingNeuron() {
        return this.mn;
    }

    public String toString() {
        return "In From " + getStartingNeuron() + " to " + getEndingNeuron();
    }

    @Override
    public double calcChange() {
        double change = 0;
        ArrayList<Weight> affected = this.mn.getForwardConnections();
        for (int i = 0; i < affected.size(); i++) {
            change += affected.get(i).calcChange() / mn.getValue() * affected.get(i).getValue();
        }
        change *= mn.getValue() * (1 - mn.getValue()) * in.getValue();
        return change * Teal.learningRate;
    }
}
