package ija.ija2022.homework2.tool.game;

import ija.ija2022.homework2.tool.common.Field;
import ija.ija2022.homework2.tool.common.CommonMaze;

public class MazeConfigure {
    private boolean reading;

    private int rowCounter;

    private CommonMaze commonMaze;

    public MazeConfigure() {
        this.reading = false;
        this.rowCounter = 1;
        this.commonMaze = null;
    }

    private boolean checkLine(String line) {
        if (line == null) return false;

        if (line.length() != this.commonMaze.numCols() - 2) return false;

        return line.matches("^[.XS]+$");
    }

    public CommonMaze createMaze() {
        if (this.reading) {
            return null;
        }

        if (this.commonMaze.getFields() == null) {
            return null;
        }

        if (this.rowCounter != this.commonMaze.numRows()) {
            return null;
        }

        return this.commonMaze;
    }

    public boolean processLine(String line) {
        if (!this.reading) {
            return false;
        }

        if (!this.checkLine(line)) {
            return false;
        }

        if (this.rowCounter >= this.commonMaze.numRows()) {
            this.rowCounter++;
            return false;
        }

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);

            Field field = ch == 'X' ? new WallField(this.rowCounter, i + 1) : new PathField(this.rowCounter, i + 1);

            if (ch == 'S')
                this.commonMaze.putObject(new PacmanObject(this.rowCounter, i + 1, this.commonMaze), this.rowCounter, i + 1);
            this.commonMaze.setField(this.rowCounter, i + 1, field);
            field.setMaze(this.commonMaze);
        }

        this.rowCounter++;

        return true;
    }

    public void startReading(int rows, int cols) {
        if (this.reading) {
            return;
        }

        if (this.commonMaze != null && this.commonMaze.getFields() != null) {
            return;
        }

        if (rows <= 0 || cols <= 0) {
            return;
        }

        this.reading = true;
        this.rowCounter = 1;
        this.commonMaze = new CommonMazeImpl(rows, cols);
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
