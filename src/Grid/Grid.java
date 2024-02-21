package Grid;

import Elements.*;
import Inputs.MouseInputs;
import Main.SimPanel;

import java.awt.*;

public class Grid {
    public SimPanel panel;
    public Element[] elementTypes;
    public int[][] grid;
    public MouseInputs mouse;
    public int timer = 0;
    public int currElementType = 1;
    int initial = 0;
    public Grid(SimPanel panel, int rowNum, int colNum, MouseInputs mouse){
        this.panel = panel;
        grid = new int[colNum][rowNum];
        this.mouse = mouse;
        this.loadGrid();
    }
    public void loadGrid(){
        elementTypes = new Element[10];
        elementTypes[1] = new Sand(panel,1);
        elementTypes[2] = new Wood(panel,2);
        elementTypes[3] = new Water(panel,3);
        elementTypes[4] = new Snow(panel,4);
        elementTypes[5] = new Smoke(panel,5);
        elementTypes[6] = new Fire(panel,6);
    }

    public void setGrid(int rowNum, int colNum, int elementType) {
        this.grid[colNum][rowNum] = elementType;
        this.grid[colNum - 1][rowNum] = elementType;
        this.grid[colNum + 1][rowNum] = elementType;
        this.grid[colNum][rowNum - 1] = elementType;
        this.grid[colNum][rowNum + 1] = elementType;
        this.grid[colNum - 1][rowNum - 1] = elementType;
        this.grid[colNum - 1][rowNum + 1] = elementType;
        this.grid[colNum + 1][rowNum - 1] = elementType;
        this.grid[colNum + 1][rowNum + 1] = elementType;
    }
    public void update(){

        if(panel.getUiPopUpState() == 0){
            boolean middleInput = isInBounds(setRowIndex(mouse.getMouseXPlacer()),setColIndex(mouse.getMouseYPlacer()));
            boolean topLeftInput = isInBounds(setRowIndex(mouse.getMouseXPlacer()) - 1,setColIndex(mouse.getMouseYPlacer()) - 1);
            boolean topMiddleInput = isInBounds(setRowIndex(mouse.getMouseXPlacer()),setColIndex(mouse.getMouseYPlacer()) - 1);
            boolean topRightInput = isInBounds(setRowIndex(mouse.getMouseXPlacer()) + 1,setColIndex(mouse.getMouseYPlacer()) - 1);
            boolean rightMiddleInput = isInBounds(setRowIndex(mouse.getMouseXPlacer()) + 1,setColIndex(mouse.getMouseYPlacer()));
            boolean bottomRightInput = isInBounds(setRowIndex(mouse.getMouseXPlacer()) + 1,setColIndex(mouse.getMouseYPlacer()) + 1);
            boolean bottomMiddleInput = isInBounds(setRowIndex(mouse.getMouseXPlacer()),setColIndex(mouse.getMouseYPlacer()) + 1);
            boolean bottomLeftInput = isInBounds(setRowIndex(mouse.getMouseXPlacer()) - 1,setColIndex(mouse.getMouseYPlacer()) + 1);
            boolean leftMiddleInput = isInBounds(setRowIndex(mouse.getMouseXPlacer()) - 1,setColIndex(mouse.getMouseYPlacer()));

            if(mouse.isDragging() && middleInput && topLeftInput && topMiddleInput && topRightInput && rightMiddleInput
                    && bottomRightInput && bottomMiddleInput && bottomLeftInput && leftMiddleInput){
                setGrid(setRowIndex(mouse.getMouseXPlacer()),setColIndex(mouse.getMouseYPlacer()),currElementType);
            }
//            if(initial == 0){
//
//                grid[panel.colNum - 3][6] = 6;
//                grid[panel.colNum - 2][6] = 2;
//                grid[panel.colNum - 2][7] = 2;
//                grid[panel.colNum - 2][8] = 2;
//                grid[panel.colNum - 2][5] = 2;
//                grid[panel.colNum - 2][4] = 2;
//                initial = 1;
//            }

            if(timer >= 5){
                initiateElementRules();
                timer = 0;
            }
            timer++;
        }

    }
    public void initiateElementRules(){
        int[][] nextGrid = new int[panel.colNum][panel.rowNum];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == elementTypes[1].getElementType()){
                    elementTypes[1].action(grid,nextGrid,j,i);
                }
                else if (grid[i][j] == elementTypes[2].getElementType()) {
                    elementTypes[2].action(grid,nextGrid,j,i);
                }
                else if (grid[i][j] == elementTypes[3].getElementType()) {
                    elementTypes[3].action(grid,nextGrid,j,i);
                }
                else if(grid[i][j] == elementTypes[4].getElementType()){
                    elementTypes[4].action(grid,nextGrid,j,i);
                }
                else if(grid[i][j] == elementTypes[5].getElementType()){
                    elementTypes[5].action(grid,nextGrid,j,i);
                }
                else if(grid[i][j] == elementTypes[6].getElementType()){
                    elementTypes[6].action(grid,nextGrid,j,i);
                }
            }
        }
        this.grid = nextGrid;
    }
    public void paint(Graphics2D g2){
        Element currElement;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == elementTypes[1].getElementType()){
                    currElement = elementTypes[1];
                    g2.setColor(currElement.getColor());
                    g2.fillRect(j*panel.sizePixel,i*panel.sizePixel,panel.sizePixel,panel.sizePixel);
                }
                else if (grid[i][j] == elementTypes[2].getElementType()) {
                    currElement = elementTypes[2];
                    g2.setColor(currElement.getColor());
                    g2.fillRect(j*panel.sizePixel,i*panel.sizePixel,panel.sizePixel,panel.sizePixel);
                }
                else if (grid[i][j] == elementTypes[3].getElementType()) {
                    currElement = elementTypes[3];
                    g2.setColor(currElement.getColor());
                    g2.fillRect(j*panel.sizePixel,i*panel.sizePixel,panel.sizePixel,panel.sizePixel);
                }
                else if(grid[i][j] == elementTypes[4].getElementType()){
                    currElement = elementTypes[4];
                    g2.setColor(currElement.getColor());
                    g2.fillRect(j*panel.sizePixel,i*panel.sizePixel,panel.sizePixel,panel.sizePixel);
                }
                else if(grid[i][j] == elementTypes[5].getElementType()){
                    currElement = elementTypes[5];
                    g2.setColor(currElement.getColor());
                    g2.fillRect(j*panel.sizePixel,i*panel.sizePixel,panel.sizePixel,panel.sizePixel);
                }
                else if(grid[i][j] == elementTypes[6].getElementType()){
                    currElement = elementTypes[6];
                    g2.setColor(currElement.getColor());
                    g2.fillRect(j*panel.sizePixel,i*panel.sizePixel,panel.sizePixel,panel.sizePixel);
                }
            }
        }
    }

    public int setColIndex(int y){
        return (y/panel.sizePixel);
    }
    public int setRowIndex(int x){
        return (x/panel.sizePixel);
    }

    public void setCurrElementType(int currElementType) {
        this.currElementType = currElementType;
    }
    public void clearItems(int col, int row){
        grid = new int[col][row];
    }
    public boolean isInBounds(int row, int col){
        return (row < panel.rowNum && row >= 0 && col < panel.colNum && col >= 0);
    }
}
