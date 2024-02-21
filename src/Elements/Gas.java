package Elements;

import Main.SimPanel;


public abstract class Gas extends Element{
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
            nextGrid[indexY - 1][indexX] = 5;
        }
        else if (isCellEmpty(grid,indexX - dir,indexY - 1) && isCellEmpty(nextGrid,indexX - dir,indexY - 1)) {
            nextGrid[indexY - 1][indexX - dir] = 5;
        }
        else if (isCellEmpty(grid,indexX + dir,indexY - 1) && isCellEmpty(nextGrid,indexX + dir,indexY - 1)) {
            nextGrid[indexY - 1][indexX + dir] = 5;
        }
        else if (isCellEmpty(grid,indexX - dir,indexY) && isCellEmpty(nextGrid,indexX - dir,indexY)) {
            grid[indexY][indexX] = 0;
            int disappearNum = (int) (Math.random()*150) + 1;
            if(disappearNum != 100){
                nextGrid[indexY][indexX - dir] = 5;
            }

        }
        else if(isCellEmpty(grid,indexX + dir,indexY) && isCellEmpty(nextGrid,indexX + dir,indexY)){
            grid[indexY][indexX] = 0;
            int disappearNum = (int) (Math.random()*150) + 1;
            if(disappearNum != 100){
                nextGrid[indexY][indexX + dir] = 5;
            }
        }
        else {
            int disappearNum = (int) (Math.random()*100) + 1;
            if(disappearNum == 90){
                nextGrid[indexY][indexX] = 0;
            }
            else {
                nextGrid[indexY][indexX] = 5;
            }
        }
    }
}
