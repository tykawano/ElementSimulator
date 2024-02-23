package Elements;

import Main.SimPanel;

import java.awt.*;

public class Sand extends SolidMovable{
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
        boolean turnedIntoGlass = false;
        int threshold = 7;
        int random;
        int oddsOfGlassmaking = 3;


        if(isLavaCheck(grid,indexX - 1,indexY)){
            nextGrid[indexY][indexX] = 10;
            nextGrid[indexY][indexX - 1] = 5;
            for (int i = threshold; i > 1; i--) {
                if (isSand(grid,indexX + i,indexY + i)){
                    random = (int) (Math.random()*oddsOfGlassmaking)+ 1;
                    if(random == 1){
                        grid[indexY + i][indexX + i] = 10;
                        nextGrid[indexY + i][indexX + i] = 10;
                    }
                }
            }
            turnedIntoGlass = true;
        }
        else if(isLavaCheck(grid,indexX - 1,indexY - 1)){
            nextGrid[indexY][indexX] = 10;
            nextGrid[indexY - 1][indexX - 1] = 5;
            for (int i = threshold; i > 1; i--) {
                if (isSand(grid,indexX + i,indexY + i)){
                    random = (int) (Math.random()*oddsOfGlassmaking)+ 1;
                    if(random == 1){
                        grid[indexY + i][indexX + i] = 10;
                        nextGrid[indexY + i][indexX + i] = 10;
                    }
                }
            }
            turnedIntoGlass = true;
        }
        else if(isLavaCheck(grid,indexX,indexY - 1)) {
            nextGrid[indexY][indexX] = 10;
            nextGrid[indexY - 1][indexX] = 5;
            for (int i = threshold; i > 1; i--) {
                if (isSand(grid,indexX,indexY + i)){
                    random = (int) (Math.random()*oddsOfGlassmaking)+ 1;
                    if(random == 1){
                        grid[indexY + i][indexX] = 10;
                        nextGrid[indexY + i][indexX] = 10;
                    }
                }
            }
            turnedIntoGlass = true;
        }
        else if(isLavaCheck(grid,indexX + 1,indexY - 1) ){
            nextGrid[indexY][indexX] = 10;
            nextGrid[indexY - 1][indexX + 1] = 5;
            for (int i = threshold; i > 1; i--) {
                if (isSand(grid,indexX - i,indexY + i)){
                    random = (int) (Math.random()*oddsOfGlassmaking)+ 1;
                    if(random == 1){
                        grid[indexY + i][indexX - i] = 10;
                        nextGrid[indexY + i][indexX - i] = 10;
                    }
                }
            }
            turnedIntoGlass = true;
        }
        else if((isLavaCheck(grid,indexX + 1,indexY) && isCellEmpty(nextGrid,indexX + 1,indexY))){
            nextGrid[indexY][indexX] = 10;
            nextGrid[indexY][indexX + 1] = 5;
            for (int i = threshold; i > 1; i--) {
                if (isSand(grid,indexX - i,indexY + i)){
                    random = (int) (Math.random()*oddsOfGlassmaking)+ 1;
                    if(random == 1){
                        grid[indexY + i][indexX - i] = 10;
                        nextGrid[indexY + i][indexX - i] = 10;
                    }
                }
            }
            turnedIntoGlass = true;
        }
        else if(isLavaCheck(grid,indexX + 1,indexY + 1) && isCellEmpty(nextGrid,indexX + 1,indexY + 1)){
            nextGrid[indexY][indexX] = 10;
            nextGrid[indexY + 1][indexX + 1] = 5;
            for (int i = threshold; i > 1; i--) {
                if (isSand(grid,indexX - i,indexY - i)){
                    random = (int) (Math.random()*oddsOfGlassmaking)+ 1;
                    if(random == 1){
                        grid[indexY - i][indexX - i] = 10;
                        nextGrid[indexY - i][indexX - i] = 10;
                    }
                }
            }
            turnedIntoGlass = true;
        }
        else if((isLavaCheck(grid,indexX,indexY + 1) && isCellEmpty(nextGrid,indexX,indexY + 1)) ){
            nextGrid[indexY][indexX] = 10;
            nextGrid[indexY + 1][indexX] = 5;
            for (int i = threshold; i > 1; i--) {
                if (isSand(grid,indexX,indexY - i)){
                    random = (int) (Math.random()*oddsOfGlassmaking)+ 1;
                    if(random == 1){
                        grid[indexY - i][indexX] = 10;
                        nextGrid[indexY - i][indexX] = 10;
                    }
                }
            }
            turnedIntoGlass = true;
        }
        else if((isLavaCheck(grid,indexX - 1,indexY + 1) && isCellEmpty(nextGrid,indexX - 1,indexY + 1))){
            nextGrid[indexY][indexX] = 10;
            nextGrid[indexY + 1][indexX - 1] = 5;
            for (int i = threshold; i > 1; i--) {
                if (isSand(grid,indexX + i,indexY - i)){
                    random = (int) (Math.random()*oddsOfGlassmaking)+ 1;
                    if(random == 1){
                        grid[indexY - i][indexX + i] = 10;
                        nextGrid[indexY - i][indexX + i] = 10;
                    }
                }
            }
            turnedIntoGlass = true;
        }

        if(!turnedIntoGlass){
            super.action(grid, nextGrid, indexX, indexY);
        }

    }
}
