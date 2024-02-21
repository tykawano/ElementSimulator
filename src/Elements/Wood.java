package Elements;

import Main.SimPanel;

import java.awt.*;

public class Wood extends SolidImmovable {

    public Wood(SimPanel panel, int elementType) {
        super(panel);
        this.setDefaults(elementType);
    }

    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        this.color = new Color(150, 111, 51);
    }

    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {
        if(isCellWood(grid,indexX,indexY) && (isCellWood(nextGrid,indexX,indexY) || isCellEmpty(nextGrid,indexX,indexY))){
            nextGrid[indexY][indexX] = 2;
        }
    }

    public boolean isCellWood(int[][] grid ,int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            return (grid[nextCol][nextRow] == 2);
        }
        return false;
    }
}
