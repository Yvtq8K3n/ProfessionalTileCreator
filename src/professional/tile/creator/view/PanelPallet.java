package professional.tile.creator.view;

import org.openide.awt.DropDownButtonFactory;
import professional.tile.creator.controller.ColorsTileController;
import professional.tile.creator.controller.TilesetController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class PanelPallet extends JPanel {


    JTabbedPane tabbedPane;
    ColorReplacer colorReplacer;
    ReplaceColorsMenu replaceColorsMenu;

    JPanel jPanelSortDisplayColors;
    JButton btnSortColors;

    JPanel jPanelColors;
    JLabel lbTilesetColors;
    TilesetColorsPanel tilesetColorsPanel;

    public PanelPallet() {
        initComponents();
    }

    public void initComponents(){
        //tabbedPane
        colorReplacer = new ColorReplacer();
        replaceColorsMenu = new ReplaceColorsMenu();

        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(175, 280));
        tabbedPane.addTab("Replace", colorReplacer);//Single Operations
        tabbedPane.addTab("Selector", replaceColorsMenu);//MultiOperations
        tabbedPane.addTab("Misc", new JPanel());

        //jPanelSortDisplayColors
        lbTilesetColors = new JLabel("TilesetManager Colors:");
        btnSortColors = createbtnDropDown(new ImageIcon("./resources/hueIcon.png"));
        btnSortColors.setPreferredSize(new Dimension(40, 20));
        btnSortColors.setFocusable(false);

        jPanelSortDisplayColors = new JPanel();
        jPanelSortDisplayColors.setLayout(new BorderLayout());
        jPanelSortDisplayColors.setPreferredSize(new Dimension(200, 30));
        jPanelSortDisplayColors.add(lbTilesetColors,BorderLayout.LINE_START);
        jPanelSortDisplayColors.add(btnSortColors,BorderLayout.LINE_END);


        //ColorTileSet
        tilesetColorsPanel = new TilesetColorsPanel();
        JScrollPane scrollPanel = new JScrollPane(tilesetColorsPanel);
        scrollPanel.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        //Other stuff
        jPanelColors = new JPanel();
        jPanelColors.setLayout(new BorderLayout());
        jPanelColors.add(jPanelSortDisplayColors, BorderLayout.PAGE_START);
        jPanelColors.add(scrollPanel, BorderLayout.CENTER);



        //PanelPallet
        addActions();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(tabbedPane);
        add(jPanelColors);
    }

    private void addActions() {
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int index = tabbedPane.getSelectedIndex();
                boolean isSingleSelectionTab = index == 0;
                boolean isMultiSelectionTab = index ==1;

                if (isSingleSelectionTab){
                    tilesetColorsPanel.setOperator(TilesetColorsPanel.Operation.SINGLE);
                    ColorsTileController.INSTANCE.clearSelection();
                }
                if (isMultiSelectionTab){
                    tilesetColorsPanel.setOperator(TilesetColorsPanel.Operation.MULTI);
                    ColorsTileController.INSTANCE.clearSelection();
                }
            }
        });
    }

    public JButton createbtnDropDown(ImageIcon icon){
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem menuItemColorsbyInvertStep = new JMenuItem("Sort: Colors By InvertStep");
        menuItemColorsbyInvertStep.addActionListener(e -> ColorsTileController.INSTANCE.setColorsSort("InverseStep"));
        popupMenu.add(menuItemColorsbyInvertStep);

        JMenuItem menuItemColorsByLuminosity = new JMenuItem("Sort: Colors By Luminosity");
        menuItemColorsByLuminosity.addActionListener(e -> ColorsTileController.INSTANCE.setColorsSort("Luminosity"));
        popupMenu.add(menuItemColorsByLuminosity);

        JMenuItem menuItemColorsByHue = new JMenuItem("Sort: Colors By Hue");
        menuItemColorsByHue.addActionListener(e -> ColorsTileController.INSTANCE.setColorsSort("Hue"));
        popupMenu.add(menuItemColorsByHue);

        JMenuItem menuItemColorsByStep = new JMenuItem("Sort: Colors By Step");
        menuItemColorsByStep.addActionListener(e -> ColorsTileController.INSTANCE.setColorsSort("Step"));
        popupMenu.add(menuItemColorsByStep);

        JMenuItem menuItemColorsbyRGBStep = new JMenuItem("Sort: Colors By RGBStep");
        menuItemColorsbyRGBStep.addActionListener(e -> ColorsTileController.INSTANCE.setColorsSort("RGBStep"));
        popupMenu.add(menuItemColorsbyRGBStep);

        return DropDownButtonFactory.createDropDownButton(icon, popupMenu);
    }

}
