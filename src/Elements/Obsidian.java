package Elements;

import Main.SimPanel;

import java.awt.*;

public class Obsidian extends SolidImmovable{
    public Obsidian(SimPanel panel, int elementType) {
        super(panel);
        this.setDefaults(elementType);
    }

    @Override
    public void setDefaults(int elementType) {
        this.elementType = elementType;
        color = new Color(91,73,101);
    }
}
