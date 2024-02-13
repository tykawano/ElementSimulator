package Grid;

import Elements.Element;
import Elements.Sand;
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
    public Grid(SimPanel panel, int rowNum, int colNum, MouseInputs mouse){
        this.panel = panel;
        grid = new int[colNum][rowNum];
        this.mouse = mouse;
        this.loadGrid();
    }
    public void loadGrid(){
        elementTypes = new Element[10];
        elementTypes[1] = new Sand(panel,1);
    }

    public void setGrid(int rowNum, int colNum, int elementType) {
        this.grid[colNum][rowNum] = elementType;
    }
    public void update(){
        if(panel.getUiPopUpState() == 0){
            if(mouse.isDragging()){
                setGrid(setRowIndex(mouse.getMouseX()),setColIndex(mouse.getMouseY()),currElementType);
            }

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
}
