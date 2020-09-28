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
public class Neuron {

    public ArrayList<Weight> forwardConnections;
    public ArrayList<Weight> backwardConnections;
    public double bias;
    public double value;
    public String id = "";
    public static int number = 0;
    public int idNum = 0;

    public Neuron() {
        this.idNum = number++;

    }

    public String getAffected() {
        String out = "";
        for (Weight forwardConnection : this.forwardConnections) {
            out += forwardConnection.getValue() + " ";
        }
        return out;
    }

    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static int getNumber() {
        int[] f = {1};
        number++;
        //System.out.println(number);

        return number;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getBias() {
        return this.bias;
    }

    public void calcOutput(double w1, double w2, Neuron n1, Neuron n2) {
        this.setValue(n1.getValue() * w1 + n2.getValue() * w2 + this.bias);
    }

    @Override
    public String toString() {
        return this.idNum + "";
    }

}
