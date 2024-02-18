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
                nextGrid[indexY][indexX] = 1;

            }

    }
}
