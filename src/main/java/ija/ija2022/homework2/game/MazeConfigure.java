package ija.ija2022.homework2.game;

import ija.ija2022.homework2.common.Field;
import ija.ija2022.homework2.common.Maze;

public class MazeConfigure {
    private boolean reading;

    private int rowCounter;

    private Maze maze;

    public MazeConfigure() {
        this.reading = false;
        this.rowCounter = 1;
        this.maze = null;
    }

    private boolean checkLine(String line) {
        if (line == null) return false;

        if (line.length() != this.maze.numCols() - 2) return false;

        return line.matches("^[.XS]+$");
    }

    public Maze createMaze() {
        if (this.reading) {
            return null;
        }

        if (this.maze.getFields() == null) {
            return null;
        }

        if (this.rowCounter != this.maze.numRows()) {
            return null;
        }

        return this.maze;
    }

    public boolean processLine(String line) {
        if (!this.reading) {
            return false;
        }

        if (!this.checkLine(line)) {
            return false;
        }

        if (this.rowCounter >= this.maze.numRows()) {
            this.rowCounter++;
            return false;
        }

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);

            Field field = ch == 'X' ? new WallField(this.rowCounter, i + 1) : new PathField(this.rowCounter, i + 1);

            if (ch == 'S')
                this.maze.putObject(new PacmanObject(this.rowCounter, i + 1, this.maze), this.rowCounter, i + 1);
            this.maze.setField(this.rowCounter, i + 1, field);
            field.setMaze(this.maze);
        }

        this.rowCounter++;

        return true;
    }

    public void startReading(int rows, int cols) {
        if (this.reading) {
            return;
        }

        if (this.maze != null && this.maze.getFields() != null) {
            return;
        }

        if (rows <= 0 || cols <= 0) {
            return;
        }

        this.reading = true;
        this.rowCounter = 1;
        this.maze = new MazeImpl(rows, cols);
    }

    public boolean stopReading() {
        if (this.reading) {
            this.rowCounter++;
            this.reading = false;
            return true;
        }

        return false;
    }
}
