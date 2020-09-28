/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.SwingUtilities.paintComponent;

/**
 *
 * @author tyler
 */
public class Graph extends JComponent {

    public Graph() {
        JFrame f = new JFrame("Graph Maker");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        //Inputs: text field and Draw button
        JPanel inputs = new JPanel();

        //button
        JButton button = new JButton("Draw");
        inputs.add(button);
        //Add the inputs panel to the main one
        mainPanel.add(inputs, BorderLayout.NORTH);
        //Add an area for functions' graphs
        //-----graphArea = new GraphComponent();
        //Add it to the main panel
        //-----mainPanel.add(graphArea);
        //Add the main panel to the default panel
        f.getContentPane().add(mainPanel);
        //Set automatically the best size for each element
        f.pack();
        //Set the frame visible
        f.setVisible(true);

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.white);
        int width = super.getWidth();
        int height = super.getHeight();
        g2d.clearRect(0, 0, width, height);
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.blue);
        g2d.drawLine(width / 2, 0, width / 2, height);
        g2d.drawLine(0, height / 2, width, height / 2);
        int unit = 10;
        for (int i = width / 2 + unit; i < width; i += unit) {
            g2d.drawLine(i, 0, i, height);
        }
        for (int i = width / 2 - unit; i > 0; i -= unit) {
            g2d.drawLine(i, 0, i, height);
        }
        for (int i = height / 2 + unit; i < height; i += unit) {
            g2d.drawLine(0, i, width, i);
        }
        for (int i = height / 2 - unit; i > 0; i -= unit) {
            g2d.drawLine(0, i, width, i);
        }
        for (int i = width / 2, i2 = 0; i < width; i += unit, i2++) {
            g2d.drawString(i2 + ",0", i + 5, height / 2 - 5);
        }
        for (int i = width / 2 - unit, i2 = -1; i > 0; i -= unit, i2--) {
            g2d.drawString(i2 + ",0", i + 5, height / 2 - 5);
        }
        for (int i = height / 2 + unit, i2 = -1; i < height; i += unit, i2--) {
            g2d.drawString("0," + i2, width / 2 + 5, i - 5);
        }
        for (int i = height / 2 - unit, i2 = 1; i > 0; i -= unit, i2++) {
            g2d.drawString("0," + i2, width / 2 + 5, i - 5);
        }
    }

    
    
    public static void main (String[] args) {
        Graph g = new Graph();
        //g.paintComponent(new Graphics());
    }
    
    
    
}
