package Main;

import Grid.Grid;
import Inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class SimPanel extends JPanel implements Runnable{
    public final int sizePixel = 10;
    public final int rowNum = 120;
    public final int colNum = 60;
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
    public boolean cursorEraserPhase = false;
    public int lastElementSlected = 1;

    MouseInputs mouse = new MouseInputs();
    public Grid grid;
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

        if(count >= 8){
            int clearButtonMinX = widthPixel - (sizePixel*14);
            int clearButtonMaxX = clearButtonMinX + sizePixel * 5;
            int eraserButtonMinX = widthPixel - (sizePixel*22);
            int eraserButtonMaxX = eraserButtonMinX + sizePixel*5;

            int buttonMinX = widthPixel - (sizePixel*6);
            int buttonMaxX = buttonMinX + sizePixel * 5;
            int buttonMinY = sizePixel;
            int buttonMaxY = buttonMinY + sizePixel + (sizePixel*2);

            // Eraser button is pressed
            if(UiPopUpState == 0 && mouse.isButtonPressed() && !cursorEraserPhase &&
                    (mouse.mouseXUI >= eraserButtonMinX && mouse.mouseXUI <= eraserButtonMaxX)
                    && (mouse.mouseYUI >= buttonMinY && mouse.mouseYUI <= buttonMaxY)){
                lastElementSlected = grid.currElementType;
                grid.setCurrElementType(0);
                cursorEraserPhase = true;
            }
            else if(UiPopUpState == 0 && mouse.isButtonPressed() && cursorEraserPhase &&
                    (mouse.mouseXUI >= eraserButtonMinX && mouse.mouseXUI <= eraserButtonMaxX)
                    && (mouse.mouseYUI >= buttonMinY && mouse.mouseYUI <= buttonMaxY)){
                grid.setCurrElementType(lastElementSlected);
                cursorEraserPhase = false;
            }

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
                buttonState = SOLIDSTATEUI;
            }
            else if (UiPopUpState == 1 && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= solidButtonMinX && mouse.mouseXUI <= solidButtonMaxX)
                    && (mouse.mouseYUI >= liquidButtonMinY && mouse.mouseYUI <= liquidButtonMaxY)) {
                buttonState = LIQUIDSTATEUI;
            }
            else if (UiPopUpState == 1 && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= solidButtonMinX && mouse.mouseXUI <= solidButtonMaxX)
                    && (mouse.mouseYUI >= gasButtonMinY && mouse.mouseYUI <= gasButtonMaxY)) {
                buttonState = GASSTATEUI;
            }
            else if (UiPopUpState == 1 && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= solidButtonMinX && mouse.mouseXUI <= solidButtonMaxX)
                    && (mouse.mouseYUI >= plasmaButtonMinY && mouse.mouseYUI <= plasmaButtonMaxY)) {
                buttonState = PLAMSMASTATEUI;
            }



            // update to see if sand UI button is pressed
            int button1MinX = sizePixel*5;
            int button1MaxX = button1MinX + sizePixel*4;
            int button1MinY = 6*sizePixel;
            int button1MaxY = button1MinY + sizePixel*2;

            int button2MinX = sizePixel*10;
            int button2MaxX = button2MinX + sizePixel*4;

            int button3MinX = sizePixel*15;
            int button3MaxX = button3MinX + sizePixel*6;

            int button4MinX = sizePixel*20;
            int button4MaxX = button4MinX + sizePixel*6;

            if(UiPopUpState == 1 && buttonState == SOLIDSTATEUI && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= button1MinX && mouse.mouseXUI <= button1MaxX)
                    && (mouse.mouseYUI >= button1MinY && mouse.mouseYUI <= button1MaxY)){
                grid.setCurrElementType(1);
                UiPopUpState = 0;
                cursorEraserPhase = false;
            }
            else if (UiPopUpState == 1 && buttonState == SOLIDSTATEUI && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= button2MinX && mouse.mouseXUI <= button2MaxX)
                    && (mouse.mouseYUI >= button1MinY && mouse.mouseYUI <= button1MaxY)) {
                grid.setCurrElementType(2);
                UiPopUpState = 0;
                cursorEraserPhase = false;
            }
            else if (UiPopUpState == 1 && buttonState == SOLIDSTATEUI && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= button3MinX && mouse.mouseXUI <= button3MaxX)
                    && (mouse.mouseYUI >= button1MinY && mouse.mouseYUI <= button1MaxY)) {
                grid.setCurrElementType(4);
                UiPopUpState = 0;
                cursorEraserPhase = false;
            }
            else if (UiPopUpState == 1 && buttonState == SOLIDSTATEUI && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= button4MinX && mouse.mouseXUI <= button4MaxX)
                    && (mouse.mouseYUI >= button1MinY && mouse.mouseYUI <= button1MaxY)) {
                grid.setCurrElementType(8);
                UiPopUpState = 0;
                cursorEraserPhase = false;
            }

            if(UiPopUpState == 1 && buttonState == LIQUIDSTATEUI && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= button1MinX && mouse.mouseXUI <= button1MaxX)
                    && (mouse.mouseYUI >= button1MinY && mouse.mouseYUI <= button1MaxY)){
                grid.setCurrElementType(3);
                UiPopUpState = 0;
                cursorEraserPhase = false;
            }

            if(UiPopUpState == 1 && buttonState == GASSTATEUI && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= button1MinX && mouse.mouseXUI <= button1MaxX)
                    && (mouse.mouseYUI >= button1MinY && mouse.mouseYUI <= button1MaxY)){
                grid.setCurrElementType(5);
                UiPopUpState = 0;
                cursorEraserPhase = false;
            }
            else if (UiPopUpState == 1 && buttonState == GASSTATEUI && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= button2MinX && mouse.mouseXUI <= button2MaxX)
                    && (mouse.mouseYUI >= button1MinY && mouse.mouseYUI <= button1MaxY)) {
                grid.setCurrElementType(7);
                UiPopUpState = 0;
                cursorEraserPhase = false;
            }

            if(UiPopUpState == 1 && buttonState == PLAMSMASTATEUI && mouse.isButtonPressed() &&
                    (mouse.mouseXUI >= button1MinX && mouse.mouseXUI <= button1MaxX)
                    && (mouse.mouseYUI >= button1MinY && mouse.mouseYUI <= button1MaxY)){
                grid.setCurrElementType(6);
                UiPopUpState = 0;
                cursorEraserPhase = false;
            }
            count = 0;
        }


        count++;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);

//        for (int i = 0; i < heightPixel; i+=sizePixel) {
//            for (int j = 0; j < widthPixel; j+=sizePixel) {
//                g2.drawRect(j,i,sizePixel,sizePixel);
//            }
//        }

        paintButtonUI(g2);
        grid.paint(g2);

        if(UiPopUpState == 0){
            paintMouseOutline(g2);
        }

        if(UiPopUpState == 1){
            paintOptionsScreen(g2);
            if(buttonState == SOLIDSTATEUI){
                paintSolidsButtons(g2);
            }
            else if(buttonState == LIQUIDSTATEUI){
                paintLiquidsButtons(g2);
            }
            else if (buttonState == GASSTATEUI) {
                paintGasesButtons(g2);
            }
            else if(buttonState == PLAMSMASTATEUI){
                paintPlasmaButtons(g2);
            }

        }

        g2.dispose();
    }
    public void paintMouseOutline(Graphics2D g2){
        // place for left corner sizePixel
        int rowSize = (mouse.getMouseConstantX() / sizePixel) - 1; // 2
        int colSize = mouse.getMouseConstantY()  / sizePixel - 1; // 2

        int x = rowSize*sizePixel;
        int y = colSize*sizePixel;

        Color colorOutline;
        if(!cursorEraserPhase){
            colorOutline = new Color(100, 107, 99);
        }
        else {
            colorOutline = new Color(245, 64, 33);
        }
        g2.setColor(colorOutline);


        g2.setStroke(new BasicStroke(1));
        g2.drawRect(x,y,3*sizePixel,3*sizePixel);
    }

    public void paintSolidsButtons(Graphics2D g2){
        createButton(g2,ycord,5*sizePixel,new Color(194,178,128));
        g2.drawString("SAND",6*sizePixel,ycord + sizePixel + (sizePixel/4));

        createButton(g2,ycord,10*sizePixel,new Color(150, 111, 51));
        g2.drawString("WOOD",11*sizePixel,ycord + sizePixel + (sizePixel/4));

        createButton(g2,ycord,15*sizePixel,new Color(255,250,250));
        g2.drawString("SNOW",16*sizePixel,ycord + sizePixel + (sizePixel/4));

        createButton(g2,ycord,20*sizePixel,new Color(58,49,40));
        g2.drawString("GRAVEL",21*sizePixel,ycord + sizePixel + (sizePixel/4));
    }

    public void paintLiquidsButtons(Graphics2D g2){
        createButton(g2,ycord,5*sizePixel,new Color(28,163,236));
        g2.drawString("WATER",6*sizePixel,ycord + sizePixel + (sizePixel/4));

//        createButton(g2,ycord,10*sizePixel,new Color(150, 111, 51));
//        g2.drawString("WOOD",11*sizePixel,ycord + sizePixel + (sizePixel/4));
    }
    public void paintGasesButtons(Graphics2D g2){
        createButton(g2,ycord,5*sizePixel,new Color(126,126,126,200));
        g2.drawString("SMOKE",6*sizePixel,ycord + sizePixel + (sizePixel/4));

        createButton(g2,ycord,10*sizePixel,new Color(71,71,71,120));
        g2.drawString("Steam",11*sizePixel,ycord + sizePixel + (sizePixel/4));
    }

    public void paintPlasmaButtons(Graphics2D g2){
        createButton(g2,ycord,5*sizePixel,new Color(255,90,0));
        g2.drawString("FIRE",6*sizePixel,ycord + sizePixel + (sizePixel/4));
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
            g2.drawString("Options",widthPixel - (sizePixel*5) - 3,sizePixel*2 + 3);
        }
        else{
            g2.drawString("Close",widthPixel - (sizePixel*5) - 3,sizePixel*2 + 3);
        }

        // Reset Button
        g2.setColor(new Color(235,106,14,180));
        g2.fillRoundRect(widthPixel - (sizePixel*14),sizePixel,sizePixel*5,sizePixel*2,35,35);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(widthPixel - (sizePixel*14),sizePixel,sizePixel*5,sizePixel*2,25,25);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD));
        g2.drawString("Clear",widthPixel - (sizePixel*13) + 3,sizePixel*2 + 3);

        // Eraser Button
        g2.setColor(new Color( 67,71,80,180));
        g2.fillRoundRect(widthPixel - (sizePixel*22),sizePixel,sizePixel*5,sizePixel*2,35,35);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(widthPixel - (sizePixel*22),sizePixel,sizePixel*5,sizePixel*2,25,25);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD));
        if(!cursorEraserPhase){
            g2.drawString("Eraser",widthPixel - (sizePixel*21) - 3,sizePixel*2 + 3);
        }
        else {
            g2.drawString("Brush",widthPixel - (sizePixel*21) - 3,sizePixel*2 + 3);
        }

    }

    public void paintOptionsScreen(Graphics2D g2){
        // pop up background
        g2.setColor(new Color(100,107,99));
        g2.fillRect(sizePixel*4,sizePixel*4,UISIZEWIDTH,UISIZEHEIGHT);
        g2.setColor(Color.WHITE);
        g2.drawLine(xcord - (sizePixel/4),ycord - (sizePixel*2) + (sizePixel/6),xcord - (sizePixel/4),(sizePixel*4 + UISIZEHEIGHT - (sizePixel/5)));

        // pop up #1 for solid's options
        createButton(g2, ycord,xcord,new Color(255,255,255));
        g2.drawString("Solids",xcord + sizePixel,ycord + (sizePixel + (sizePixel/4)));

        // pop up #2 for liquid Option
        int buttonCordY = ycord +(sizePixel*2) + (sizePixel*2);
        createButton(g2, buttonCordY,xcord,new Color(255,255,255));
        g2.drawString("Liquids",xcord + sizePixel,buttonCordY + (sizePixel + (sizePixel/4)));

        // pop up #3 for gases Option
        buttonCordY += (sizePixel*2) + (sizePixel*2);
        createButton(g2, buttonCordY,xcord,new Color(255,255,255));
        g2.drawString("Gases",xcord + sizePixel,buttonCordY + (sizePixel + (sizePixel/4)));

        // pop up #4 for plasma Option
        buttonCordY += (sizePixel*2) + (sizePixel*2);
        createButton(g2,buttonCordY,xcord,new Color(255,255,255));
        g2.drawString("Plasma",xcord + sizePixel,buttonCordY + (sizePixel + (sizePixel/4)));
    }

    private void createButton(Graphics2D g2, int ycord,int xcord, Color color) {
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
