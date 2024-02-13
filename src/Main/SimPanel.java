package Main;

import Grid.Grid;
import Inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class SimPanel extends JPanel implements Runnable{
    public final int sizePixel = 15;
    public final int rowNum = 80;
    public final int colNum = 45;
    public final int widthPixel = sizePixel*rowNum;
    public final int heightPixel = sizePixel*colNum;
    public final int UISIZEWIDTH = widthPixel - (2*(4*sizePixel));
    public final int UISIZEHEIGHT = heightPixel - (2*(3*sizePixel));
    public final int xcord = (sizePixel*4+UISIZEWIDTH) - (sizePixel*4 + (sizePixel/4));
    public final int ycord = (sizePixel*4) + (sizePixel*2);
    public final int fps = 100;
    public int UiPopUpState = 0;
    public static final int SOLIDSTATEUI = 0;
    public static final int LIQUIDSTATEUI = 1;
    public static final int GASSTATEUI = 2;
    public static final int PLAMSMASTATEUI = 3;
    int count = 0;
    public int buttonState = SOLIDSTATEUI;

    MouseInputs mouse = new MouseInputs();
    Grid grid;
    Thread simThread;
    public SimPanel(){
        setDefault();

        grid = new Grid(this,rowNum,colNum,mouse);
        this.startThread();
    }
    public void startThread(){
        simThread = new Thread(this);
        simThread.start();
    }
    public void update(){
        // update grid elements and there rules
        grid.update();


        int clearButtonMinX = widthPixel - (sizePixel*14);
        int clearButtonMaxX = clearButtonMinX + sizePixel * 5;

        int buttonMinX = widthPixel - (sizePixel*6);
        int buttonMaxX = buttonMinX + sizePixel * 5;
        int buttonMinY = sizePixel;
        int buttonMaxY = buttonMinY + sizePixel + (sizePixel*2);
        if(count >= 7){
            // clear button pressed check
            if(UiPopUpState == 0 && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= clearButtonMinX && mouse.mouseXUI <= clearButtonMaxX)
                    && (mouse.mouseYUI >= buttonMinY && mouse.mouseYUI <= buttonMaxY)){
                grid.clearItems(colNum,rowNum);
            }

            // update options button to see if clicked
            if(UiPopUpState == 0 && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= buttonMinX && mouse.mouseXUI <= buttonMaxX)
                    && (mouse.mouseYUI >= buttonMinY && mouse.mouseYUI <= buttonMaxY)){
                UiPopUpState = 1;
            }
            else if (UiPopUpState == 1 && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= buttonMinX && mouse.mouseXUI <= buttonMaxX)
                    && (mouse.mouseYUI >= buttonMinY && mouse.mouseYUI <= buttonMaxY)) {
                UiPopUpState = 0;
            }

            // update to see if solid's UI button is pressed
            int solidButtonMinX = widthPixel - sizePixel*8 - (sizePixel/4);
            int solidButtonMaxX = solidButtonMinX + sizePixel*4;
            int solidButtonMinY = 6*sizePixel;
            int solidButtonMaxY = solidButtonMinY + sizePixel*2;

            int liquidButtonMinY = solidButtonMinY + sizePixel*4;
            int liquidButtonMaxY = liquidButtonMinY + sizePixel*2;

            int gasButtonMinY = liquidButtonMinY + sizePixel*4;
            int gasButtonMaxY = gasButtonMinY + sizePixel*2;

            int plasmaButtonMinY = gasButtonMinY + sizePixel*4;
            int plasmaButtonMaxY = plasmaButtonMinY + sizePixel*2;

            if(UiPopUpState == 1 && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= solidButtonMinX && mouse.mouseXUI <= solidButtonMaxX)
                    && (mouse.mouseYUI >= solidButtonMinY && mouse.mouseYUI <= solidButtonMaxY)){
                System.out.println("solid button");
                buttonState = SOLIDSTATEUI;
            }
            else if (UiPopUpState == 1 && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= solidButtonMinX && mouse.mouseXUI <= solidButtonMaxX)
                    && (mouse.mouseYUI >= liquidButtonMinY && mouse.mouseYUI <= liquidButtonMaxY)) {
                System.out.println("liquid button");
                buttonState = LIQUIDSTATEUI;
            }
            else if (UiPopUpState == 1 && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= solidButtonMinX && mouse.mouseXUI <= solidButtonMaxX)
                    && (mouse.mouseYUI >= gasButtonMinY && mouse.mouseYUI <= gasButtonMaxY)) {
                System.out.println("gas button");
                buttonState = GASSTATEUI;
            }
            else if (UiPopUpState == 1 && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= solidButtonMinX && mouse.mouseXUI <= solidButtonMaxX)
                    && (mouse.mouseYUI >= plasmaButtonMinY && mouse.mouseYUI <= plasmaButtonMaxY)) {
                System.out.println("plasma button");
                buttonState = PLAMSMASTATEUI;
            }



            // update to see if sand UI button is pressed
            int sandButtonMinX = sizePixel*5;
            int sandButtonMaxX = sandButtonMinX + sizePixel*4;
            int sandButtonMinY = 6*sizePixel;
            int sandButtonMaxY = sandButtonMinY + sizePixel*2;

            int woodButtonMinX = sizePixel*10;
            int woodButtonMaxX = woodButtonMinX + sizePixel*4;

            if(buttonState == SOLIDSTATEUI && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= sandButtonMinX && mouse.mouseXUI <= sandButtonMaxX)
                    && (mouse.mouseYUI >= sandButtonMinY && mouse.mouseYUI <= sandButtonMaxY)){
                grid.setCurrElementType(1);
                System.out.println(grid.currElementType);
            }
            else if (buttonState == SOLIDSTATEUI && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= woodButtonMinX && mouse.mouseXUI <= woodButtonMaxX)
                    && (mouse.mouseYUI >= sandButtonMinY && mouse.mouseYUI <= sandButtonMaxY)) {
                grid.setCurrElementType(2);
                System.out.println(grid.currElementType);
            }
            count = 0;
        }


        count++;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);

        for (int i = 0; i < heightPixel; i+=sizePixel) {
            for (int j = 0; j < widthPixel; j+=sizePixel) {
                g2.drawRect(j,i,sizePixel,sizePixel);
            }
        }

        paintButtonUI(g2);
        grid.paint(g2);

        if(UiPopUpState == 1){
            paintOptionsScreen(g2);
            if(buttonState == SOLIDSTATEUI){
                paintSolidsButtons(g2);
            }
            else if(buttonState == LIQUIDSTATEUI){

            }
            else if (buttonState == GASSTATEUI) {

            }
            else if(buttonState == PLAMSMASTATEUI){

            }

        }

        g2.dispose();
    }

    public void paintSolidsButtons(Graphics2D g2){
        createButtons(g2,ycord,5*sizePixel,new Color(194,178,128));
        g2.drawString("SAND",6*sizePixel,ycord + sizePixel + (sizePixel/4));

        createButtons(g2,ycord,10*sizePixel,new Color(150, 111, 51));
        g2.drawString("WOOD",11*sizePixel,ycord + sizePixel + (sizePixel/4));
    }
    public void paintButtonUI(Graphics2D g2){
        // Button display
        g2.setColor(new Color(255,164,32,180));
        g2.fillRoundRect(widthPixel - (sizePixel*6),sizePixel,sizePixel*5,sizePixel*2,35,35);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(widthPixel - (sizePixel*6),sizePixel,sizePixel*5,sizePixel*2,25,25);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD));

        if(UiPopUpState == 0){
            g2.drawString("OPTIONS",widthPixel - (sizePixel*5) + 3,sizePixel*2 + 3);
        }
        else{
            g2.drawString("CLOSE",widthPixel - (sizePixel*5) + 3,sizePixel*2 + 3);
        }

        g2.setColor(new Color(235,106,14,180));
        g2.fillRoundRect(widthPixel - (sizePixel*14),sizePixel,sizePixel*5,sizePixel*2,35,35);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(widthPixel - (sizePixel*14),sizePixel,sizePixel*5,sizePixel*2,25,25);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD));
        g2.drawString("Clear",widthPixel - (sizePixel*13) + 3,sizePixel*2 + 3);
    }

    public void paintOptionsScreen(Graphics2D g2){
        // pop up background
        g2.setColor(new Color(100,107,99));
        g2.fillRect(sizePixel*4,sizePixel*4,UISIZEWIDTH,UISIZEHEIGHT);
        g2.setColor(Color.WHITE);
        g2.drawLine(xcord - (sizePixel/4),ycord - (sizePixel*2) + (sizePixel/6),xcord - (sizePixel/4),(sizePixel*4 + UISIZEHEIGHT - (sizePixel/5)));

        // pop up #1 for solid's options
        createButtons(g2, ycord,xcord,new Color(255,255,255));
        g2.drawString("SOLIDS",xcord + sizePixel,ycord + (sizePixel + (sizePixel/4)));

        // pop up #2 for liquid Option
        int buttonCordY = ycord +(sizePixel*2) + (sizePixel*2);
        createButtons(g2, buttonCordY,xcord,new Color(255,255,255));
        g2.drawString("LIQUIDS",xcord + sizePixel,buttonCordY + (sizePixel + (sizePixel/4)));

        // pop up #3 for gases Option
        buttonCordY += (sizePixel*2) + (sizePixel*2);
        createButtons(g2, buttonCordY,xcord,new Color(255,255,255));
        g2.drawString("GASES",xcord + sizePixel,buttonCordY + (sizePixel + (sizePixel/4)));

        // pop up #4 for plasma Option
        buttonCordY += (sizePixel*2) + (sizePixel*2);
        createButtons(g2,buttonCordY,xcord,new Color(255,255,255));
        g2.drawString("Plasma",xcord + sizePixel,buttonCordY + (sizePixel + (sizePixel/4)));
    }

    private void createButtons(Graphics2D g2, int ycord,int xcord, Color color) {
        g2.setColor(color);
        g2.fillRoundRect(xcord, ycord,sizePixel*4,sizePixel*2,35,35);
        g2.setColor(new Color(0,0,0));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(xcord, ycord,sizePixel*4,sizePixel*2,25,25);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD));
    }

    @Override
    public void run() {
        double nanoTime_Per_Second = 1000000000.0 / fps;
        double delta = 0;
        double lastFrame = System.nanoTime();
        double currTime;

        double time = 0;
        int count = 0;
        while (simThread != null){
            currTime = System.nanoTime();
            delta += (currTime - lastFrame) / nanoTime_Per_Second;
            time += (currTime - lastFrame);
            lastFrame = currTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                count++;
            }

            if(time >= 1000000000){
                System.out.println("FPS: " + count);

                count = 0;
                time -= 1000000000;
            }
        }
    }
    public void setDefault(){
        // default settings
        setPreferredSize(new Dimension(widthPixel,heightPixel));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        // Mouse Inputs
        mouse = new MouseInputs();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        setFocusable(true);
    }

    public int getUiPopUpState() {
        return UiPopUpState;
    }
}
