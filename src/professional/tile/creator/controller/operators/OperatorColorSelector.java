package professional.tile.creator.controller.operators;

import professional.tile.creator.controller.ColorsTileController;


import java.awt.event.MouseEvent;

public class OperatorColorSelector extends Operator {

    @Override
    public void mousePressed(MouseEvent e) {
        ColorsTileController.INSTANCE.createSelector(e.getX(), e.getY());
        System.out.println("ColorSelector: CREATED");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ColorsTileController.INSTANCE.resizeSelector(e.getX(), e.getY());
        System.out.println("ColorSelector: RESIZED");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ColorsTileController.INSTANCE.finishSelector(e.getX(), e.getY());
        System.out.println("ColorSelector: FINISHED");
    }
}
