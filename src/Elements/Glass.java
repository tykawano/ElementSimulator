package Elements;

import Main.SimPanel;

import java.awt.*;

public class Glass extends SolidImmovable{
    public Glass(SimPanel panel, int elementType) {
        super(panel);
        this.setDefaults(elementType);
    }


    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        color = new Color(216,228,233,80);
    }
}
