package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JPanel {
    private static final int CELL_SIZE = 25;
    private int rows;
    private int cols;
    private int[][] maze;
    private boolean[][] visited;
    private boolean[][] path;
    private Point start = new Point(0, 0);
    private Point end;

    public Main(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.end = new Point(rows - 1, cols - 1);
        this.maze = new int[rows][cols];
        this.visited = new boolean[rows][cols];
        this.path = new boolean[rows][cols];

        generateMaze(0, 0);
        resetVisited();
        findPath(start.x, start.y);
        setPreferredSize(new Dimension(cols * CELL_SIZE, rows * CELL_SIZE));
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

    private void shuffle(Integer[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private void resetVisited() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                visited[i][j] = false;
    }

    private boolean findPath(int r, int c) {
        if (!inBounds(r, c) || maze[r][c] == 0 || visited[r][c])
            return false;

        visited[r][c] = true;

        if (r == end.x && c == end.y) {
            path[r][c] = true;
            return true;
        }

        int[] dr = {0, 0, -2, 2};  // Move in steps of 2
        int[] dc = {-2, 2, 0, 0};

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            int wallR = r + dr[i] / 2;
            int wallC = c + dc[i] / 2;

            if (inBounds(nr, nc) && maze[wallR][wallC] == 1 && maze[nr][nc] == 1 && !visited[nr][nc]) {
                if (findPath(nr, nc)) {
                    path[r][c] = true;
                    return true;
                }
            }
        }

        return false;
    }

    private boolean inBounds(int r, int c) {
        return r >= 0 && c >= 0 && r < rows && c < cols;
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
                g.fillRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }

        // Draw solution path
        g.setColor(Color.BLUE);
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (path[r][c])
                    g.fillRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        // Start and End
        g.setColor(Color.GREEN);
        g.fillRect(start.y * CELL_SIZE, start.x * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        g.setColor(Color.RED);
        g.fillRect(end.y * CELL_SIZE, end.x * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public static void main(String[] args) {
        int size = 21; // Default size
        boolean validInput = false;

        while (!validInput) {
            String input = JOptionPane.showInputDialog("Enter maze size (odd number ≥ 5, e.g., 21 for 21x21):");
            if (input == null) {
                JOptionPane.showMessageDialog(null, "No input provided. Exiting.");
                System.exit(0);
            }

            try {
                size = Integer.parseInt(input.trim());
                if (size < 5) {
                    JOptionPane.showMessageDialog(null, "Size must be at least 5.");
                } else {
                    if (size % 2 == 0) {
                        size++; // Make it odd
                        JOptionPane.showMessageDialog(null, "Even number entered. Automatically increased to next odd number: " + size);
                    }
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a positive integer.");
            }
        }

        final int finalSize = size;
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Recursive Maze Solver");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Main(finalSize, finalSize));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
