package Elements;

import Main.SimPanel;

import java.awt.*;

public class Wood extends SolidImmovable {

    public Wood(SimPanel panel, int elementType) {
        super(panel);
        this.setDefaults(elementType);
    }

    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        this.color = new Color(150, 111, 51);
        this.isFlamable = true;
    }
}
