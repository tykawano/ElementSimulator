package Elements;

import Main.SimPanel;

public abstract class Liquid extends Element{
    public int dispersalRate;
    public Liquid(SimPanel panel) {
        super(panel);
    }

    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {
        if(indexX + 1 >= panel.rowNum || indexX - 1 < 0){
            nextGrid[indexY][indexX] = 3;
        }
        else if(indexY + 1 >= panel.colNum){
            if(isCellEmpty(grid,indexX + 1,indexY)){
                nextGrid[indexY][indexX + 1] = 3;
            }
//            else if(isCellEmpty(grid,indexX - 1,indexY)){
//                nextGrid[indexY][indexX - 1] = 3;
//            }
            else{
                nextGrid[indexY][indexX] = 3;
            }
        }
        else {
            int dir = 1;
            if(Math.random() < 0.5){
                dir *= -1;
            }
            if(isCellEmpty(grid,indexX,indexY + 1)){
                nextGrid[indexY + 1][indexX] = 3;
            }
            else if (isCellEmpty(grid,indexX - dir,indexY + 1)) {
                nextGrid[indexY + 1][indexX - dir] = 3;
            }
            else if(isCellEmpty(grid,indexX + dir,indexY + 1)){
                nextGrid[indexY + 1][indexX + dir] = 3;
            }
            else {
//                int currAmountShifted = 0;
//                for (int i = 1; i <= dispersalRate; i++) {
//                    if(!isCellEmpty(grid,indexX + 1,indexY)){
//                        break;
//                    }
//                    currAmountShifted++;
//                }
                if(isCellEmpty(grid,indexX + 1,indexY)){
                    nextGrid[indexY][indexX + 1] = 3;
                }

            }
        }
    }
}
