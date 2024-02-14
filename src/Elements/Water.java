package Elements;

import Main.SimPanel;

import java.awt.*;

public class Water extends Liquid{
    public Water(SimPanel panel, int elementType) {
        super(panel);
        this.setDefaults(elementType);
    }

    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        color = new Color(28,163,236);
        dispersalRate = 5;
    }
}
