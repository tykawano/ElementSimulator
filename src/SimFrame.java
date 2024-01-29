import javax.swing.*;

public class SimFrame extends JFrame {
    public SimFrame(){
        setDefault();
    }
    public void setDefault(){
        setTitle("Element Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SimPanel mainPanel = new SimPanel();
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
//        setResizable(false);
        setVisible(true);
    }

}
