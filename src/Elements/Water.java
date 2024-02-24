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
        boolean turnedIntoObsidian;

        turnedIntoObsidian = lavaReaction(grid,nextGrid,indexX,indexY);

        if(!turnedIntoObsidian){
            if(isFireCheck(grid,indexX - 1,indexY)){
                nextGrid[indexY][indexX] = 0;
                nextGrid[indexY][indexX - 1] = 7;
            }
            else if(isFireCheck(grid,indexX - 1,indexY - 1)){
                nextGrid[indexY][indexX] = 0;
                nextGrid[indexY - 1][indexX - 1] = 7;
            }
            else if(isFireCheck(grid,indexX,indexY - 1)){
                nextGrid[indexY][indexX] = 0;
                nextGrid[indexY - 1][indexX] = 7;
            }
            else if(isFireCheck(grid,indexX + 1,indexY - 1)){
                nextGrid[indexY][indexX] = 0;
                nextGrid[indexY - 1][indexX + 1] = 7;
            }
            else {
                super.action(grid,nextGrid,indexX,indexY);
            }
        }


    }

    private boolean lavaReaction(int[][] grid, int[][] nextGrid, int indexX, int indexY){
        int threshold = 10;
        int oddsOfObsidian = 2;
        int random;


        boolean turnedIntoObsidian;
        if((isLavaCheck(grid,indexX + 1,indexY) && isCellEmpty(nextGrid,indexX + 1,indexY))){
            grid[indexY][indexX] = 7;
            grid[indexY][indexX + 1] = 11;
            nextGrid[indexY][indexX] = 7;
            nextGrid[indexY][indexX + 1] = 11;
            for (int i = threshold; i >= 1; i--) {
                if (isLavaCheck(grid,indexX + 1 + i,indexY + i)){
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

        if(isLavaCheck(grid,indexX + 1,indexY + 1) && isCellEmpty(nextGrid,indexX + 1,indexY + 1)){
            grid[indexY][indexX] = 7;
            grid[indexY + 1][indexX + 1] = 11;
            nextGrid[indexY][indexX] = 7;
            nextGrid[indexY + 1][indexX + 1] = 11;
            for (int i = threshold; i >= 1; i--) {
                if (isLavaCheck(grid,indexX + 1 + i,indexY + 1 + i)){
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

        if((isLavaCheck(grid,indexX,indexY + 1) && isCellEmpty(nextGrid,indexX,indexY + 1)) ){
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

        if((isLavaCheck(grid,indexX - 1,indexY + 1) && isCellEmpty(nextGrid,indexX - 1,indexY + 1))){
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
    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        color = new Color(28,163,236);
        dispersalRate = 5;
    }
}
