package ija.ija2022.homework2.tool.game;

import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

public class GhostObjectCommon implements CommonMazeObject {
    private int row;

    private int col;

    private CommonMaze commonMaze;

    public GhostObjectCommon(int row, int col, CommonMaze commonMaze) {
        this.row = row;
        this.col = col;
        this.commonMaze = commonMaze;
    }

    @Override
    public boolean canMove(CommonField.Direction dir) {
        CommonField nextCommonField = this.commonMaze.getField(this.row + dir.y(), this.col + dir.x());

        return nextCommonField != null && nextCommonField.canMove();
    }

    @Override
    public boolean move(CommonField.Direction dir) {
        boolean canMove = this.canMove(dir);

        if (canMove) {
            this.commonMaze.moveObject(this, this.row + dir.y(), this.col + dir.x());

            this.row += dir.y();
            this.col += dir.x();

            return true;
        }

        return false;
    }

    @Override
    public int getRow() {
        return this.row;
    }

    @Override
    public int getCol() {
        return this.col;
    }

    @Override
    public boolean isPacman() {
        return false;
    }

    @Override
    public int getLives() {
        return 0;
    }
}
