package Elements;

import Main.SimPanel;

import java.awt.*;

public class Sand extends Solid{
    public Sand( SimPanel panel,int elementType){
        super(panel);
        this.setDefaults(elementType);
    }
    public void setDefaults(int elementType){
        this.elementType = elementType;
        color = new Color(194,178,128);
    }



}
