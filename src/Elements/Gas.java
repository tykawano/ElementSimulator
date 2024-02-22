package Elements;

import Main.SimPanel;


public abstract class Gas extends Element{
    public int chanceHorizontal;
    public int chanceNoMovement;
    public Gas(SimPanel panel) {
        super(panel);
    }

    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {

        int dir = 1;
        if(Math.random() < 0.5){
            dir *= -1;
        }

        if(isCellEmpty(grid,indexX,indexY - 1) && isCellEmpty(nextGrid,indexX,indexY - 1)){
            nextGrid[indexY - 1][indexX] = elementType;
        }
        else if (isCellEmpty(grid,indexX - dir,indexY - 1) && isCellEmpty(nextGrid,indexX - dir,indexY - 1)) {
            nextGrid[indexY - 1][indexX - dir] = elementType;
        }
        else if (isCellEmpty(grid,indexX + dir,indexY - 1) && isCellEmpty(nextGrid,indexX + dir,indexY - 1)) {
            nextGrid[indexY - 1][indexX + dir] = elementType;
        }
        else if (isCellSolid(grid,indexX,indexY - 1)) {
            switchElements(grid,indexX,indexY,indexX,indexY - 1,nextGrid);
        }
        else if (isCellEmpty(grid,indexX - dir,indexY) && isCellEmpty(nextGrid,indexX - dir,indexY)) {
            grid[indexY][indexX] = 0;
            int disappearNum = (int) (Math.random()*chanceHorizontal) + 1;
            if(disappearNum != 1){
                nextGrid[indexY][indexX - dir] = elementType;
            }

        }
        else if(isCellEmpty(grid,indexX + dir,indexY) && isCellEmpty(nextGrid,indexX + dir,indexY)){
            grid[indexY][indexX] = 0;
            int disappearNum = (int) (Math.random()*chanceHorizontal) + 1;
            if(disappearNum != 1){
                nextGrid[indexY][indexX + dir] = elementType;
            }
        }
        else {
            int disappearNum = (int) (Math.random()*chanceNoMovement) + 1;
            if(disappearNum == 1){
                nextGrid[indexY][indexX] = 0;
            }
            else {
                nextGrid[indexY][indexX] = elementType;
            }
        }
    }

}
