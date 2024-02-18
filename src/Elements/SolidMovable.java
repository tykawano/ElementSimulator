package Elements;

import Main.SimPanel;

public abstract class SolidMovable extends Element {
    public SolidMovable(SimPanel panel) {
        super(panel);
    }
    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {
            int dir = 1;
            if(Math.random() < 0.5){
                dir *= -1;
            }
            if(isCellEmpty(grid,indexX,indexY + 1) && isCellEmpty(nextGrid,indexX,indexY + 1)){
                nextGrid[indexY + 1][indexX] = 1;
            }
            else if (isCellEmpty(grid,indexX - dir,indexY + 1) && isCellEmpty(nextGrid,indexX - dir,indexY + 1)) {
                nextGrid[indexY + 1][indexX - dir] = 1;
            }
            else if(isCellEmpty(grid,indexX + dir,indexY + 1) && isCellEmpty(nextGrid,indexX + dir,indexY + 1)){
                nextGrid[indexY + 1][indexX + dir] = 1;
            }
            else {
                if(isWater(grid, indexX, indexY + 1)){
                    switchElements(grid,indexX,indexY,indexX,indexY + 1,nextGrid);
                }
                else {
                    nextGrid[indexY][indexX] = 1;
                }

            }

    }

    private boolean isWater(int[][] grid ,int nextRow, int nextCol ){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            return (grid[nextCol][nextRow] == 3);
        }
        return false;
    }
}
