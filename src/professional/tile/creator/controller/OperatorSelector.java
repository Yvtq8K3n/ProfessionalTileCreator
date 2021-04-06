package professional.tile.creator.controller;

import professional.tile.creator.Exceptions.OutOfBoundsException;
import professional.tile.creator.model.Selector;
import professional.tile.creator.model.Tileset;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class OperatorSelector extends Operator {

    public OperatorSelector(){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Tileset tileset = TilesetController.INSTANCE.getTileset();
        if (tileset !=null){
            Selector selector = TilesetController.INSTANCE.getSelector();
            if (selector == null || selector.state == selector.state.FINISH) { // deveria ser com estados
                System.out.println("Selector: CREATED");
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
        super.mouseMoved(e);
        Selector selector = TilesetController.INSTANCE.getSelector();
        if (selector != null) { // deveria ser com estados
            if (selector.state == Selector.State.CREATED
                    || selector.state == Selector.State.RESIZING){
                try {
                    selector.setEndX(e.getX());
                } catch (OutOfBoundsException ex) {
                    System.out.println(ex.getMessage());
                }
                try {
                    selector.setEndY(e.getY());
                } catch (OutOfBoundsException ex) {
                    System.out.println(ex.getMessage());
                }

                System.out.println("Selector: RESIZING");
                selector.state = Selector.State.RESIZING;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Selector selector = TilesetController.INSTANCE.getSelector();
        if (selector != null) {
            System.out.println("Selector: FINISHED");
            selector.state = Selector.State.FINISH;
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
