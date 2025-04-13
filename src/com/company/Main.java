package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JPanel {

    private int rows = 20;
    private int cols = 20;
    private int[][] maze = new int[rows][cols];

    public Main() {
        setPreferredSize(new Dimension(cols * 25, rows * 25));
    }

    private boolean inBounds(int r, int c) {
        return r >= 0 && c >= 0 && r < rows && c < cols;
    }

    private void shuffle(Integer[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Recursive Maze Solver");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Main());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
