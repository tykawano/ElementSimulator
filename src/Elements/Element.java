package Elements;

import Main.SimPanel;

import java.awt.*;

public abstract class Element {
    public Color color;
    public int elementType = 0;
    public SimPanel panel;

    public Element( SimPanel panel){
        this.panel = panel;
    }
    abstract public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY);
    public boolean isCellEmpty(int[][] grid,int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            return (grid[nextCol][nextRow] == 0);
        }
        return false;
    }

    public int getElementType() {
        return elementType;
    }

    public Color getColor() {
        return color;
    }
}
