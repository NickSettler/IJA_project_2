package ija.ija2022.homework2.tool.game;

import ija.ija2022.homework2.tool.common.CommonMazeObject;
import ija.ija2022.homework2.tool.common.IField;
import ija.ija2022.homework2.tool.common.IMazeObject;

import java.util.List;

public class PathField extends BaseField implements IField {
    public PathField(int row, int col) {
        super(row, col);
        this.object = null;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        List<CommonMazeObject> ghosts = this.commonMaze.ghosts();

        for (CommonMazeObject commonMazeObject : ghosts) {
            IMazeObject ghost = (IMazeObject) commonMazeObject;

            if (ghost == null) continue;

            if (ghost.getRow() == this.row && ghost.getCol() == this.col) return false;
        }

        PacmanObject pacman = this.commonMaze.getPacman();

        return pacman.getRow() != this.row || pacman.getCol() != this.col;
    }

    @Override
    public boolean put(IMazeObject object) {
        if (object == null) return false;

        if (!this.isEmpty()) return false;

        this.object = object;

        this.notifyObservers();

        return true;
    }

    @Override
    public boolean remove(IMazeObject object) {
        if (object == null) return false;

        if (this.isEmpty()) return false;

        this.object = null;

        this.notifyObservers();

        return true;
    }
}
