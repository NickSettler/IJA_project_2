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
        Field nextField = this.commonMaze.getField(this.row + dir.y(), this.col + dir.x());

        return nextField != null && nextField.canMove();
    }

    @Override
    public boolean move(Field.Direction dir) {
        boolean canMove = this.canMove(dir);

        if (canMove) {
            this.commonMaze.moveObject(this, this.row + dir.y(), this.col + dir.x());

            this.row += dir.y();
            this.col += dir.x();

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
