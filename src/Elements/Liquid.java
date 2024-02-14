package Elements;

import Main.SimPanel;

public abstract class Liquid extends Element{
    public int dispersalRate;
    public Liquid(SimPanel panel) {
        super(panel);
    }

    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {

    }
}
