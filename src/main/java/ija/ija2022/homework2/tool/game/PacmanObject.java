package ija.ija2022.homework2.tool.game;

import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.Field;
import ija.ija2022.homework2.tool.common.MazeObject;

public class PacmanObject implements MazeObject {
    private int row;

    private int col;

    private CommonMaze commonMaze;

    public PacmanObject(int row, int col, CommonMaze commonMaze) {
        this.row = row;
        this.col = col;
        this.commonMaze = commonMaze;
    }

    @Override
    public boolean canMove(Field.Direction dir) {
        int rK = dir == Field.Direction.U ? -1 : dir == Field.Direction.D ? 1 : 0;
        int cK = dir == Field.Direction.L ? -1 : dir == Field.Direction.R ? 1 : 0;

        Field nextField = this.commonMaze.getField(this.row + rK, this.col + cK);

        return nextField != null && nextField.canMove();
    }

    @Override
    public boolean move(Field.Direction dir) {
        boolean canMove = this.canMove(dir);

        if (canMove) {
            int rK = dir == Field.Direction.U ? -1 : dir == Field.Direction.D ? 1 : 0;
            int cK = dir == Field.Direction.L ? -1 : dir == Field.Direction.R ? 1 : 0;

            this.commonMaze.moveObject(this, this.row + rK, this.col + cK);

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
