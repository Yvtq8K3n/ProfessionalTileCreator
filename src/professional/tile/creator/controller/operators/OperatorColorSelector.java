package professional.tile.creator.controller.operators;

import professional.tile.creator.Exceptions.OutOfBoundsException;
import professional.tile.creator.controller.ColorsTileController;
import professional.tile.creator.controller.TilesetController;
import professional.tile.creator.model.Tileset;
import professional.tile.creator.model.selection.Selector;
import professional.tile.creator.model.selection.SelectorColor;
import professional.tile.creator.model.selection.SelectorTileset;

import java.awt.event.MouseEvent;

public class OperatorColorSelector extends Operator {

    @Override
    public void mousePressed(MouseEvent e) {
        Tileset tileset = TilesetController.INSTANCE.getTileset();

        if (tileset !=null){

            SelectorColor selector = ColorsTileController.INSTANCE.getSelector();

            if (selector == null || selector.getState() == Selector.State.FINISH) { // deveria ser com estados
                System.out.println("SelectorTileset: CREATED");
                try {
                    ColorsTileController.INSTANCE.createSelector(e.getX(), e.getY());
                } catch (OutOfBoundsException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Tileset tileset = TilesetController.INSTANCE.getTileset();

        if (tileset !=null) {

            SelectorColor selector = ColorsTileController.INSTANCE.getSelector();
            if (selector != null) {
                if (selector.getState() == Selector.State.CREATED
                        || selector.getState() == Selector.State.RESIZING) {

                    //move to the controller
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

                    System.out.println("SelectorTileset: RESIZING");
                    selector.setState(SelectorTileset.State.RESIZING);
                }
            }
        }
    }

    /*@Override
    public void mousePressed(MouseEvent e) {
        Object o = e.getSource();
        Point point = e.getPoint();

        if (e.getSource() instanceof ColorsTilesetRepresentation){
            ColorsTilesetRepresentation colorsTilesetRepresentation = (ColorsTilesetRepresentation) o;
            Component component = colorsTilesetRepresentation.getComponentAt(point);

            if (component instanceof ColorRepresentation){
                ColorRepresentation colorRepresentation = (ColorRepresentation) component;
                if (colorRepresentation.getState() == ColorRepresentation.State.INACTIVE){
                    colorRepresentation.setState(ColorRepresentation.State.ACTIVE);
                }else{
                    colorRepresentation.setState(ColorRepresentation.State.INACTIVE);
                }
            }
            colorsTilesetRepresentation.repaint();
            colorsTilesetRepresentation.revalidate();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Object o = e.getSource();
        Point point = e.getPoint();

        if (e.getSource() instanceof ColorsTilesetRepresentation){
            ColorsTilesetRepresentation colorsTilesetRepresentation = (ColorsTilesetRepresentation) o;
            Component component = colorsTilesetRepresentation.getComponentAt(point);

            if (component instanceof ColorRepresentation){
                ColorRepresentation colorRepresentation = (ColorRepresentation) component;
                if (colorRepresentation.getState() == ColorRepresentation.State.INACTIVE){
                    colorRepresentation.setState(ColorRepresentation.State.ACTIVE);
                }else{
                    colorRepresentation.setState(ColorRepresentation.State.INACTIVE);
                }
            }
            colorsTilesetRepresentation.repaint();
            colorsTilesetRepresentation.revalidate();
        }
    }*/
}
