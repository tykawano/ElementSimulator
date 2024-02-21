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
            int dir = 1;
            if(Math.random() < 0.5){
                dir *= -1;
            }

            if(isCellEmpty(grid,indexX,indexY + 1) && isCellEmpty(nextGrid,indexX,indexY + 1)){
                nextGrid[indexY + 1][indexX] = elementType;
            }
            else if (isCellEmpty(grid,indexX - dir,indexY + 1) && isCellEmpty(nextGrid,indexX - dir,indexY + 1)) {
                nextGrid[indexY + 1][indexX - dir] = elementType;
            }
            else if (isCellEmpty(grid,indexX + dir,indexY + 1) && isCellEmpty(nextGrid,indexX + dir,indexY + 1)) {
                nextGrid[indexY + 1][indexX + dir] = elementType;
            }
            else if (isCellEmpty(grid,indexX - dir,indexY) && isCellEmpty(nextGrid,indexX - dir,indexY)) {
                grid[indexY][indexX] = 0;
                nextGrid[indexY][indexX - dir] = elementType;
            }
            else if(isCellEmpty(grid,indexX + dir,indexY) && isCellEmpty(nextGrid,indexX + dir,indexY)){
                grid[indexY][indexX] = 0;
                nextGrid[indexY][indexX + dir] = elementType;
            }
            else {
                if(isSand(grid,indexX,indexY - 1)){
                    switchElements(grid,indexX,indexY,indexX,indexY - 1,nextGrid);
                }
                else {
                    nextGrid[indexY][indexX] = elementType;
                }

            }
        }

    }

    public boolean isFireCheck(int[][] grid ,int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            return grid[nextCol][nextRow] == 6;
        }
        return false;
    }
    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        color = new Color(28,163,236);
        dispersalRate = 5;
    }
}
