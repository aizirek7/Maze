
---

# 🧩 Recursive Maze Generator & Solver (Java + Swing)

This Java application generates a **random maze** using **recursive backtracking (DFS)** and solves it using a pathfinding algorithm. The maze and its solution are displayed using **Java Swing**.

---

## 📸 Preview


![image](https://github.com/user-attachments/assets/440f2571-a291-464d-814d-6632813ae297)

---

## 🚀 Features

- Generates a **random perfect maze** (only one path between any two points).
- Uses **Depth-First Search (DFS)** for both maze generation and solving.
- Visualizes the maze with:
  - **Walls**: `black`
  - **Paths**: `white`
  - **Start point**: `green`
  - **End point**: `red`
  - **Solution path**: `blue`
- Includes **input validation** to ensure a custom maze size is odd and meets the minimum size requirement.

---

## 📥 Input

When the program runs, it prompts the user to enter the **maze size**. 

### 🔢 Input Requirements:
- **Minimum size**: `5`
- **Must be an odd number** (even numbers are automatically rounded up to the next odd number)

### ✅ Examples of valid inputs:
- `21` → valid
- `20` → adjusted to `21` automatically
- `3` → rejected (too small)
- `abc` → rejected (not a number)

- ![image](https://github.com/user-attachments/assets/936e784a-cba8-44eb-bce7-119c17241133)


---

## 📤 Output

- A graphical window displays:
  - The generated **random maze**.
  - The **solution path** from the top-left corner to the bottom-right.
- The terminal/console will display error messages only; the main output is graphical.

---

## 🧑‍💻 How to Run

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/maze-solver-java.git
   cd maze-solver-java
   ```

2. **Compile the Java file**:
   ```bash
   javac Main.java
   ```

3. **Run the program**:
   ```bash
   java Main
   ```

4. **Input the maze size** when prompted.

---

## 🛠 Dependencies

- Java 8 or higher
- No external libraries — the project uses **Java Swing** and **Java AWT** for the GUI.

---


---

## 📌 Notes

- The maze will always be a square grid.
- The program works best with **odd-numbered sizes**, e.g., `21x21`, `31x31`, etc.
- The maze is generated randomly each time the program is run.
  

---

## 🙌 Acknowledgements

- Maze generation algorithm based on **Depth-First Search (DFS)**.
- **Java Swing** used for graphical display of the maze and solution.

---
