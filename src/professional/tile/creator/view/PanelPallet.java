package professional.tile.creator.view;

import javax.swing.*;
import java.awt.*;

public class PanelPallet extends JPanel {

    public PanelPallet() {
        initComponents();
    }

    public void initComponents(){
        //Panel1
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.red);
        panel1.setPreferredSize(new Dimension(175, 500));

        add(panel1);
    }

}
