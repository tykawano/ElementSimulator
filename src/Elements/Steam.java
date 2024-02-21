package Elements;

import Main.SimPanel;

import java.awt.*;

public class Steam extends Gas{

    public Steam(SimPanel panel, int elementType) {
        super(panel);
        this.setDefaults(elementType);
    }

    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        color = new Color(71,71,71,120);
        this.chanceHorizontal = 75;
        this.chanceNoMovement = 25;
    }


}
