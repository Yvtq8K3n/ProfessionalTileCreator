package professional.tile.creator.view;

import javax.swing.*;
import java.awt.*;

public class ScrollColorDisplay extends JComponent{
   JScrollBar jScrollBar;
   JTextField textField;

    public ScrollColorDisplay(Color foreground) {
        initComponents();
        setMaximumSize(new Dimension(200, 22));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(0);
        setLayout(flowLayout);
        textField.setForeground(foreground);
        textField.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void initComponents() {
        jScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
        jScrollBar.setPreferredSize(new Dimension(140, 20));
        textField = new JTextField("255");

        add(jScrollBar);
        add(textField);
    }
}
