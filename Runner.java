/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

import java.util.Scanner;

/**
 *
 * @author tyler
 */
public class Runner {
    
    public static int boolToInt (boolean b) {
        if (b == true) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public static boolean intToBool (int i) {
        return i == 1;
    }

    public static void main(String[] args) {
        double[] input = new double[2];
        for (int i = 0; i < input.length; i++) {
            input[i] = 0;
        }
        double[] output = new double[1];
        double b1 = 0.35;
        double b2 = 0.5;
        Teal nn = new Teal(2, 13, 1, b1, b2, input, output); // numIn, numMn, numOn
        int count = 0;
        while (count < 500000) {
            count++;
        double rand = Math.random();
        nn.in.get(0).setValue(rand);
        nn.on.get(0).setTarget(rand*rand);
        nn.calcAll(true);
        double approx = nn.on.get(0).getValue();
        double exact = nn.on.get(0).getTarget();
        //System.out.println(nn.on.get(0).getTarget() + "   " + nn.on.get(0).getValue() + "     " + nn.in.get(0).getValue());
        //System.out.println(100*(approx-exact)/exact);

/*            int rand, first, second, target;
            boolean fBoolean, sBoolean, tBoolean;

            rand = (int) (Math.random() * 2 + 0);
            first = rand;
            rand = (int) (Math.random() * 2 + 0);
            second = rand;
            fBoolean = first == 1;
            sBoolean = second == 1;

            nn.in.get(0).setValue(first);
            nn.in.get(1).setValue(second);

            tBoolean = fBoolean ^ sBoolean;// && thirBool;
            if (tBoolean) {
                target = 1;
            } else {
                target = 0;
            }
            //target = 1.0 / (Math.pow(first, 2));

            nn.on.get(0).setTarget(target);

            nn.calcAll(true);
            double approx = nn.on.get(0).getValue();
            double exact = nn.on.get(0).getTarget();
        */
            //System.out.println(nn.on.get(0).getTarget() + "   " + nn.on.get(0).getValue() + "     " + nn.in.get(0).getValue() + "    " + nn.in.get(1).getValue());
//        System.out.println(100*(approx-exact)/exact);

        }
        System.out.println("done");
        Scanner sc = new Scanner(System.in);
        while (true) {
            double first = sc.nextDouble();
            //boolean second = sc.nextBoolean();
            nn.in.get(0).setValue((first));
            //nn.in.get(1).setValue(boolToInt(second));
            nn.calcAll(false);
            //System.out.println(intToBool((int) Math.round(nn.on.get(0).getValue())));
            System.out.println(nn.on.get(0).getValue());
        }
        

    }
}
