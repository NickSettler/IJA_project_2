package ija.ija2022.homework2;

import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.Field;
import ija.ija2022.homework2.tool.game.MazeConfigure;

public class Main {
    public static void main(String[] args) {
        MazeConfigure cfg = new MazeConfigure();
        cfg.startReading(4, 3);
        cfg.processLine("...");
        cfg.processLine(".X.");
        cfg.processLine(".X.");
        cfg.processLine(".S.");
        cfg.stopReading();

        CommonMaze commonMaze = cfg.createMaze();
        Field path = commonMaze.getField(0, 0);
    }
}
