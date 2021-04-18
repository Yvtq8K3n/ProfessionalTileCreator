package professional.tile.creator.controller.operators;

import professional.tile.creator.controller.TilesetController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class OperatorTileSelector extends Operator {

    public OperatorTileSelector(){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        TilesetController.INSTANCE.createSelector(e.getX(), e.getY());
        System.out.println("SelectorTileset: CREATED");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        TilesetController.INSTANCE.resizeSelector(e.getX(), e.getY());
        System.out.println("SelectorTileset: RESIZED");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        TilesetController.INSTANCE.finishSelector(e.getX(), e.getY());
        System.out.println("SelectorTileset: FINISHED");
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println("Scrolled");
        int notches = e.getWheelRotation();
        if (notches < 0) {
            TilesetController.INSTANCE.increaseScaleFactor();
        } else {
            TilesetController.INSTANCE.reduceScaleFactor();
        }
    }

}
