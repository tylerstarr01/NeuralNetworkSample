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
public class InputNeuron extends Neuron{
    public InputNeuron (double value) {
        this.id = "InputNeuron " + getNumber();
        this.value = value;
        this.forwardConnections = new ArrayList<>();
    }

    
}
