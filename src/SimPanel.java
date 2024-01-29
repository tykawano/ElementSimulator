import Inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class SimPanel extends JPanel {
    public final int sizePixel = 10;
    public final int widthPixel = sizePixel*150;
    public final int heightPixel = sizePixel*70;
    MouseInputs mouse = new MouseInputs();
    public SimPanel(){
        setDefault();
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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawRect(0,0,100,100);
    }
}
