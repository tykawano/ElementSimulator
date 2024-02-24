package Elements;

import Main.SimPanel;

import java.awt.*;

public class Lava extends Liquid {
    public Lava(SimPanel panel, int elementType) {
        super(panel);
        this.setDefaults(elementType);
    }

    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        color = new Color(255, 37, 0);
    }

    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {
        boolean turnedIntoObsidian;
        woodReaction(grid, nextGrid, indexX, indexY);

        turnedIntoObsidian = waterReaction(grid,nextGrid,indexX,indexY);

        if(!turnedIntoObsidian) {
            super.action(grid, nextGrid, indexX, indexY);
        }


    }

    private boolean waterReaction(int[][] grid, int[][] nextGrid, int indexX, int indexY){
        int threshold = 10;
        int oddsOfObsidian = 2;
        int random;


        boolean turnedIntoObsidian;
        if((isWaterCheck(grid,indexX + 1,indexY) && isCellEmpty(nextGrid,indexX + 1,indexY))){
            grid[indexY][indexX] = 7;
            grid[indexY][indexX + 1] = 11;
            nextGrid[indexY][indexX] = 7;
            nextGrid[indexY][indexX + 1] = 11;
            for (int i = threshold; i >= 1; i--) {
                if (isWaterCheck(grid,indexX + 1 + i,indexY + i)){
                    random = (int) (Math.random()*oddsOfObsidian)+ 1;
                    if(random == 1){
                        grid[indexY + i][indexX + 1 + i] = 11;
                        nextGrid[indexY + i][indexX + 1 + i] = 11;
                    }
                }
            }
            turnedIntoObsidian = true;
            return turnedIntoObsidian;
        }

        if(isWaterCheck(grid,indexX + 1,indexY + 1) && isCellEmpty(nextGrid,indexX + 1,indexY + 1)){
            grid[indexY][indexX] = 7;
            grid[indexY + 1][indexX + 1] = 11;
            nextGrid[indexY][indexX] = 7;
            nextGrid[indexY + 1][indexX + 1] = 11;
            for (int i = threshold; i >= 1; i--) {
                if (isWaterCheck(grid,indexX + 1 + i,indexY + 1 + i)){
                    random = (int) (Math.random()*oddsOfObsidian)+ 1;
                    if(random == 1){
                        grid[indexY + 1 + i][indexX + 1 + i] = 11;
                        nextGrid[indexY + 1 + i][indexX + 1 + i] = 11;
                    }
                }
            }
            turnedIntoObsidian = true;
            return turnedIntoObsidian;
        }

        if((isWaterCheck(grid,indexX,indexY + 1) && isCellEmpty(nextGrid,indexX,indexY + 1)) ){
            grid[indexY][indexX] = 7;
            grid[indexY + 1][indexX] = 11;
            nextGrid[indexY][indexX] = 7;
            nextGrid[indexY + 1][indexX] = 11;
            for (int i = threshold; i >= 1; i--) {
                if (isWaterCheck(grid,indexX,indexY + 1 + i)){
                    random = (int) (Math.random()*oddsOfObsidian)+ 1;
                    if(random == 1){
                        grid[indexY + 1 + i][indexX] = 11;
                        nextGrid[indexY + 1 + i][indexX] = 11;
                    }
                }
            }
            turnedIntoObsidian = true;
            return turnedIntoObsidian;
        }

        if((isWaterCheck(grid,indexX - 1,indexY + 1) && isCellEmpty(nextGrid,indexX - 1,indexY + 1))){
            grid[indexY][indexX] = 7;
            grid[indexY + 1][indexX - 1] = 11;
            nextGrid[indexY][indexX] = 7;
            nextGrid[indexY + 1][indexX - 1] = 11;
            for (int i = threshold; i >= 1; i--) {
                if (isWaterCheck(grid,indexX - 1 - i,indexY + 1 + i)){
                    random = (int) (Math.random()*oddsOfObsidian)+ 1;
                    if(random == 1){
                        grid[indexY + 1 + i][indexX - 1 - i] = 11;
                        nextGrid[indexY + 1 + i][indexX - 1 - i] = 11;
                    }
                }
            }
            turnedIntoObsidian = true;
            return turnedIntoObsidian;
        }
        else {
            return false;
        }
    }

    private void woodReaction ( int[][] grid, int[][] nextGrid, int indexX, int indexY){
        int random;
        int oddsOfigniting = 50;

        // top left check
        if (flammableElementCheck(grid, indexX - 1, indexY - 1)) {
            random = (int) (Math.random() * oddsOfigniting) + 1;
            if (random == 1) {
                nextGrid[indexY - 1][indexX - 1] = 6;
            }
        }

        // top middle check
        if (flammableElementCheck(grid, indexX, indexY - 1)) {
            random = (int) (Math.random() * oddsOfigniting) + 1;
            if (random == 1) {
                nextGrid[indexY - 1][indexX] = 6;
            }
        }

        // top right check
        if (flammableElementCheck(grid, indexX + 1, indexY - 1)) {
            random = (int) (Math.random() * oddsOfigniting) + 1;
            if (random == 1) {
                nextGrid[indexY - 1][indexX + 1] = 6;
            }
        }

        // right middle check
        if (flammableElementCheck(grid, indexX + 1, indexY)) {
            random = (int) (Math.random() * oddsOfigniting) + 1;
            if (random == 1) {
                nextGrid[indexY][indexX + 1] = 6;
            }
        }

        // left middle check
        if (flammableElementCheck(grid, indexX - 1, indexY)) {
            random = (int) (Math.random() * oddsOfigniting) + 1;
            if (random == 1) {
                nextGrid[indexY][indexX - 1] = 6;
            }
        }

        // bottom right check
        if (flammableElementCheck(grid, indexX + 1, indexY + 1)) {
            random = (int) (Math.random() * oddsOfigniting) + 1;
            if (random == 1) {
                nextGrid[indexY + 1][indexX + 1] = 6;
            }
        }

        // bottom middle check
        if (flammableElementCheck(grid, indexX, indexY + 1)) {
            random = (int) (Math.random() * oddsOfigniting) + 1;
            if (random == 1) {
                nextGrid[indexY + 1][indexX] = 6;
            }
        }

        // bottom left check
        if (flammableElementCheck(grid, indexX - 1, indexY + 1)) {
            random = (int) (Math.random() * oddsOfigniting) + 1;
            if (random == 1) {
                nextGrid[indexY + 1][indexX - 1] = 6;
            }
        }
    }
}

