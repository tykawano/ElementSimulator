package Elements;

import Main.SimPanel;

import java.awt.*;

public abstract class Element {
    public Color color;
    public int elementType = 0;
    public SimPanel panel;
    public boolean[] isFlamable;
    public String[] elementStateMatter;

    public Element( SimPanel panel){
        this.panel = panel;
        makeFlammableArray();
        makeStateMatterArray();
    }
    private void makeFlammableArray(){
        isFlamable = new boolean[10];
        isFlamable[2] = true;
    }
    private void makeStateMatterArray(){
        elementStateMatter = new String[10];
        elementStateMatter[0] = "air";
        elementStateMatter[1] = "movableSolid";
        elementStateMatter[2] = "immovableSolid";
        elementStateMatter[3] = "liquid";
        elementStateMatter[4] = "movableSolid";
        elementStateMatter[5] = "gas";
        elementStateMatter[6] = "plasma";
        elementStateMatter[7] = "gas";
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
