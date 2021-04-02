package professional.tile.creator.view;

import javax.swing.*;
import java.awt.*;

public class ApplicationView extends JFrame{
    PanelTileset panelTileset;
    PanelPallet panelPallet;

    public ApplicationView() {
        initComponents();
        setTitle("Professional Tile Creator");
        setPreferredSize(new Dimension(600, 550));
        setResizable(false);
        setVisible(true);
        pack();
    }

    public void initComponents(){
        panelTileset  = new PanelTileset();
        panelPallet = new PanelPallet();

        //Create mainJPanel
        JPanel mainJPanel = new JPanel();
        mainJPanel.setLayout(new BorderLayout());
        mainJPanel.add(panelTileset, BorderLayout.CENTER);
        mainJPanel.add(panelPallet, BorderLayout.LINE_END);
        add(mainJPanel);
    }
}
