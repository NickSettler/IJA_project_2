package ija.ija2022.homework2.common;

public interface Field {
    enum Direction {
        D, L, R, U
    }

    boolean canMove();

    MazeObject get();

    boolean isEmpty();

    Field nextField(Field.Direction dirs);

    boolean put(MazeObject object);

    boolean remove(MazeObject object);

    void setMaze(Maze maze);
}
