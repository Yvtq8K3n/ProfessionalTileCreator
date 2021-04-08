package professional.tile.creator.view;

import javax.swing.*;
import java.awt.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class PanelPallet extends JPanel {

    JPanel jPanelColors;
    JLabel lbTilesetColors;
    ColorsRepresentation colorsRepresentation;

    public PanelPallet() {
        initComponents();
    }

    public void initComponents(){
        //jPanelColors
        lbTilesetColors = new JLabel("Tileset Colors:");
        lbTilesetColors.setPreferredSize(new Dimension(200, 40));
        colorsRepresentation = new ColorsRepresentation();
        JScrollPane scrollPanel = new JScrollPane(colorsRepresentation);
        colorsRepresentation.addScrollPanel(scrollPanel);
        scrollPanel.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        jPanelColors = new JPanel();
        jPanelColors.setLayout(new BorderLayout());
        jPanelColors.add(lbTilesetColors, BorderLayout.PAGE_START);
        jPanelColors.add(scrollPanel, BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.red);
        panel2.setPreferredSize(new Dimension(175, 300));

        //PanelPallet
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(jPanelColors);
        add(panel2);
    }

}
