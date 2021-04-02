package professional.tile.creator.view;

import javax.swing.*;
import java.awt.*;

public class PanelTileset extends JPanel {

   public PanelTileset() {
        initComponents();
   }

    public void initComponents(){
        //Panel1
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.red);
        panel1.setPreferredSize(new Dimension(450, 50));

        //Panel2
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.green);
        panel2.setPreferredSize(new Dimension(450, 450));

        //MainJPanel
        JPanel mainJPanel = new JPanel();
        mainJPanel.setLayout(new BorderLayout());
        mainJPanel.add(panel1, BorderLayout.PAGE_START);
        mainJPanel.add(panel2, BorderLayout.CENTER);
        add(mainJPanel);
    }

}
