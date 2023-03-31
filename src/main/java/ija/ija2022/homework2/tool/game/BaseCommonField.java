package ija.ija2022.homework2.tool.game;

import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

import java.util.ArrayList;
import java.util.Objects;

public class BaseCommonField implements CommonField {
    protected int row;

    protected int col;

    protected CommonMaze commonMaze;

    private ArrayList<Observer> observers = new ArrayList<>();

    public BaseCommonField(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public CommonMazeObject get() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public CommonField nextField(Direction dirs) {
        return this.commonMaze.getField(this.row + dirs.y(), this.col + dirs.x());
    }

    @Override
    public boolean put(CommonMazeObject object) {
        return false;
    }

    @Override
    public boolean remove(CommonMazeObject object) {
        return false;
    }

    @Override
    public void setMaze(CommonMaze commonMaze) {
        this.commonMaze = commonMaze;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseCommonField baseField = (BaseCommonField) o;

        if (row != baseField.row) return false;
        return col == baseField.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, commonMaze);
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update(this);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }
}
