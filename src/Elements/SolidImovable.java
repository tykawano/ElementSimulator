package Elements;

import Main.SimPanel;

public abstract class SolidImovable extends Element{

    public SolidImovable(SimPanel panel) {
        super(panel);
    }

    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {
        nextGrid[indexY][indexX] = 2;
    }
}
