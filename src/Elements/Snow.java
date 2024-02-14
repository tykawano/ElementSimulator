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
        } else {
            boolean moveLeft = (Math.random() < 0.5);
            int dir;

            if (moveLeft) {
                dir = -1;
            }
            else {
                dir = 1;
            }

            // check if the cell bellow is empty
            if(isCellEmpty(grid, indexX, indexY + 1)){
                // Check if the cell diagonal right and left is empty
                if (isCellEmpty(grid, indexX + dir, indexY + 1)) {
                    nextGrid[indexY + 1][indexX + dir] = 4; // Move diagonally
                }
                else if (isCellEmpty(grid, indexX - dir, indexY + 1)) {
                    nextGrid[indexY + 1][indexX - dir] = 4; // Move diagonally in the opposite direction
                }
                // no other options move down
                else {
                    nextGrid[indexY + 1][indexX] = 4; // If no space below, solidify
                }
            }
            else {
                nextGrid[indexY][indexX] = 4;
            }
        }

    }

}
