package ija.ija2022.homework2.tool.common;

public interface CommonMazeObject {
    boolean canMove(CommonField.Direction dir);

    boolean move(CommonField.Direction dir);

    int getRow();

    int getCol();

    boolean isPacman();

    public int getLives();
}
