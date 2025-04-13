package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JPanel {

    private int rows = 20;
    private int cols = 20;
    private int[][] maze = new int[rows][cols];

    public Main() {
        generateMaze(0, 0);
        setPreferredSize(new Dimension(cols * 25, rows * 25));
    }

    private void generateMaze(int r, int c) {
        maze[r][c] = 1;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        Integer[] dirs = {0, 1, 2, 3};
        shuffle(dirs);

        for (int dir : dirs) {
            int nr = r + dy[dir] * 2;
            int nc = c + dx[dir] * 2;
            if (inBounds(nr, nc) && maze[nr][nc] == 0) {
                maze[r + dy[dir]][c + dx[dir]] = 1;
                generateMaze(nr, nc);
            }
        }
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

    private boolean[][] visited = new boolean[rows][cols];
    private boolean[][] path = new boolean[rows][cols];

    private boolean findPath(int r, int c) {
        if (!inBounds(r, c) || maze[r][c] == 0 || visited[r][c])
            return false;

        visited[r][c] = true;

        if (r == rows - 1 && c == cols - 1) {
            path[r][c] = true;
            return true;
        }

        int[] dr = {0, 0, -1, 1};
        int[] dc = {-1, 1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (findPath(nr, nc)) {
                path[r][c] = true;
                return true;
            }
        }

        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (maze[r][c] == 0) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(c * 25, r * 25, 25, 25);
            }
        }

        g.setColor(Color.BLUE);
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (path[r][c])
                    g.fillRect(c * 25, r * 25, 25, 25);
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
