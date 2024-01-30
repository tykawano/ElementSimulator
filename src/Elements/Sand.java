package Elements;

import Main.SimPanel;

import java.awt.*;

public class Sand extends Solid{
    public Sand( SimPanel panel,int elementType){
        super(panel);
        this.setDefaults(elementType);
    }
    public void setDefaults(int elementType){
        this.elementType = elementType;
        color = new Color(194,178,128);
    }


    @Override
    public int[][] action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {
        if(isCellEmpty(grid,indexX,indexY + 1)){
            nextGrid[indexY + 1][indexX] = 1;
        }
        else if (isCellEmpty(grid,indexX - 1,indexY + 1)) {
            nextGrid[indexY + 1][indexX - 1] = 1;
        }
        else if(isCellEmpty(grid,indexX + 1,indexY + 1)){
            nextGrid[indexY + 1][indexX + 1] = 1;
        }
        else {
            nextGrid = grid;
        }
        return nextGrid;
    }
}
