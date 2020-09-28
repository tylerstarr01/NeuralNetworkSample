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
import java.util.Scanner;

/**
 *
 * @author tyler
 */
public class Year {

    public String team;
    public double win;
    public double threePointPer;
    public double freeThrowPer;
    public double twoPoint;
    public double threePoint;
    public double freeThrow;
    public double reb;
    public double ast;
    public double pts;
    public double mov;
    public double sos;
    public double srs;
    public double pace;
    public double ortg;
    public double drtg;
    public double efg;
    public double tov;
    public double orb;
    public double oefg;
    public double otov;
    public double oorb;

    public Year(String team, double[] info) {
        int i = 0;
        this.team = team;
        win = info[i++];
        threePointPer = info[i++];
        freeThrowPer = info[i++];
        twoPoint = info[i++];
        threePoint = info[i++];
        freeThrow = info[i++];
        reb = info[i++];
        ast = info[i++];
        pts = info[i++];
        mov = info[i++];
        sos = info[i++];
        srs = info[i++];
        pace = info[i++];
        ortg = info[i++];
        drtg = info[i++];
        efg = info[i++];
        tov = info[i++];
        orb = info[i++];
        oefg = info[i++];
        otov = info[i++];
        oorb = info[i++];
    }

    public static ArrayList<Year> readFile(File file) throws IOException {
        Scanner sc = new Scanner(file);
        sc.useDelimiter(",|\\n");
        for (int c = 0; c < 22; c++) {
            sc.next();
        }
        ArrayList<Year> allYears = new ArrayList<>();
        String curr = "";
        double[] stats = new double[21];
        while (sc.hasNext()) {
            curr = sc.next().substring(0, 3);
            for (int i = 0; i < 21; i++) {
                stats[i] = Double.parseDouble(sc.next());
            }
            if (curr.equals("TOR") && stats[0] == 0.707 || curr.equals("MIL") && stats[0] == 0.732 || curr.equals("PHI") && stats[0] == 0.622 || curr.equals("BOS") && stats[0] == 0.598
                    || curr.equals("GSW") && stats[0] == 0.695 || curr.equals("DEN") && stats[0] == 0.659 || curr.equals("HOU") && stats[0] == 0.646) {
                continue;

            }

            allYears.add(new Year(curr, stats));
            //System.out.println(curr);
        }
        return allYears;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Year> y = readFile(new File("C:\\Users\\tyler\\OneDrive\\Desktop\\sportsref_download.csv"));
        System.out.println(y.size());

    }
}
