package ija.ija2022.homework2.tool.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

public class PathCommonField extends BaseCommonField implements CommonField {
    public PathCommonField(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public CommonMazeObject get() {
        if (this.isEmpty()) return null;

        return this.commonMaze.getObjectsList(this.row, this.col)[0];
    }

    @Override
    public boolean isEmpty() {
        CommonMazeObject object = this.commonMaze.getObjectsList(this.row, this.col)[0];

        return object == null;
    }

    @Override
    public boolean put(CommonMazeObject object) {
        if (object == null) return false;

        if (!this.isEmpty()) return false;

        this.commonMaze.putObject(object, this.row, this.col);

        return true;
    }

    @Override
    public boolean remove(CommonMazeObject object) {
        if (object == null) return false;

        if (this.isEmpty()) return false;

        this.commonMaze.removeObject(object.getRow(), object.getCol());

        return true;
    }
}
