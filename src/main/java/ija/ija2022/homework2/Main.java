package ija.ija2022.homework2;

import ija.ija2022.homework2.common.Field;
import ija.ija2022.homework2.common.Maze;
import ija.ija2022.homework2.game.MazeConfigure;

public class Main {
    public static void main(String[] args) {
        MazeConfigure cfg = new MazeConfigure();
        cfg.startReading(4, 3);
        cfg.processLine("...");
        cfg.processLine(".X.");
        cfg.processLine(".X.");
        cfg.processLine(".S.");
        cfg.stopReading();

        Maze maze = cfg.createMaze();
        Field path = maze.getField(0, 0);
    }
}
