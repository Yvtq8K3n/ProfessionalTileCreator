package professional.tile.creator.view;

import javax.swing.*;
import java.awt.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class PanelPallet extends JPanel {

    JPanel jPanelMenuDisplayColors;
    JButton btnDisplayColorsHue;
    JButton btnDisplayColorsStep;
    JButton btnDisplayColorsInverseStep;
    JPanel jPanelColors;
    JLabel lbTilesetColors;
    ColorsRepresentation colorsRepresentation;

    public PanelPallet() {
        initComponents();
    }

    public void initComponents(){
        //jPanelMenuDisplayColors
        lbTilesetColors = new JLabel("Tileset Colors:");
        btnDisplayColorsHue = new JButton(new ImageIcon("./resources/hueIcon.png"));
        btnDisplayColorsHue.setToolTipText("Display based on HUE");
        btnDisplayColorsHue.setPreferredSize(new Dimension(26, 26));
        btnDisplayColorsHue.setBorderPainted(false);
        btnDisplayColorsStep = new JButton(new ImageIcon("./resources/stepIcon.png"));
        btnDisplayColorsStep.setToolTipText("Display based on Step");
        btnDisplayColorsStep.setPreferredSize(new Dimension(26, 26));
        btnDisplayColorsStep.setBorderPainted(false);
        btnDisplayColorsInverseStep = new JButton(new ImageIcon("./resources/inverseStepIcon.png"));
        btnDisplayColorsInverseStep.setToolTipText("Display based on InverseStep");
        btnDisplayColorsInverseStep.setPreferredSize(new Dimension(26, 26));
        btnDisplayColorsInverseStep.setBorderPainted(false);

        jPanelMenuDisplayColors = new JPanel();
        jPanelMenuDisplayColors.setPreferredSize(new Dimension(200, 40));
        jPanelMenuDisplayColors.add(lbTilesetColors);
        jPanelMenuDisplayColors.add(btnDisplayColorsHue);
        jPanelMenuDisplayColors.add(btnDisplayColorsStep);
        jPanelMenuDisplayColors.add(btnDisplayColorsInverseStep);

        colorsRepresentation = new ColorsRepresentation();
        JScrollPane scrollPanel = new JScrollPane(colorsRepresentation);
        colorsRepresentation.addScrollPanel(scrollPanel);
        scrollPanel.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        jPanelColors = new JPanel();
        jPanelColors.setLayout(new BorderLayout());
        jPanelColors.add(jPanelMenuDisplayColors, BorderLayout.PAGE_START);
        jPanelColors.add(scrollPanel, BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.red);
        panel2.setPreferredSize(new Dimension(175, 300));

        //PanelPallet
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(panel2);
        add(jPanelColors);
    }

}
