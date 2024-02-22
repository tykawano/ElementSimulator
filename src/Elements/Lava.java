package Elements;

import Main.SimPanel;

import java.awt.*;

public class Lava extends Liquid{
    public Lava(SimPanel panel, int elementType) {
        super(panel);
        this.setDefaults(elementType);
    }

    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        color = new Color(255,37,0);
    }

    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {
        int random;
        int oddsOfigniting = 50;

        // top left check
        if(flammableElementCheck(grid,indexX - 1,indexY - 1)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY - 1][indexX - 1] = 6;
            }
        }

        // top middle check
        if(flammableElementCheck(grid,indexX,indexY - 1)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY - 1][indexX] = 6;
            }
        }

        // top right check
        if(flammableElementCheck(grid,indexX + 1,indexY - 1)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY - 1][indexX + 1] = 6;
            }
        }

        // right middle check
        if(flammableElementCheck(grid,indexX + 1,indexY)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY][indexX + 1] = 6;
            }
        }

        // left middle check
        if(flammableElementCheck(grid,indexX - 1,indexY)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY][indexX - 1] = 6;
            }
        }

        // bottom right check
        if(flammableElementCheck(grid,indexX + 1,indexY + 1)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY + 1][indexX + 1] = 6;
            }
        }

        // bottom middle check
        if(flammableElementCheck(grid,indexX,indexY + 1)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY + 1][indexX] = 6;
            }
        }

        // bottom left check
        if(flammableElementCheck(grid,indexX - 1,indexY + 1)){
            random = (int) (Math.random()*oddsOfigniting)+ 1;
            if(random == 1){
                nextGrid[indexY + 1][indexX - 1] = 6;
            }
        }
        super.action(grid,nextGrid,indexX,indexY);

    }
}
