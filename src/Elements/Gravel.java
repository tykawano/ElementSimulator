package Elements;

import Main.SimPanel;

import java.awt.*;

public class Gravel extends SolidMovable{
    public Gravel(SimPanel panel, int elementType) {
        super(panel);
        this.setDefaults(elementType);
    }

    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        color = new Color(58,49,40);
    }

    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {

        if(isCellEmpty(grid,indexX,indexY + 1) && isCellEmpty(nextGrid,indexX,indexY + 1)){
            nextGrid[indexY + 1][indexX] = elementType;
        }
        else {
            nextGrid[indexY][indexX] = elementType;

        }
    }
}
