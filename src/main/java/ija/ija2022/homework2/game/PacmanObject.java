package ija.ija2022.homework2.game;

import ija.ija2022.homework2.common.Field;
import ija.ija2022.homework2.common.Maze;
import ija.ija2022.homework2.common.MazeObject;

public class PacmanObject implements MazeObject {
    private int row;

    private int col;

    private Maze maze;

    public PacmanObject(int row, int col, Maze maze) {
        this.row = row;
        this.col = col;
        this.maze = maze;
    }

    @Override
    public boolean canMove(Field.Direction dir) {
        int rK = dir == Field.Direction.U ? -1 : dir == Field.Direction.D ? 1 : 0;
        int cK = dir == Field.Direction.L ? -1 : dir == Field.Direction.R ? 1 : 0;

        Field nextField = this.maze.getField(this.row + rK, this.col + cK);

        return nextField != null && nextField.canMove();
    }

    @Override
    public boolean move(Field.Direction dir) {
        boolean canMove = this.canMove(dir);

        if (canMove) {
            int rK = dir == Field.Direction.U ? -1 : dir == Field.Direction.D ? 1 : 0;
            int cK = dir == Field.Direction.L ? -1 : dir == Field.Direction.R ? 1 : 0;

            this.maze.moveObject(this, this.row + rK, this.col + cK);

            this.row += rK;
            this.col += cK;

            return true;
        }

        return false;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}
