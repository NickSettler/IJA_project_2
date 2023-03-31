package ija.ija2022.homework2.tool.game;

import ija.ija2022.homework2.tool.common.Field;
import ija.ija2022.homework2.tool.common.MazeObject;

public class PathField extends BaseField implements Field {
    public PathField(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public MazeObject get() {
        if (this.isEmpty()) return null;

        return this.commonMaze.getObject(this.row, this.col);
    }

    @Override
    public boolean isEmpty() {
        MazeObject object = this.commonMaze.getObject(this.row, this.col);

        return object == null;
    }

    @Override
    public boolean put(MazeObject object) {
        if (object == null) return false;

        if (!this.isEmpty()) return false;

        this.commonMaze.putObject(object, this.row, this.col);

        return true;
    }

    @Override
    public boolean remove(MazeObject object) {
        if (object == null) return false;

        if (this.isEmpty()) return false;

        this.commonMaze.removeObject(object.getRow(), object.getCol());

        return true;
    }
}
