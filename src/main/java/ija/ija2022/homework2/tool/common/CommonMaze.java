package ija.ija2022.homework2.tool.common;

import ija.ija2022.homework2.tool.game.PacmanObjectCommon;

public interface CommonMaze {
    CommonField getField(int row, int col);

    int numCols();

    int numRows();

    void setField(int row, int col, CommonField commonField);

    CommonField[][] getFields();

    void putObject(CommonMazeObject object, int row, int col);

    void moveObject(CommonMazeObject object, int row, int col);

    CommonMazeObject[] getObjectsList(int row, int col);

    PacmanObjectCommon getPacman();

    void removeObject(int row, int col);
}
