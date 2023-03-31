package ija.ija2022.homework2;

import ija.ija2022.homework2.tool.MazePresenter;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonField;
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

        MazePresenter presenter = new MazePresenter(commonMaze);
        presenter.open();

        commonMaze.getPacman().move(CommonField.Direction.R);
        commonMaze.getPacman().move(CommonField.Direction.U);
    }
}
