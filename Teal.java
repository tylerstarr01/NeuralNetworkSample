/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import neuralnetwork.camera.ImageInput;

/**
 *
 * @author user
 */
public class Teal {

    public ArrayList<InputNeuron> in;
    public ArrayList<MidNeuron> mn;
    public ArrayList<OutputNeuron> on;
    public InputWeight[] iw;
    public OutputWeight[] ow;
    public int id;
    public int numIn;
    public int numMn;
    public int numOn;
    public static double learningRate = 0.8;

    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public Teal(int numIn, int numMn, int numOn, double bias1, double bias2, double[] inputs, double[] targets) {
        this.numIn = numIn;
        this.numMn = numMn;
        this.numOn = numOn;

        this.in = new ArrayList<>();
        this.mn = new ArrayList<>();
        this.on = new ArrayList<>();
        this.iw = new InputWeight[numIn * numMn];
        this.ow = new OutputWeight[numMn * numOn];
        this.id = 0;
        double num = 0.0;
        for (int i = 0; i < numIn; i++) {
            in.add(new InputNeuron(inputs[i]));
        }
        for (int i = 0; i < numMn; i++) {
            mn.add(new MidNeuron(bias1));
        }
        for (int i = 0; i < numOn; i++) {
            on.add(new OutputNeuron(bias2, targets[i]));
        }
        int count = 0;
        for (int i = 0; i < numMn; i++) {
            for (int j = 0; j < numIn; j++) {
                iw[count] = new InputWeight(in.get(j), mn.get(i), num += 0.0001);
                count++;
            }
        }
        count = 0;
        for (int i = 0; i < numOn; i++) {
            for (int j = 0; j < numMn; j++) {
                ow[count] = new OutputWeight(mn.get(j), on.get(i), num += 0.0001);
                count++;
            }
        }

    }

    public Teal(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        int c = 0;

        //System.exit(0);
        sc.useDelimiter("r");
//        while (sc.hasNext()) {
//            sc.next();
//            c++;
//        }
        this.numIn = Integer.parseInt(sc.next());
        this.numMn = Integer.parseInt(sc.next());
        this.numOn = Integer.parseInt(sc.next());

        this.in = new ArrayList<>();
        this.mn = new ArrayList<>();
        this.on = new ArrayList<>();
        this.iw = new InputWeight[numIn * numMn];
        this.ow = new OutputWeight[numMn * numOn];
        this.id = 0;

        for (int i = 0; i < numIn; i++) {
            in.add(new InputNeuron(0));
        }
        for (int i = 0; i < numMn; i++) {
            mn.add(new MidNeuron(0.35));
        }
        for (int i = 0; i < numOn; i++) {
            on.add(new OutputNeuron(0.7, 0));
        }
        int count = 0;
        for (int i = 0; i < numMn; i++) {
            for (int j = 0; j < numIn; j++) {
                String s = sc.next();
                if (s.contains("\n")) {
                    //  continue;
                }
                System.out.println(s + " " + count);
                double d = Double.parseDouble(s);
                iw[count] = new InputWeight(in.get(j), mn.get(i), d);
                count++;
            }
        }

        System.out.println("\n\n\n\n\n");
        count = 0;
        for (int i = 0; i < numOn; i++) {
            for (int j = 0; j < numMn; j++) {
                String s = sc.next();
                if (s.contains("\n")) {
                    // continue;
                }
                System.out.println(s + " " + count);
                double d = Double.parseDouble(s);
                ow[count] = new OutputWeight(mn.get(j), on.get(i), d);
                count++;
            }
        }

    }

    public static double getError(double actual, double target) {
        return 100 * Math.abs(actual - target) / target;
    }

    public boolean isFinished() {
        double first = 0;
        double second = 0;
        double target;
        this.in.get(0).setValue(first);
        this.in.get(1).setValue(second);
        boolean fBoolean = first == 1;
        boolean sBoolean = second == 1;
        boolean tBoolean = fBoolean ^ sBoolean;
        target = tBoolean ? 1 : 0;
        this.on.get(0).setTarget(target);
        return true;
    }

    public void changeInputs(double[] values) {
        if (values.length != in.size()) {
            throw (new RuntimeException("That's the big No" + values.length + " " + in.size()));
        }
        for (int i = 0; i < values.length; i++) {
            in.get(i).setValue(values[i]);
        }
    }

    public void changeOutputs(double[] values) {
        if (values.length != on.size()) {
            throw (new RuntimeException("That's the big no " + values.length + on.size()));
        }
        for (int i = 0; i < values.length; i++) {
            on.get(i).setTarget(values[i]);
        }
    }

    public void storeData() throws IOException {
        String neuronData = this.numIn + "r" + this.numMn + "r" + this.numOn + "r";
        String inputWeightData = "";
        String outputWeightData = "";
        for (InputWeight w : this.iw) {
            inputWeightData += w.getValue() + "r";
        }
        for (OutputWeight w : this.ow) {
            outputWeightData += w.getValue() + "r";
        }
        ImageInput.convertToFile(neuronData + inputWeightData + "" + outputWeightData, "Desktop", "networkData1", ".txt");
    }

    public void generateTestCase() {
        int rand;
        int first;
        int second;
        int third;
        boolean fBoolean;
        boolean sBoolean;
        boolean thirBool;
        boolean tBoolean;
        double target = 0;
        rand = (int) (Math.random() * 2 + 0);
        first = rand;
        rand = (int) (Math.random() * 2 + 0);
        second = rand;
        rand = (int) (Math.random() * 2 + 0);
        third = rand;
        fBoolean = first == 1;
        sBoolean = second == 1;
        thirBool = third == 1;

        this.in.get(0).setValue(first);
        this.in.get(1).setValue(second);
        //this.in.get(21).setValue(third);
        tBoolean = fBoolean ^ sBoolean ^ thirBool;// && thirBool;
        if (tBoolean) {
            target = 1;
        } else {
            target = 0;
        }

        this.on.get(0).setTarget(target);
    }

    public void calcAll(boolean t) {
        for (MidNeuron mn1 : this.mn) {
            mn1.setValue(mn1.calculateValue());
        }

        for (OutputNeuron on1 : this.on) {
            on1.setValue(on1.calculateValue());
        }
        if (t) {
            calcChangeAll();
        }
    }

    public void calcChangeAll() {
        for (InputWeight iw1 : this.iw) {
            iw1.updateWeight(iw1.calcChange());
        }
        for (OutputWeight ow1 : this.ow) {
            ow1.updateWeight(ow1.calcChange());
        }
    }

    public double getLast() {
        return this.on.get(0).getValue();
    }

    public void run(int times) {
        int c = 0;
        while (c < times) {
            this.generateTestCase();
            this.calcAll(true);
            System.out.println(this.on.get(0).getTarget() + "  " + this.on.get(0).getValue());
            c++;
        }
    }

    public static void main(String[] args) throws IOException {
        Teal n = new Teal(2, 5, 1, 0.35, 0.6, new double[36], new double[2]); // numIn, numMn, numOn
        String fs = File.separator;
//        Teal n = new Teal(new File(System.getProperty("user.home") + fs + "Desktop" + fs + "networkData1.txt"));
        //n.run(3);
        //n.storeData();
        //System.exit(0);
        double b1 = 0.35;
        double b2 = 0.60;
        InputNeuron i1 = new InputNeuron(1);
        InputNeuron i2 = new InputNeuron(1);
        MidNeuron h1 = new MidNeuron(b1);
        MidNeuron h2 = new MidNeuron(b1);
        double targetOutputo1 = 0;
        double targetOutputo2 = 1;
        OutputNeuron o1 = new OutputNeuron(b2, targetOutputo1);
        OutputNeuron o2 = new OutputNeuron(b2, targetOutputo2);

        InputWeight w1 = new InputWeight(i1, h1, 0.1);
        InputWeight w2 = new InputWeight(i2, h1, 0.2);
        InputWeight w3 = new InputWeight(i1, h2, 0.3);
        InputWeight w4 = new InputWeight(i2, h2, 0.4);

        OutputWeight w5 = new OutputWeight(h1, o1, 0.5);
        OutputWeight w6 = new OutputWeight(h2, o1, 0.6);

        OutputWeight w7 = new OutputWeight(h1, o2, 0.7);
        OutputWeight w8 = new OutputWeight(h2, o2, 0.8);
        InputWeight[] iwArr = new InputWeight[]{w1, w2, w3, w4};
        OutputWeight[] owArr = new OutputWeight[]{w5, w6};
        double[] input = new double[36];
        for (int i = 0; i < input.length; i++) {
            input[i] = 0;
        }
        double[] output = new double[2];
        Teal nn = new Teal(36, 40, 2, b1, b2, input, output); // numIn, numMn, numOn
        int rand;
        int first;
        int second;
        int third;
        boolean fBoolean;
        boolean sBoolean;
        boolean thirBool;
        boolean tBoolean;
        double target = 0;
        int count = 0;
        double total = 100;
        double error = 100;

        //for (int k = 0; k < 500000; k++) ;
        int counter = 0;
        while (true) {
            ArrayList<Year> y = Year.readFile(new File("C:\\Users\\tyler\\OneDrive\\Desktop\\sportsref_download.csv"));
            Year currentYear = y.get(counter % y.size());
            //System.out.println("                     " + h1.getValue() + "  " + nn.mn.get(0).getValue() + " " + i1.getAffected() + " " + nn.in.get(0).getAffected());
            //System.out.println("                     " + h2.getAffected() + "           " + nn.mn.get(1).getAffected());
            //System.exit(0);

            rand = (int) (Math.random() * 2 + 0);
            first = rand;
            rand = (int) (Math.random() * 2 + 0);
            second = rand;
            rand = (int) (Math.random() * 2 + 0);
            third = rand;
            fBoolean = first == 1;
            sBoolean = second == 1;
            thirBool = third == 1;
            i1.setValue(first);
            i2.setValue(second);
            nn.in.get(0).setValue(currentYear.efg);
            nn.in.get(1).setValue(currentYear.win);
            nn.in.get(2).setValue(currentYear.mov);
            //nn.in.get(2).setValue(third);
            tBoolean = fBoolean ^ sBoolean;// && thirBool;
            if (tBoolean) {
                target = 1;
            } else {
                target = 0;
            }
            //target = 1.0 / (Math.pow(first, 2));
            o1.setTarget(target);
            nn.on.get(0).setTarget(target);

            nn.calcAll(true);
            h1.setValue(h1.calculateValue());
            h2.setValue(h2.calculateValue());

            o1.setValue(o1.calculateValue());
            o2.setValue(o2.calculateValue());
            //System.out.println(i1.getValue() + " " + i2.getValue() + " " + o1.getTarget() + " " + o1.getValue());// + " " + error);

            System.out.println(nn.on.get(0).getTarget() + " " + nn.on.get(0).getValue());

            for (int j = 0; j < iwArr.length; j++) {
                InputWeight curr = iwArr[j];
                curr.updateWeight(curr.calcChange());
            }
            for (int j = 0; j < owArr.length; j++) {
                OutputWeight curr = owArr[j];
                curr.updateWeight(curr.calcChange());
            }
        }

        /*
         for (int k = 0; k < 5000; k++) {
         rand = (int) (Math.random() * 2);
         first = rand;
         rand = (int) (Math.random() * 2);
         second = rand;
         fBoolean = first == 1;
         sBoolean = second == 1;
         i1.setValue(first);
         i2.setValue(second);
         nn.in.get(0).setValue(0);
         nn.in.get(1).setValue(1);
         tBoolean = fBoolean ^ sBoolean;
         if (tBoolean) {
         target = 1;
         } else {
         target = 0;
         }
         o1.setTarget(target);
         nn.on.get(0).setTarget(1);
         for (int i = 0; i < nn.mn.size(); i++) {
         nn.mn.get(i).setValue(nn.mn.get(i).calculateValue());
         }
         h1.setValue(h1.calculateValue());
         h2.setValue(h2.calculateValue());
         for (int i = 0; i < nn.on.size(); i++) {
         nn.on.get(i).setValue(nn.on.get(i).calculateValue());
         }
         o1.setValue(o1.calculateValue());
         o2.setValue(o2.calculateValue());
         //System.out.println(nn.in.get(0).getValue() + " " + nn.in.get(1).getValue() + " " + nn.on.get(0).getTarget() + " " + nn.on.get(0).getValue());
         System.out.println(o1.getTarget() + " " + o1.getValue());
         for (int e = 0; e < nn.iw.length; e++) {
         InputWeight curr = nn.iw[e];
         curr.updateWeight(curr.calcChange());
         }
         for (int i = 0; i < nn.ow.length; i++) {
         OutputWeight curr = nn.ow[i];
         curr.updateWeight(curr.calcChange());
         }
         for (int j = 0; j < iwArr.length; j++) {
         InputWeight curr = iwArr[j];
         curr.updateWeight(curr.calcChange());
         }
         for (int j = 0; j < owArr.length; j++) {
         OutputWeight curr = owArr[j];
         curr.updateWeight(curr.calcChange());
         }
         }
         for (int k = 0; k < 5000; k++) {
         rand = (int) (Math.random() * 2);
         first = rand;
         rand = (int) (Math.random() * 2);
         second = rand;
         fBoolean = first == 1;
         sBoolean = second == 0;
         i1.setValue(first);
         i2.setValue(second);
         nn.in.get(0).setValue(1);
         nn.in.get(1).setValue(0);
         tBoolean = fBoolean ^ sBoolean;
         if (tBoolean) {
         target = 1;
         } else {
         target = 0;
         }
         o1.setTarget(target);
         nn.on.get(0).setTarget(1);
         for (int i = 0; i < nn.mn.size(); i++) {
         nn.mn.get(i).setValue(nn.mn.get(i).calculateValue());
         }
         h1.setValue(h1.calculateValue());
         h2.setValue(h2.calculateValue());
         for (int i = 0; i < nn.on.size(); i++) {
         nn.on.get(i).setValue(nn.on.get(i).calculateValue());
         }
         o1.setValue(o1.calculateValue());
         o2.setValue(o2.calculateValue());
         //System.out.println(nn.in.get(0).getValue() + " " + nn.in.get(1).getValue() + " " + nn.on.get(0).getTarget() + " " + nn.on.get(0).getValue());
         //System.out.println(h1.getValue() + " " + h2.getValue());
         for (int e = 0; e < nn.iw.length; e++) {
         InputWeight curr = nn.iw[e];
         curr.updateWeight(curr.calcChange());
         }
         for (int i = 0; i < nn.ow.length; i++) {
         OutputWeight curr = nn.ow[i];
         curr.updateWeight(curr.calcChange());
         }
         for (int j = 0; j < iwArr.length; j++) {
         InputWeight curr = iwArr[j];
         curr.updateWeight(curr.calcChange());
         }
         for (int j = 0; j < owArr.length; j++) {
         OutputWeight curr = owArr[j];
         curr.updateWeight(curr.calcChange());
         }
         }
         for (int k = 0; k < 1; k++) {
         rand = (int) (Math.random() * 2);
         first = rand;
         rand = (int) (Math.random() * 2);
         second = rand;
         fBoolean = first == 1;
         sBoolean = second == 1;
         i1.setValue(first);
         i2.setValue(second);
         nn.in.get(0).setValue(0);
         nn.in.get(1).setValue(0);
         tBoolean = fBoolean ^ sBoolean;
         if (tBoolean) {
         target = 1;
         } else {
         target = 0;
         }
         o1.setTarget(0);
         nn.on.get(0).setTarget(0);
         for (int i = 0; i < nn.mn.size(); i++) {
         nn.mn.get(i).setValue(nn.mn.get(i).calculateValue());
         }
         h1.setValue(h1.calculateValue());
         h2.setValue(h2.calculateValue());
         for (int i = 0; i < nn.on.size(); i++) {
         nn.on.get(i).setValue(nn.on.get(i).calculateValue());
         }
         o1.setValue(o1.calculateValue());
         o2.setValue(o2.calculateValue());
         //            for (int i = 0; i < iwArr.length; i++) {
         //                System.out.println("   " + iwArr[i].getValue() + " " + iwArr[i].getStartingNeuron().id + " " + iwArr[i].getEndingNeuron().id);
         //            }
         //            for (int i = 0; i < owArr.length; i++) {
         //                Z.out.println("   " + owArr[i].getValue() + " " + owArr[i].getStartingNeuron().id + " " + owArr[i].getEndingNeuron().id);
         //            }
         ////System.out.println(nn.in.get(0).getValue() + " " + nn.in.get(1).getValue() + " " + nn.on.get(0).getTarget() + " " + nn.on.get(0).getValue());
         for (int e = 0; e < nn.iw.length; e++) {
         InputWeight curr = nn.iw[e];
         curr.updateWeight(curr.calcChange());
         }
         for (int i = 0; i < nn.ow.length; i++) {
         OutputWeight curr = nn.ow[i];
         curr.updateWeight(curr.calcChange());
         }
         for (int j = 0; j < iwArr.length; j++) {
         InputWeight curr = iwArr[j];
         curr.updateWeight(curr.calcChange());
         }
         for (int j = 0; j < owArr.length; j++) {
         OutputWeight curr = owArr[j];
         curr.updateWeight(curr.calcChange());
         }
         }
         nn.in.get(0).setValue(0);
         nn.in.get(1).setValue(0);
         nn.on.get(0).setTarget(0);
         System.out.println();
         System.out.println();
         System.out.println();
         System.out.println();*/
    }
}
