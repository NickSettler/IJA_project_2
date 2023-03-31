package ija.ija2022.homework2.tool.game;

import ija.ija2022.homework2.tool.common.Field;
import ija.ija2022.homework2.tool.common.MazeObject;

public class WallField extends BaseField implements Field {
    public WallField(int row, int col) {
        super(row, col);
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
        return true;
    }

    @Override
    public boolean put(MazeObject object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(MazeObject object) {
        throw new UnsupportedOperationException();
    }
}
