package professional.tile.creator.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class ScrollColorDisplay extends JComponent implements AdjustmentListener{
    public static final int MAX_VALUE = 248;
    public static final int MIN_VALUE = 0;

    JScrollBar jScrollBar;
    JTextField textField;

    public ScrollColorDisplay(Color foreground, int value) {
       this(foreground);
       setValue(value);
    }

    public ScrollColorDisplay(Color foreground) {
        initComponents();
        setMaximumSize(new Dimension(200, 22));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(0);
        setLayout(flowLayout);
        textField.setForeground(foreground);
        textField.setFont(new Font("Arial", Font.BOLD, 14));
        textField.setColumns(2);
    }

    private void initComponents() {
        textField = new JTextField(String.valueOf(MAX_VALUE));
        jScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
        jScrollBar.setPreferredSize(new Dimension(140, 20));
        jScrollBar.setMaximum(MAX_VALUE+10);//Due to extend is required to add 10px
        jScrollBar.addAdjustmentListener(this);
        add(jScrollBar);
        add(textField);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        int value = e.getValue() / 8 * 8;

        textField.setText(String.valueOf(value));
    }

    public void setValue(int value) {
        jScrollBar.setValue(value);
        textField.setText(String.valueOf(value));
    }
}
