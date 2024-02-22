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
        isFlamable = new boolean[15];
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
        elementStateMatter[8] = "movableSolid";
        elementStateMatter[9] = "liquid";
    }
    abstract public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY);
    abstract public void setDefaults(int elementType);

    // checker to see if cell is value 0, where there is empty space
    public boolean isCellEmpty(int[][] grid,int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            return (grid[nextCol][nextRow] == 0);
        }
        return false;
    }

    // swapping elements in the array
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

    // checker if an element in array is snow
    public boolean isSnowCheck(int[][] grid, int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            return (grid[nextCol][nextRow] == 4);
        }
        return false;
    }

    public boolean isGravelCheck(int[][] grid, int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            return (grid[nextCol][nextRow] == 8);
        }
        return false;
    }

    // checker if an element in array is fire
    public boolean isFireCheck(int[][] grid,int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            return (grid[nextCol][nextRow] == 6);
        }
        return false;
    }

    // checker if an element in array is water
    public boolean isWaterCheck(int[][] grid,int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            return (grid[nextCol][nextRow] == 3);
        }
        return false;
    }

    // checker if an element in array is a flammable element
    public boolean flammableElementCheck(int[][] grid ,int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            int elementType = grid[nextCol][nextRow];
            return isFlamable[elementType];
        }
        return false;
    }

    // checker if an element in array is solid element
    public boolean isCellSolid(int[][] grid ,int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            int currElementCheck = grid[nextCol][nextRow];
            return elementStateMatter[currElementCheck].equals("movableSolid") || elementStateMatter[currElementCheck].equals("liquid");
        }
        return false;
    }

    // checker if an element in array is sand
    public boolean isSand(int[][] grid ,int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            return (grid[nextCol][nextRow] == 1);
        }
        return false;
    }

    // checker if an element in array is wood
    public boolean isWoodCheck(int[][] grid , int nextRow, int nextCol){
        if((nextRow < panel.rowNum && nextRow >= 0) && (nextCol < panel.colNum && nextCol >= 0)){
            return (grid[nextCol][nextRow] == 2);
        }
        return false;
    }
}
