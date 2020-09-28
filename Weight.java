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
public class Weight {

    public double error;
    public double weight;
    public int number;
    public static ArrayList<Weight> allWeights;

    public Weight() {
        //allWeights.add(this);
       
    }

    public int id() {
        return this.number;
    }

    public double calcChange() {
//        this.change =  -(target - calculated)*calculated*(1-calculated)*midValue;
        return 0;
    }

    public double getValue() {
        return this.weight;
    }

    public void updateWeight(double delta) {
        this.weight -= delta;
    }
}
