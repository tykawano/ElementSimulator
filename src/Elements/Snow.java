package Elements;

import Main.SimPanel;

import java.awt.*;

public class Snow extends SolidMovable{
    public Snow(SimPanel panel,int elementType) {
        super(panel);
        this.setDefaults(elementType);
    }

    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        color = new Color(255, 250, 250);
    }
    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {
        if (indexY + 1 >= panel.colNum) {
            nextGrid[indexY][indexX] = 4;
        }
        else {
            boolean moveLeft = (Math.random() < 0.5);
            int dir;

            if (moveLeft) {
                dir = -1;
            }
            else {
                dir = 1;
            }

            // check if the cell bellow is empty
            if(isCellEmpty(grid, indexX, indexY + 1) && isCellEmpty(nextGrid,indexX,indexY + 1)){
                // Check if the cell diagonal right and left is empty
                if (isCellEmpty(grid, indexX + dir, indexY + 1) && isCellEmpty(nextGrid,indexX + dir,indexY + 1)) {
                    nextGrid[indexY + 1][indexX + dir] = 4; // Move diagonally
                }
                else if (isCellEmpty(grid, indexX - dir, indexY + 1) && isCellEmpty(nextGrid,indexX - dir,indexY + 1)) {
                    nextGrid[indexY + 1][indexX - dir] = 4; // Move diagonally in the opposite direction
                }
                // no other options move down
                else {
                    nextGrid[indexY + 1][indexX] = 4; // If no space below, solidify
                }
            }
            else {
                boolean countSolidsBelow = false;
                int columnPileUpThreshhold = 10;
                for (int i = 2; i < columnPileUpThreshhold + 1; i++) {
                    if(!isCellEmpty(grid,indexX,indexY + i) &&
                            (indexY + i < panel.colNum && indexY + i >= 0) && isCellSnow(grid,indexX,indexY + i)) {
                        countSolidsBelow = true;
                    }
                    else {
                        countSolidsBelow = false;
                        break;
                    }
                }

                if(countSolidsBelow){

                    if (isCellEmpty(grid, indexX + dir, indexY + 1) && isCellEmpty(nextGrid,indexX + dir,indexY + 1)) {
                        nextGrid[indexY + 1][indexX + dir] = 4; // Move diagonally
                    }
                    else if (isCellEmpty(grid, indexX - dir, indexY + 1) && isCellEmpty(nextGrid,indexX - dir,indexY + 1)) {
                        nextGrid[indexY + 1][indexX - dir] = 4; // Move diagonally in the opposite direction
                    }
                    else {
                        nextGrid[indexY][indexX] = 4;
                    }
                }
                else {
                    nextGrid[indexY][indexX] = 4;
                }

            }
        }

    }

    // checker for snow
    public boolean isCellSnow(int[][] grid,int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            return (grid[nextCol][nextRow] == 4);
        }
        return false;
    }

}
