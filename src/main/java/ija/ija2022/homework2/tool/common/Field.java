package ija.ija2022.homework2.tool.common;

public interface Field {
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

    MazeObject get();

    boolean isEmpty();

    Field nextField(Field.Direction dirs);

    boolean put(MazeObject object);

    boolean remove(MazeObject object);

    void setMaze(CommonMaze commonMaze);
}
