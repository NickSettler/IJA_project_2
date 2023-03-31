package ija.ija2022.homework2.tool.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

import java.util.Objects;

public class PacmanObjectCommon implements CommonMazeObject {
    private int row;

    private int col;

    private int lives;

    private final CommonMaze commonMaze;

    public PacmanObjectCommon(int row, int col, CommonMaze commonMaze) {
        this.row = row;
        this.col = col;
        this.commonMaze = commonMaze;
        this.lives = 3;
    }

    @Override
    public boolean canMove(CommonField.Direction dir) {
        CommonField nextCommonField = this.commonMaze.getField(this.row + dir.y(), this.col + dir.x());

        return nextCommonField != null && nextCommonField.canMove();
    }

    @Override
    public boolean move(CommonField.Direction dir) {
        boolean canMove = this.canMove(dir);

        if (!canMove) return false;

        this.commonMaze.moveObject(this, this.row + dir.y(), this.col + dir.x());

        this.row += dir.y();
        this.col += dir.x();

        CommonMazeObject[] objects = this.commonMaze.getObjectsList(this.row, this.col);
        for (CommonMazeObject commonMazeObject : objects) {
            if (commonMazeObject instanceof GhostObjectCommon) {
                this.lives -= 1;
                break;
            }
        }

        return true;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    @Override
    public boolean isPacman() {
        return true;
    }

    public int getLives() {
        return this.lives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PacmanObjectCommon that = (PacmanObjectCommon) o;

        if (getRow() != that.getRow()) return false;
        if (getCol() != that.getCol()) return false;
        if (lives != that.lives) return false;
        return Objects.equals(commonMaze, that.commonMaze);
    }

    @Override
    public int hashCode() {
        int result = getRow();
        result = 31 * result + getCol();
        result = 31 * result + lives;
        result = 31 * result + (commonMaze != null ? commonMaze.hashCode() : 0);
        return result;
    }
}
