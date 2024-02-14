package Elements;

import Main.SimPanel;

import java.awt.*;

public class Sand extends SolidMovable{
    public Sand( SimPanel panel,int elementType){
        super(panel);
        this.setDefaults(elementType);
    }
    @Override
    public void setDefaults(int elementType){
        this.elementType = elementType;
        color = new Color(194,178,128);
    }





}
