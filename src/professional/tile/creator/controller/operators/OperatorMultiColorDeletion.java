package professional.tile.creator.controller.operators;

import professional.tile.creator.controller.ColorsTileController;


import java.awt.event.MouseEvent;

public class OperatorMultiColorDeletion extends Operator {

    @Override
    public void mousePressed(MouseEvent e) {
        ColorsTileController.INSTANCE.createSelector(e.getX(), e.getY());
        System.out.println("ColorDeletion: CREATING");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ColorsTileController.INSTANCE.resizeSelector(e.getX(), e.getY());
        System.out.println("ColorDeletion: RESIZING");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDragged(e);
        ColorsTileController.INSTANCE.removeFromSelection();
        System.out.println("ColorDeletion: FINISHING");
    }
}
