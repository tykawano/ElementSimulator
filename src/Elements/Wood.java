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
        if(isWoodCheck(grid,indexX,indexY) && (isWoodCheck(nextGrid,indexX,indexY) || isCellEmpty(nextGrid,indexX,indexY))){
            nextGrid[indexY][indexX] = elementType;
        }
    }


}
