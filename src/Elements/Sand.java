package Elements;

import Main.SimPanel;

import java.awt.*;

public class Sand extends Solid{
    public Sand( SimPanel panel,int elementType){
        super(panel);
        this.setDefaults(elementType);
    }
    @Override
    public void setDefaults(int elementType){
        this.elementType = elementType;
        color = new Color(194,178,128);
    }

    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {
        if(indexY + 1 >= panel.colNum){
            nextGrid[indexY][indexX] = 1;
        }
        else {
            int dir = 1;
            if(Math.random() < 0.5){
                dir *= -1;
            }
            if(isCellEmpty(grid,indexX,indexY + 1)){
                nextGrid[indexY + 1][indexX] = 1;
            }
            else if (isCellEmpty(grid,indexX - dir,indexY + 1)) {
                nextGrid[indexY + 1][indexX - dir] = 1;
            }
            else if(isCellEmpty(grid,indexX + dir,indexY + 1)){
                nextGrid[indexY + 1][indexX + dir] = 1;
            }
            else {
                nextGrid[indexY][indexX] = 1;
            }
        }
    }



}
