package Elements;

import Main.SimPanel;

public abstract class Liquid extends Element{
    public int dispersalRate;
    public Liquid(SimPanel panel) {
        super(panel);
    }

    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {
        int dir = 1;
        if(Math.random() < 0.5){
            dir *= -1;
        }

        if(isCellEmpty(grid,indexX,indexY + 1) && isCellEmpty(nextGrid,indexX,indexY + 1)){
            nextGrid[indexY + 1][indexX] = 3;
        }
        else if (isCellEmpty(grid,indexX - dir,indexY + 1) && isCellEmpty(nextGrid,indexX - dir,indexY + 1)) {
            nextGrid[indexY + 1][indexX - dir] = 3;
        }
        else if (isCellEmpty(grid,indexX + dir,indexY + 1) && isCellEmpty(nextGrid,indexX + dir,indexY + 1)) {
            nextGrid[indexY + 1][indexX + dir] = 3;
        }
        else if (isCellEmpty(grid,indexX - dir,indexY) && isCellEmpty(nextGrid,indexX - dir,indexY)) {
            nextGrid[indexY][indexX - dir] = 3;
        }
        else if(isCellEmpty(grid,indexX + dir,indexY) && isCellEmpty(nextGrid,indexX + dir,indexY)){
            nextGrid[indexY][indexX + dir] = 3;
        }
        else {
            if(isSand(grid,indexX,indexY - 1)){
                switchElements(grid,indexX,indexY,indexX,indexY - 1,nextGrid);
            }
            else {
                nextGrid[indexY][indexX] = 3;
            }

        }
    }

    public boolean isSand(int[][] grid ,int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            return (grid[nextCol][nextRow] == 1);
        }
        return false;
    }
}
