package professional.tile.creator.controller.operators;

import professional.tile.creator.controller.ColorsTileController;

import java.awt.event.MouseEvent;

public class OperatorSingleColorSelection extends Operator {
    @Override
    public void mousePressed(MouseEvent e) {
        ColorsTileController.INSTANCE.removePreviousSelection();
        ColorsTileController.INSTANCE.createSelector(e.getX(), e.getY());
        ColorsTileController.INSTANCE.addToSelection();
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
