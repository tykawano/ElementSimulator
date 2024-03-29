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
        color = new Color(126,126,126,200);
        this.chanceHorizontal = 150;
        this.chanceNoMovement = 100;
    }
}
