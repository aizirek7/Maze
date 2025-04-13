package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JPanel {
    private static final int CELL_SIZE = 25; // Size of each cell in pixels
    private int rows;
    private int cols;
    private int[][] maze; // Maze grid: 0 = wall, 1 = path
    private boolean[][] visited; // Tracks visited cells during pathfinding
    private boolean[][] path; // Stores the final path from start to end
    private Point start = new Point(0, 0); // Starting point of the maze
    private Point end; // Ending point of the maze

    // Constructor: Initializes the maze, generates it, and finds a solution
    public Main(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.end = new Point(rows - 1, cols - 1);
        this.maze = new int[rows][cols];
        this.visited = new boolean[rows][cols];
        this.path = new boolean[rows][cols];

        generateMaze(0, 0); // Recursive DFS maze generation
        resetVisited(); // Reset visited before solving
        findPath(start.x, start.y); // Find path from start to end
        setPreferredSize(new Dimension(cols * CELL_SIZE, rows * CELL_SIZE));
    }

    // Recursively generates the maze using DFS
    private void generateMaze(int r, int c) {
        maze[r][c] = 1; // Mark cell as open
        int[] dx = {0, 0, -1, 1}; // Directions: Left, Right, Up, Down
        int[] dy = {-1, 1, 0, 0};
        Integer[] dirs = {0, 1, 2, 3}; // Direction indices
        shuffle(dirs); // Shuffle directions for randomness

        // Explore neighbors in random order
        for (int dir : dirs) {
            int nr = r + dy[dir] * 2;
            int nc = c + dx[dir] * 2;
            if (inBounds(nr, nc) && maze[nr][nc] == 0) {
                maze[r + dy[dir]][c + dx[dir]] = 1; // Knock down wall between cells
                generateMaze(nr, nc); // Recursive call
            }
        }
    }

    // Randomly shuffles an array of integers (Fisher-Yates shuffle)
    private void shuffle(Integer[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // Resets the visited array before pathfinding
    private void resetVisited() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                visited[i][j] = false;
    }

    // Recursive backtracking pathfinding from (r, c) to the end
    private boolean findPath(int r, int c) {
        if (!inBounds(r, c) || maze[r][c] == 0 || visited[r][c])
            return false;

        visited[r][c] = true;

        if (r == end.x && c == end.y) {
            path[r][c] = true; // Mark path cell
            return true;
        }

        int[] dr = {0, 0, -2, 2};  // Move in 2-step directions (skip walls)
        int[] dc = {-2, 2, 0, 0};

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            int wallR = r + dr[i] / 2; // Wall between current and neighbor
            int wallC = c + dc[i] / 2;

            // Only proceed if neighbor and wall are paths and not visited
            if (inBounds(nr, nc) && maze[wallR][wallC] == 1 && maze[nr][nc] == 1 && !visited[nr][nc]) {
                if (findPath(nr, nc)) {
                    path[r][c] = true; // Mark path
                    return true;
                }
            }
        }

        return false; // No path found
    }

    // Checks if (r, c) is within the bounds of the maze
    private boolean inBounds(int r, int c) {
        return r >= 0 && c >= 0 && r < rows && c < cols;
    }

    // Renders the maze and the solution path
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw maze
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (maze[r][c] == 0) {
                    g.setColor(Color.BLACK); // Wall
                } else {
                    g.setColor(Color.WHITE); // Path
                }
                g.fillRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }

        // Draw the solution path in blue
        g.setColor(Color.BLUE);
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (path[r][c])
                    g.fillRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        // Draw start cell in green
        g.setColor(Color.GREEN);
        g.fillRect(start.y * CELL_SIZE, start.x * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        // Draw end cell in red
        g.setColor(Color.RED);
        g.fillRect(end.y * CELL_SIZE, end.x * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    // Entry point: prompts user for size and launches the GUI
    public static void main(String[] args) {
        int size = 21; // Default size
        boolean validInput = false;

        // Prompt user for input and validate
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
