package ija.ija2022.homework2.tool.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

public class CommonMazeImpl implements CommonMaze {
    private final int rows;
    private final int cols;
    private final CommonField[][] commonFields;

    private final CommonMazeObject[][] objects;

    public CommonMazeImpl(int rows, int cols) {
        this.rows = rows + 2;
        this.cols = cols + 2;
        this.commonFields = new CommonField[this.rows][this.cols];
        this.objects = new CommonMazeObject[this.rows * this.cols][1];

        this.generateWalls();
    }

    private void generateWalls() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (i == 0 || i == this.rows - 1 || j == 0 || j == this.cols - 1) {
                    this.commonFields[i][j] = new WallCommonField(i, j);
                }
            }
        }
    }

    @Override
    public CommonField getField(int row, int col) {
        if (row < 0 || row >= this.rows)
            return null;

        if (col < 0 || col >= this.cols)
            return null;

        return this.commonFields[row][col];
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
    public void setField(int row, int col, CommonField commonField) {
        this.commonFields[row][col] = commonField;
    }

    @Override
    public CommonField[][] getFields() {
        return this.commonFields;
    }

    @Override
    public void putObject(CommonMazeObject object, int row, int col) {
        if (object == null)
            return;

        this.objects[row * this.cols + col][0] = object;
    }

    public void moveObject(CommonMazeObject object, int row, int col) {
        if (object == null)
            return;

        int oldRow = object.getRow();
        int oldCol = object.getCol();

        this.objects[oldRow * this.cols + oldCol][0] = null;
        this.objects[row * this.cols + col][0] = object;
    }

    @Override
    public CommonMazeObject[] getObjectsList(int row, int col) {
        return this.objects[row * this.cols + col];
    }

    public PacmanObjectCommon getPacman() {
        for (CommonMazeObject[] objectsCollection : this.objects) {
            for (CommonMazeObject object : objectsCollection) {
                if (object instanceof PacmanObjectCommon) {
                    return (PacmanObjectCommon) object;
                }
            }
        }

        return null;
    }

    @Override
    public void removeObject(int row, int col) {
        this.objects[row * this.cols + col] = null;
    }
}
