package professional.tile.creator.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;

public class ColorPicker extends JPanel {
    private Color color;

    private ColorRepresentation colorDisplay;

    private JPanel jScrollChangeColor;
    private ScrollColorDisplay scrollRed;
    private ScrollColorDisplay scrollGreen;
    private ScrollColorDisplay scrollBlue;


    public ColorPicker() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        //Create color
        color = new Color(ScrollColorDisplay.MAX_VALUE, 0 , 0);

        //jColorDisplay
        colorDisplay = new ColorRepresentation();
        colorDisplay.setFocusable(false);
        colorDisplay.setBackground(color);

        //jScrollChangeColor
        scrollRed = new ScrollColorDisplay(Color.RED, ScrollColorDisplay.MAX_VALUE){
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                super.adjustmentValueChanged(e);
                color = new Color(e.getValue(), color.getGreen(), color.getBlue());
                colorDisplay.setBackground(color);
            }
        };
        scrollGreen = new ScrollColorDisplay(Color.GREEN,ScrollColorDisplay.MIN_VALUE){
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                super.adjustmentValueChanged(e);
                color = new Color(color.getRed(),e.getValue(), color.getBlue());
                colorDisplay.setBackground(color);
            }
        };
        scrollBlue =  new ScrollColorDisplay(Color.BLUE,ScrollColorDisplay.MIN_VALUE){
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                super.adjustmentValueChanged(e);
                color = new Color(color.getRed(),color.getGreen(), e.getValue());
                colorDisplay.setBackground(color);
            }
        };
        jScrollChangeColor = new JPanel();
        jScrollChangeColor.setLayout(new BoxLayout(jScrollChangeColor, BoxLayout.Y_AXIS));
        jScrollChangeColor.add(scrollRed);
        jScrollChangeColor.add(scrollGreen);
        jScrollChangeColor.add(scrollBlue);

        //ColorPicker
        add(colorDisplay, BorderLayout.LINE_START);
        add(jScrollChangeColor, BorderLayout.LINE_END);
    }

    public Color getColor() {
        return color;
    }
}
