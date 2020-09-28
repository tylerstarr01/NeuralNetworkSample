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
public class HiddenLayer {

    private static int layerNum;
    private int num;
    private ArrayList<MidNeuron> mn;

    public HiddenLayer(int length, double bias) {
        this.num = layerNum++;
        this.mn = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            mn.add(new MidNeuron(bias));
        }
        
    }

    public int getNum() {
        return this.num;
    }

    public ArrayList<MidNeuron> getMnList() {
        return this.mn;
    }
}
