package professional.tile.creator.controller;

import professional.tile.creator.Exceptions.OutOfBoundsException;
import professional.tile.creator.model.Tileset;
import professional.tile.creator.model.selection.Selector;
import professional.tile.creator.model.selection.SelectorTileset;

import java.awt.event.MouseEvent;

public class OperatorColorSelector extends Operator {

    @Override
    public void mousePressed(MouseEvent e) {
            SelectorTileset selectorTileset = TilesetController.INSTANCE.getSelectorTileset();
            if (selectorTileset == null || selectorTileset.getState() == Selector.State.FINISH){


                System.out.println("SelectorTileset: CREATED");
                try {
                    TilesetController.INSTANCE.createSelector(e.getX(), e.getY());
                } catch (OutOfBoundsException ex) {
                    System.out.println(ex.getMessage());
                }
            }
    }

}
