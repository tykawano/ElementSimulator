package Elements;

import Main.SimPanel;

import java.awt.*;

public class Wood extends Solid{

    public Wood(SimPanel panel, int elementType) {
        super(panel);
        this.setDefaults(elementType);
    }

    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {
        nextGrid[indexY][indexX] = 2;
    }

    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        this.color = new Color(150, 111, 51);
        this.isFlamable = true;
    }
}
