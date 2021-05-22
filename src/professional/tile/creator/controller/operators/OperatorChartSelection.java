package professional.tile.creator.controller.operators;

import professional.tile.creator.controller.ColorsTileController;

import java.awt.event.MouseEvent;

public class OperatorChartSelection extends Operator {
    @Override
    public void mousePressed(MouseEvent e) {
        ColorsTileController.INSTANCE.createChartSelector(e.getX(), e.getY());
        System.out.println("ColorDeletion: CREATING");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //ColorsTileController.INSTANCE.addChartSelection(e.getX(), e.getY());
        System.out.println("ColorDeletion: RESIZING");
    }
}
