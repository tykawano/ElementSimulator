package Grid;

import Elements.Element;
import Elements.Sand;
import Main.SimPanel;

import java.awt.*;

public class Grid {
    public SimPanel panel;
    public Element[] elementTypes;
    public int[][] grid;
    public Grid(SimPanel panel,int rowNum,int colNum){
        this.panel = panel;
        grid = new int[colNum][rowNum];
        this.loadGrid();
    }
    public void loadGrid(){
        elementTypes = new Element[10];
        elementTypes[1] = new Sand(panel,1);
    }

    public void setGrid(int rowNum, int colNum, int elementType) {
        this.grid[colNum][rowNum] = elementType;
    }

    public Element[] getElementTypes() {
        return elementTypes;
    }
    public void update(){
        setGrid(25,1,1);
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


}
