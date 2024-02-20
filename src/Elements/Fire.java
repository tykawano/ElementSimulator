package Elements;

import Main.SimPanel;

import java.awt.*;

public class Fire extends Plasma{
    public Fire(SimPanel panel, int elementType) {
        super(panel);
        this.setDefaults(elementType);
    }

    @Override
    public void action(int[][] grid, int[][] nextGrid, int indexX, int indexY) {
        
    }

    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        color = new Color(255,90,0);
    }
}
