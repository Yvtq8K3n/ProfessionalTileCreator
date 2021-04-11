package professional.tile.creator.view;

import javax.swing.*;
import java.awt.*;

public class ColorButton extends AbstractButton {

    public final static int DIMENSION = 25;

    public ColorButton() {
        super();
        setPreferredSize(new Dimension(DIMENSION, DIMENSION));
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
