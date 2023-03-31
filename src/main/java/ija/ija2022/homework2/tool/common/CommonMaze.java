package ija.ija2022.homework2.tool.common;

public interface CommonMaze {
    Field getField(int row, int col);

    int numCols();

    int numRows();

    void setField(int row, int col, Field field);

    Field[][] getFields();

    void putObject(MazeObject object, int row, int col);

    void moveObject(MazeObject object, int row, int col);

    MazeObject getObject(int row, int col);

    void removeObject(int row, int col);
}
