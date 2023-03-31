package ija.ija2022.homework2.tool.game;

import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.Field;
import ija.ija2022.homework2.tool.common.MazeObject;

public class CommonMazeImpl implements CommonMaze {
    private int rows;
    private int cols;
    private Field[][] fields;

    private PacmanObject pacman;

    private MazeObject objects[];

    public CommonMazeImpl(int rows, int cols) {
        this.rows = rows + 2;
        this.cols = cols + 2;
        this.fields = new Field[this.rows][this.cols];
        this.objects = new MazeObject[this.rows * this.cols];

        this.generateWalls();
    }

    private void generateWalls() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (i == 0 || i == this.rows - 1 || j == 0 || j == this.cols - 1) {
                    this.fields[i][j] = new WallField(i, j);
                }
            }
        }
    }

    @Override
    public Field getField(int row, int col) {
        if (row < 0 || row >= this.rows)
            return null;

        if (col < 0 || col >= this.cols)
            return null;

        return this.fields[row][col];
    }

    @Override
    public int numCols() {
        return this.cols;
    }

    @Override
    public int numRows() {
        return this.rows;
    }

    @Override
    public void setField(int row, int col, Field field) {
        this.fields[row][col] = field;
    }

    @Override
    public Field[][] getFields() {
        return this.fields;
    }

    @Override
    public void putObject(MazeObject object, int row, int col) {
        if (object == null)
            return;

        this.objects[row * this.cols + col] = object;
    }

    public void moveObject(MazeObject object, int row, int col) {
        if (object == null)
            return;

        int oldRow = object.getRow();
        int oldCol = object.getCol();

        this.objects[oldRow * this.cols + oldCol] = null;
        this.objects[row * this.cols + col] = object;
    }

    @Override
    public MazeObject getObject(int row, int col) {
        return this.objects[row * this.cols + col];
    }

    @Override
    public void removeObject(int row, int col) {
        this.objects[row * this.cols + col] = null;
    }
}
