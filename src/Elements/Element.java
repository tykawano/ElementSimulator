package Elements;

import Main.SimPanel;

import java.awt.*;

public abstract class Element {
    public Color color;
    public int elementType = 0;
    public int pixelX;
    public int pixelY;
    public int rowNum;
    public int colNum;
    public SimPanel panel;

    public Element( SimPanel panel){
        this.panel = panel;
//        this.setCords(x,y);
    }
    private void setCords(int x, int y){
        this.setRowColIndex(x,y);
        this.setElementsPixelPos(x,y);
    }
    private void setElementsPixelPos(int x, int y){
        int tempX = x%panel.sizePixel;
        int tempY = y%panel.sizePixel;

        pixelX = x - tempX;
        pixelY = y - tempY;
    }
    public void setRowColIndex(int x, int y){
        rowNum = x/panel.sizePixel;
        colNum = y/panel.sizePixel;
    }


    public int getElementType() {
        return elementType;
    }

    public Color getColor() {
        return color;
    }
}
