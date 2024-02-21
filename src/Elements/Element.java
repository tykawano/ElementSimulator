package Elements;

import Main.SimPanel;

import java.awt.*;

public abstract class Element {
    public Color color;
    public int elementType = 0;
    public SimPanel panel;
    public boolean[] isFlamable;

    public Element( SimPanel panel){
        this.panel = panel;
        makeFlammableArray();
    }
    private void makeFlammableArray(){
        isFlamable = new boolean[10];
        isFlamable[2] = true;
    }
    abstract public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY);
    abstract public void setDefaults(int elementType);
    public boolean isCellEmpty(int[][] grid,int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            return (grid[nextCol][nextRow] == 0);
        }
        return false;
    }
    public void switchElements(int[][]grid, int rowFirstElement, int colFirstElement, int rowSecondElement, int colSecondElement, int[][] nextGrid){
        int temp = grid[colFirstElement][rowFirstElement];
        nextGrid[colFirstElement][rowFirstElement] = grid[colSecondElement][rowSecondElement];
        nextGrid[colSecondElement][rowSecondElement] = temp;
    }

    public int getElementType() {
        return elementType;
    }

    public Color getColor() {
        return color;
    }
}
