package Main;

import Grid.Grid;
import Inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class SimPanel extends JPanel implements Runnable{
    public final int sizePixel = 20;
    public final int rowNum = 50;
    public final int colNum = 25;
    public final int widthPixel = sizePixel*rowNum;
    public final int heightPixel = sizePixel*colNum;
    public final int UISIZEWIDTH = widthPixel - (2*(4*sizePixel));
    public final int UISIZEHEIGHT = heightPixel - (2*(3*sizePixel));
    public final int xcord = (sizePixel*4+UISIZEWIDTH) - (sizePixel*4 + (sizePixel/4));
    public final int ycord = (sizePixel*4) + (sizePixel/4);
    public final int fps = 100;
    public int UiPopUpState = 0;
    public static final int SOLIDSTATEUI = 0;
    public static final int LIQUIDSTATEUI = 1;
    public static final int GASSTATEUI = 2;
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
        grid.update();

        int buttonMinX = widthPixel - (sizePixel*6);
        int buttonMaxX = buttonMinX + sizePixel * 5;
        int buttonMinY = sizePixel;
        int buttonMaxY = buttonMinY + sizePixel + (sizePixel*2);
        if(UiPopUpState == 0 && mouse.isButtonPressed() &&
                (mouse.mouseXUI >= buttonMinX && mouse.mouseXUI <= buttonMaxX)
                && (mouse.mouseYUI >= buttonMinY && mouse.mouseYUI <= buttonMaxY)){
            UiPopUpState = 1;
        }
        else if (UiPopUpState == 1 && !mouse.isButtonPressed() &&
                (mouse.mouseXUI >= buttonMinX && mouse.mouseXUI <= buttonMaxX)
                && (mouse.mouseYUI >= buttonMinY && mouse.mouseYUI <= buttonMaxY)) {
            UiPopUpState = 0;
        }
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
        }

        g2.dispose();
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



    }

    public void paintOptionsScreen(Graphics2D g2){
        // pop up background
        g2.setColor(new Color(100,107,99));
        g2.fillRect(sizePixel*4,sizePixel*4,UISIZEWIDTH,UISIZEHEIGHT);

        // pop up #1 for solid's options
        g2.setColor(new Color(255,255,255));
        g2.fillRoundRect(xcord,ycord,sizePixel*4,sizePixel*2,35,35);
        g2.setColor(new Color(0,0,0));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(xcord,ycord,sizePixel*4,sizePixel*2,25,25);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD));
        g2.drawString("SOLIDS",xcord + sizePixel,ycord + (sizePixel + (sizePixel/4)));

        // pop up #2 for liquid Option
        int buttonCordY = ycord +(sizePixel*2) + (sizePixel/4);
        g2.setColor(new Color(255,255,255));
        g2.fillRoundRect(xcord,buttonCordY,sizePixel*4,sizePixel*2,35,35);
        g2.setColor(new Color(0,0,0));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(xcord,buttonCordY,sizePixel*4,sizePixel*2,25,25);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD));
        g2.drawString("LIQUIDS",xcord + sizePixel,buttonCordY + (sizePixel + (sizePixel/4)));

        // pop up #3 for gases Option
        buttonCordY += (sizePixel*2) + (sizePixel/4);
        g2.setColor(new Color(255,255,255));
        g2.fillRoundRect(xcord,buttonCordY,sizePixel*4,sizePixel*2,35,35);
        g2.setColor(new Color(0,0,0));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(xcord,buttonCordY,sizePixel*4,sizePixel*2,25,25);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD));
        g2.drawString("GASES",xcord + sizePixel,buttonCordY + (sizePixel + (sizePixel/4)));
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
