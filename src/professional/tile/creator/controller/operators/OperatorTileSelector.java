package professional.tile.creator.controller.operators;

import professional.tile.creator.Exceptions.OutOfBoundsException;
import professional.tile.creator.controller.TilesetController;
import professional.tile.creator.model.selection.Selector;
import professional.tile.creator.model.selection.SelectorTileset;
import professional.tile.creator.model.Tileset;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class OperatorTileSelector extends Operator {

    public OperatorTileSelector(){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Tileset tileset = TilesetController.INSTANCE.getTileset();
        if (tileset !=null){
            SelectorTileset selectorTileset = TilesetController.INSTANCE.getSelectorTileset();
            if (selectorTileset == null || selectorTileset.getState() == Selector.State.FINISH) { // deveria ser com estados
                System.out.println("SelectorTileset: CREATED");
                try {
                    TilesetController.INSTANCE.createSelector(e.getX(), e.getY());
                } catch (OutOfBoundsException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        SelectorTileset selectorTileset = TilesetController.INSTANCE.getSelectorTileset();
        if (selectorTileset != null) { // deveria ser com estados
            if (selectorTileset.getState() == Selector.State.CREATED
                    || selectorTileset.getState() == Selector.State.RESIZING){
                try {
                    selectorTileset.setEndX(e.getX());
                } catch (OutOfBoundsException ex) {
                    System.out.println(ex.getMessage());
                }
                try {
                    selectorTileset.setEndY(e.getY());
                } catch (OutOfBoundsException ex) {
                    System.out.println(ex.getMessage());
                }

                System.out.println("SelectorTileset: RESIZING");
                selectorTileset.setState(SelectorTileset.State.RESIZING);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        SelectorTileset selectorTileset = TilesetController.INSTANCE.getSelectorTileset();
        if (selectorTileset != null) {
            System.out.println("SelectorTileset: FINISHED");
            selectorTileset.setState(SelectorTileset.State.FINISH);
        }
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
