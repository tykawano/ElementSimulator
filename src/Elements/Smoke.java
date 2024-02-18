package Elements;

import Main.SimPanel;

import java.awt.*;

public class Smoke extends Gas{
    public Smoke(SimPanel panel, int elementType) {
        super(panel);
        this.setDefaults(elementType);
    }

    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        color = new Color(132, 136, 132,100);
    }
}
