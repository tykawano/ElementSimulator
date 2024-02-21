package Elements;

import Main.SimPanel;

import java.awt.*;

public class Fire extends Plasma{
    public Fire(SimPanel panel, int elementType) {
        super(panel);
        this.setDefaults(elementType);
    }

    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {
        int random;
        int count = 0;
        int oddsOfigniting = 15;

        // top left check
        if(flammableElementCheck(grid,indexX - 1,indexY - 1)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY - 1][indexX - 1] = 6;
            }
            count += 1;
        }

        // top middle check
        if(flammableElementCheck(grid,indexX,indexY - 1)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY - 1][indexX] = 6;
            }
            count += 1;
        }

        // top right check
        if(flammableElementCheck(grid,indexX + 1,indexY - 1)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY - 1][indexX + 1] = 6;
            }
            count += 1;
        }

        // right middle check
        if(flammableElementCheck(grid,indexX + 1,indexY)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY][indexX + 1] = 6;
            }
            count += 1;
        }

        // left middle check
        if(flammableElementCheck(grid,indexX - 1,indexY)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY][indexX - 1] = 6;
            }
            count += 1;
        }

        // bottom right check
        if(flammableElementCheck(grid,indexX + 1,indexY + 1)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY + 1][indexX + 1] = 6;
            }
            count += 1;
        }

        // bottom middle check
        if(flammableElementCheck(grid,indexX,indexY + 1)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY + 1][indexX] = 6;
            }
            count += 1;
        }

        // bottom left check
        if(flammableElementCheck(grid,indexX - 1,indexY + 1)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY + 1][indexX - 1] = 6;
            }
            count += 1;
        }

        if(count == 0){
            random = (int) (Math.random()*4)+ 1;
            if(random == 1){
                nextGrid[indexY][indexX] = 5;
            }
            else {
                nextGrid[indexY][indexX] = 6;
            }
        }
        else {
            nextGrid[indexY][indexX] = 6;
        }
    }

    public boolean flammableElementCheck(int[][] grid ,int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            int elementType = grid[nextCol][nextRow];
            return isFlamable[elementType];
        }
        return false;
    }

    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        color = new Color(255,90,0);
    }
}
