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
        int random;
        int chanceWaterSoluble = 5;
        boolean turnedIntoWater = false;

        // check if any tile 3 tiles left, right, top, bottom around snow tile are water
        boolean leftCheck = isWaterCheck(grid,indexX - 1,indexY + 1) &&
                isWaterCheck(grid,indexX - 1,indexY) &&
                isWaterCheck(grid,indexX - 1,indexY - 1);

        boolean topCheck = isWaterCheck(grid,indexX - 1,indexY - 1) &&
                isWaterCheck(grid,indexX,indexY - 1) &&
                isWaterCheck(grid,indexX + 1,indexY - 1);

        boolean rightCheck = isWaterCheck(grid,indexX + 1,indexY - 1) &&
                isWaterCheck(grid,indexX + 1,indexY) &&
                isWaterCheck(grid,indexX + 1,indexY + 1);

        boolean bottomCheck = isWaterCheck(grid,indexX - 1, indexY + 1) &&
                isWaterCheck(grid,indexX,indexY + 1) &&
                isWaterCheck(grid,indexX + 1,indexY + 1);

        boolean edgeTopLeft = isWaterCheck(grid,indexX,indexY - 1) &&
                isWaterCheck(grid,indexX + 1,indexY - 1);

        boolean edgeTopRight = isWaterCheck(grid,indexX - 1,indexY - 1) &&
                isWaterCheck(grid,indexX,indexY - 1);

        boolean edgeBottomLeft = isWaterCheck(grid,indexX,indexY + 1) &&
                isWaterCheck(grid,indexX + 1,indexY + 1);

        boolean edgeBottomRight = isWaterCheck(grid,indexX - 1, indexY + 1) &&
                isWaterCheck(grid,indexX,indexY + 1);

        boolean edgeRight = isWaterCheck(grid,indexX + 1,indexY - 1) &&
                isWaterCheck(grid,indexX + 1,indexY);


        boolean edgeLeft = isWaterCheck(grid,indexX - 1,indexY) &&
                isWaterCheck(grid,indexX - 1,indexY - 1);

        if(leftCheck || topCheck || rightCheck || bottomCheck){
            random = (int) (Math.random()*chanceWaterSoluble) + 1;
            if(random == 1){
                nextGrid[indexY][indexX] = 3;
                turnedIntoWater = true;
            }
            else {
                nextGrid[indexY][indexX] = elementType;
            }
        }
        else if ((indexX == 0 && indexY == panel.colNum - 1) && (edgeTopLeft || edgeRight)) {
            random = (int) (Math.random()*chanceWaterSoluble) + 1;
            if(random == 1){
                nextGrid[indexY][indexX] = 3;
                turnedIntoWater = true;
            }
            else {
                nextGrid[indexY][indexX] = elementType;
            }
        }
        else if((indexX == panel.rowNum - 1 && indexY == panel.colNum - 1) && (edgeTopRight || edgeLeft)){
            random = (int) (Math.random()*chanceWaterSoluble) + 1;
            if(random == 1){
                nextGrid[indexY][indexX] = 3;
                turnedIntoWater = true;
            }
            else {
                nextGrid[indexY][indexX] = elementType;
            }
        }
        else if ((indexX == 0 && indexY < panel.colNum - 1 && indexY > 0) && (edgeTopLeft || edgeBottomLeft || edgeRight)) {
            random = (int) (Math.random()*chanceWaterSoluble) + 1;
            if(random == 1){
                nextGrid[indexY][indexX] = 3;
                turnedIntoWater = true;
            }
            else {
                nextGrid[indexY][indexX] = elementType;
            }
        }
        else if ((indexX == panel.rowNum - 1 && indexY < panel.colNum - 1 && indexY > 0) && (edgeTopRight || edgeBottomRight || edgeLeft)) {
            random = (int) (Math.random()*chanceWaterSoluble) + 1;
            if(random == 1){
                nextGrid[indexY][indexX] = 3;
                turnedIntoWater = true;
            }
            else {
                nextGrid[indexY][indexX] = elementType;
            }
        }

        // check if tiles around snow tile are fire
        if(isFireCheck(grid,indexX - 1,indexY) || isFireCheck(grid,indexX - 1,indexY - 1) ||
                isFireCheck(grid,indexX,indexY - 1) || isFireCheck(grid,indexX + 1,indexY - 1) ||
                (isFireCheck(grid,indexX + 1,indexY) && isCellEmpty(nextGrid,indexX + 1,indexY)) ||
                (isFireCheck(grid,indexX + 1,indexY + 1) && isCellEmpty(nextGrid,indexX + 1,indexY + 1)) ||
                (isFireCheck(grid,indexX,indexY + 1) && isCellEmpty(nextGrid,indexX,indexY + 1)) ||
                (isFireCheck(grid,indexX - 1,indexY + 1) && isCellEmpty(nextGrid,indexX - 1,indexY + 1))){
            nextGrid[indexY][indexX] = 3;
            turnedIntoWater = true;
        }

        // normal snow movement
        if(!turnedIntoWater){
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
                            (indexY + i < panel.colNum && indexY + i >= 0) && isSnowCheck(grid,indexX,indexY + i)) {
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


}
