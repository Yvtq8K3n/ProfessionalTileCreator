package professional.tile.creator.view;

import javax.swing.*;
import java.awt.*;

public class ApplicationView extends JFrame {
    PanelTileset panelTileset;
    PanelPallet panelPallet;

    public ApplicationView() {
        initComponents();
        try {
            //Attempting to get System Look and Feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException | UnsupportedLookAndFeelException e ) {
            e.printStackTrace();
        }
        setTitle("Professional Tile Creator");
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
