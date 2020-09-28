/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

import java.util.ArrayList;

/**
 *
 * @author student
 */
public class MidWeight extends Weight {

    public MidNeuron in;
    public MidNeuron mn;
    public int fromLayer;
    public int toLayer;

    public MidWeight(MidNeuron in, MidNeuron mn, double value, int fromLayer, int toLayer) {
        
        //this.start = in;
        this.in = in;
        this.fromLayer = fromLayer;
        this.toLayer = toLayer;
        this.mn = mn;
        this.weight = value;
        this.in.forwardConnections.add(this);
        this.mn.backwardConnections.add(this);
        throw new RuntimeException("L");

    }

    public Neuron getStartingNeuron() {
        return this.in;
    }

    public Neuron getEndingNeuron() {
        return this.mn;
    }

    @Override
    public String toString() {
        return "Mid From " + getStartingNeuron() + " to " + getEndingNeuron();
    }

    public int getFromLayer() {
        return this.fromLayer;
    }

    public int getToLayer() {
        return this.toLayer;
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
