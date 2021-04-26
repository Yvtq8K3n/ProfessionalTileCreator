package professional.tile.creator.controller.operators;

import professional.tile.creator.controller.ColorsTileController;

import java.awt.event.MouseEvent;

public class OperatorSingleColorSelection extends Operator {
    @Override
    public void mousePressed(MouseEvent e) {
        ColorsTileController.INSTANCE.createSimpleSelector(e.getX(), e.getY());
        System.out.println("ColorSelection: CREATING");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed(e);
    }

}
