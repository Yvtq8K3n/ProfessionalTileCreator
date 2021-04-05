package professional.tile.creator;

import professional.tile.creator.Exceptions.OutOfBoundsException;
import professional.tile.creator.controller.ApplicationController;
import professional.tile.creator.model.Selector;
import professional.tile.creator.model.Tileset;

import java.awt.event.MouseEvent;

public class OperadorSelector extends Operador {

    public OperadorSelector(){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Tileset tileset = ApplicationController.INSTANCE.getTileset();
        if (tileset !=null){
            Selector selector = ApplicationController.INSTANCE.getSeletor();
            if (selector == null || selector.state == selector.state.FINISH) { // deveria ser com estados
                System.out.println("Selector: CREATED");
                try {
                    ApplicationController.INSTANCE.createSelector(e.getX(), e.getY());
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
        Selector selector = ApplicationController.INSTANCE.getSeletor();
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
        Selector selector = ApplicationController.INSTANCE.getSeletor();
        if (selector != null) {
            System.out.println("SELECTOR: FINISHED");
            selector.state = Selector.State.FINISH;
        }
    }

}
