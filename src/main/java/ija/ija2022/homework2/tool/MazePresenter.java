package ija.ija2022.homework2.tool;

import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.Field;
import ija.ija2022.homework2.tool.common.Observable;
import ija.ija2022.homework2.tool.game.PacmanObject;
import ija.ija2022.homework2.tool.game.PathField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class MazePresenter extends JPanel implements Observable.Observer {
    private static final int CELL_SIZE = 40;


    private final int WIDTH;
    private final int HEIGHT;

    private final int RECT_X = CELL_SIZE;
    private final int RECT_Y = CELL_SIZE;
    private final int RECT_WIDTH;
    private final int RECT_HEIGHT;

    private final CommonMaze commonMaze;

    public MazePresenter(CommonMaze commonMaze) {
        this.commonMaze = commonMaze;

        this.RECT_WIDTH = commonMaze.numCols() * CELL_SIZE;
        this.RECT_HEIGHT = commonMaze.numRows() * CELL_SIZE;

        this.WIDTH = RECT_WIDTH + 2 * RECT_X;
        this.HEIGHT = RECT_HEIGHT + 2 * RECT_Y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);

        this.drawFields(g);
        this.drawPacman(g);
    }

    private void drawPacman(@NotNull Graphics g) {
        PacmanObject pacman = this.commonMaze.getPacman();

        int x = pacman.getCol() * CELL_SIZE + RECT_X;
        int y = pacman.getRow() * CELL_SIZE + RECT_Y;

        g.setColor(Color.YELLOW);
        g.fillArc(x, y, CELL_SIZE, CELL_SIZE, 0, 360);
    }

    public void drawFields(@NotNull Graphics g) {
        for (int i = 0; i < this.commonMaze.numRows(); i++) {
            for (int j = 0; j < this.commonMaze.numCols(); j++) {
                Field field = this.commonMaze.getField(i, j);

                if (field == null) continue;

                int x = j * CELL_SIZE + RECT_X;
                int y = i * CELL_SIZE + RECT_Y;

                Color color = field instanceof PathField ? Color.CYAN.darker() : Color.BLACK;

                g.setColor(color);
                g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    public void open() {
        MazePresenter mazePresenter = new MazePresenter(this.commonMaze);

        JFrame frame = new JFrame("Maze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mazePresenter);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void render() {

    }

    @Override
    public void update(Observable o) {

    }
}
