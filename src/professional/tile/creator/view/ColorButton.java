package professional.tile.creator.view;

import javax.swing.*;
import java.awt.*;

public class ColorButton extends AbstractButton {

    public ColorButton() {
        setPreferredSize(new Dimension(25, 25));
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
