package Elements;

import Main.SimPanel;

import java.awt.*;

public class Water extends Liquid{
    public Water(SimPanel panel, int elementType) {
        super(panel);
        this.setDefaults(elementType);
    }

    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {
        if(isFireCheck(grid,indexX - 1,indexY)){
            nextGrid[indexY][indexX - 1] = 7;
        }
        else if(isFireCheck(grid,indexX - 1,indexY - 1)){
            nextGrid[indexY - 1][indexX - 1] = 7;
        }
        else if(isFireCheck(grid,indexX,indexY - 1)){
            nextGrid[indexY - 1][indexX] = 7;
        }
        else if(isFireCheck(grid,indexX + 1,indexY - 1)){
            nextGrid[indexY - 1][indexX + 1] = 7;
        }
        else {
            super.action(grid,nextGrid,indexX,indexY);
        }

    }
    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        color = new Color(28,163,236);
        dispersalRate = 5;
    }
}
