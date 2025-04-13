
---

```markdown
# 🧩 Recursive Maze Generator & Solver (Java + Swing)

This Java application generates a **random maze** using recursive backtracking (DFS) and then solves it using a pathfinding algorithm. The maze and its solution are displayed using **Java Swing**.

---

## 📸 Preview

![Maze Screenshot](screenshots/sample-maze.png) <!-- Replace with your actual image path -->

---

## 🚀 Features

- Generates a random **perfect maze** (only one path between any two points).
- Uses **Depth-First Search (DFS)** for both generation and solving.
- Visualizes:
  - Walls (`black`)
  - Paths (`white`)
  - Start point (`green`)
  - End point (`red`)
  - Solution path (`blue`)
- Input validation for custom maze sizes.

---

## 📥 Input

The program prompts the user to enter the **maze size** on startup.

### 🔢 Requirements:
- **Minimum size**: `5`
- **Must be an odd number** (even numbers are automatically rounded up)

### ✅ Examples:
- `21` → valid
- `20` → adjusted to `21` automatically
- `3` → rejected (too small)
- `abc` → rejected (not a number)

---

## 📤 Output

- A visual window displaying:
  - The generated maze
  - The **solution path** from the top-left corner to the bottom-right
- Terminal logs only show error messages or prompts (UI is mostly graphical).

---

## 🧑‍💻 How to Run

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/maze-solver-java.git
   cd maze-solver-java
   ```

2. **Compile the program**:
   ```bash
   javac Main.java
   ```

3. **Run the program**:
   ```bash
   java Main
   ```

4. **Enter maze size** in the popup window.

---

## 🛠 Dependencies

- Java 8 or higher
- No external libraries — uses only standard `javax.swing` and `java.awt`.

---

## 📂 File Structure

```
.
├── Main.java
├── README.md
└── screenshots/
    └── sample-maze.png (optional - add your own)
```

---

## 📌 Notes

- Works best with square mazes like `21x21`, `31x31`, etc.
- Maze generation is random on each run.

---

## 📃 License

This project is open-source and free to use under the MIT License.

---

## 🙌 Acknowledgements

- Maze generation logic inspired by classic DFS algorithms.
- UI built with Java Swing for simplicity.

```

