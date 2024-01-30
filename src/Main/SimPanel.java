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
    public final int fps = 60;
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
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        grid.paint(g2);

        for (int i = 0; i < heightPixel; i+=sizePixel) {
            for (int j = 0; j < widthPixel; j+=sizePixel) {
                g2.drawRect(j,i,sizePixel,sizePixel);
            }
        }

        g2.dispose();
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
}
