package professional.tile.creator;

import professional.tile.creator.controller.ApplicationController;
import professional.tile.creator.model.Selector;

import java.awt.*;
import java.awt.event.MouseEvent;

public class OperadorSelector extends Operador {

    public OperadorSelector(){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Selector selector = ApplicationController.INSTANCE.getSeletor();
        if (selector == null || selector.state == selector.state.FINISH) { // deveria ser com estados
            System.out.println("Selector: CREATED");
            ApplicationController.INSTANCE.createSelector(e.getX(), e.getY());
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
                    || selector.state == Selector.State.CREATED)
            System.out.println("Selector: Resizing");
            selector.setEndX(e.getX());
            selector.setEndY(e.getY());
            selector.state = Selector.State.REZISING;
        }

        /*if (quadrado != null) { // aqui deveria estar implementado com estados
            main.getAreaDesenho().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } else {
            main.getAreaDesenho().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        }*/
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
