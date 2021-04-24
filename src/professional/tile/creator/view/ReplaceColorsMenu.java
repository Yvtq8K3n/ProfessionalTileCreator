package professional.tile.creator.view;

import professional.tile.creator.view.components.ColorRepresentation;
import professional.tile.creator.view.components.ScrollColorDisplay;

import javax.swing.*;
import java.awt.*;

public class ReplaceColorsMenu extends JPanel {
    //jReplaceColor
    JLabel lbreplaceColor;
    JButton colorRepresentation;

    //jChangeReplaceColor
    ColorRepresentation colorDisplay;
    ScrollColorDisplay scrollRed;
    ScrollColorDisplay scrollGreen;
    ScrollColorDisplay scrollBlue;

    //jReplacingColors
    JLabel lbreplacingColors;
    JScrollPane scrollPane;
    JButton btnReplaceAll;
    JList<Color> colorList;

    public ReplaceColorsMenu() {
        initComponents();
        setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public void initComponents(){
        //jReplaceColor
        /*lbreplaceColor = new JLabel("Selected Color:");
        colorRepresentation = new JButton();
        colorRepresentation.setIcon(new ImageIcon("./resources/grabberIcon.png"));
        colorRepresentation.setPreferredSize(new Dimension(25, 25));
        colorRepresentation.setFocusable(false);
        colorRepresentation.setBorder(new LineBorder(Color.BLACK));
        colorRepresentation.setFocusable(false);
        colorDisplay = new ColorRepresentation();
        colorDisplay.setFocusable(false);
        colorDisplay.setBackground(Color.RED);

        //jChangeReplaceColor
        scrollRed = new ScrollColorDisplay(Color.RED);
        scrollGreen = new ScrollColorDisplay(Color.GREEN);
        scrollBlue =  new ScrollColorDisplay(Color.BLUE);
        JPanel jEditorColorPanel = new JPanel();
        jEditorColorPanel.setLayout(new BoxLayout(jEditorColorPanel, BoxLayout.Y_AXIS));
        jEditorColorPanel.add(scrollRed);
        jEditorColorPanel.add(scrollGreen);
        jEditorColorPanel.add(scrollBlue);*/

        //jReplaceAllOptions
        lbreplacingColors = new JLabel("Selected Colors:");
        scrollPane = new JScrollPane(colorList);
        scrollPane.setPreferredSize(new Dimension(170, 120));
        btnReplaceAll = new JButton();
        btnReplaceAll.setIcon(new ImageIcon("./resources/grabberIcon.png"));
        JPanel jPanelAllOption = new JPanel();
        jPanelAllOption.setLayout(new BorderLayout());
        jPanelAllOption.setPreferredSize(new Dimension(30, 120));
        jPanelAllOption.add(btnReplaceAll, BorderLayout.PAGE_END);

        //ReplaceColorsMenu
        //JPanel jPanel = addComponentsProperty(lbreplaceColor, colorRepresentation);
        //addComponentsProperty(colorDisplay, jEditorColorPanel);
        //jPanel.setPreferredSize(new Dimension(200, 25));
        add(lbreplacingColors);
        add(scrollPane);
        add(jPanelAllOption);
    }

    public JPanel addComponentsProperty(Component componentLeft, Component componentRight){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(componentLeft, BorderLayout.LINE_START);
        jPanel.add(componentRight, BorderLayout.LINE_END);
        add(jPanel);
        return jPanel;
    }
}
