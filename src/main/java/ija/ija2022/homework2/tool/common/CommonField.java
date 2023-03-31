package ija.ija2022.homework2.tool.common;

public interface CommonField extends Observable {
    enum Direction {
        D(0, 1), L(-1, 0), R(1, 0), U(0, -1);

        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x() {
            return x;
        }

        public int y() {
            return y;
        }
    }

    boolean canMove();

    CommonMazeObject get();

    boolean isEmpty();

    CommonField nextField(CommonField.Direction dirs);

    boolean put(CommonMazeObject object);

    boolean remove(CommonMazeObject object);

    void setMaze(CommonMaze commonMaze);
}
