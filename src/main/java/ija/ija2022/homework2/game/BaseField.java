package ija.ija2022.homework2.game;

import ija.ija2022.homework2.common.Field;
import ija.ija2022.homework2.common.Maze;
import ija.ija2022.homework2.common.MazeObject;

import java.util.Objects;

public class BaseField implements Field {
    protected int row;

    protected int col;

    protected Maze maze;

    public BaseField(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public MazeObject get() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Field nextField(Direction dirs) {
        int rK = dirs == Direction.U ? -1 : dirs == Direction.D ? 1 : 0;
        int cK = dirs == Direction.L ? -1 : dirs == Direction.R ? 1 : 0;

        return this.maze.getField(this.row + rK, this.col + cK);
    }

    @Override
    public boolean put(MazeObject object) {
        return false;
    }

    @Override
    public boolean remove(MazeObject object) {
        return false;
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseField baseField = (BaseField) o;

        if (row != baseField.row) return false;
        return col == baseField.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, maze);
    }
}
