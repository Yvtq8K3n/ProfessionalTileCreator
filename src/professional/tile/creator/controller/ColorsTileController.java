package professional.tile.creator.controller;

import professional.tile.creator.exceptions.OutOfBoundsException;
import professional.tile.creator.model.Point;
import professional.tile.creator.model.selection.Selector;
import professional.tile.creator.model.selection.SelectorColor;
import professional.tile.creator.view.ColorsTilesetRepresentation;


public enum  ColorsTileController {
    INSTANCE;

    //Model
    private SelectorColor selector;

    //View
    private ColorsTilesetRepresentation colorsTilesetRepresentation;

    public void createSelector(int startX, int startY){
        //Retrieve the highest value X and Y
        int maxX = colorsTilesetRepresentation.getWidth();
        int maxY = colorsTilesetRepresentation.getHeight();

        if (selector == null || (selector!=null && selector.getState() == Selector.State.FINISH)) {
            try {
                selector = new SelectorColor(startX, startY, maxX, maxY);

                //Force colorsTilesetRepresentation to readjust every time selector is resized
                selector.addPropertyChangeListener(colorsTilesetRepresentation);

                //Reload View
                colorsTilesetRepresentation.revalidate();
                colorsTilesetRepresentation.repaint();
            } catch (OutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    public void resizeSelector(int endX, int endY) {
        if (selector != null || selector.getState() == Selector.State.CREATED
                || selector.getState() == Selector.State.RESIZING) {
            try {
                selector.resizeEndCoordinates(new Point(endX, endY));
            } catch (OutOfBoundsException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void finishSelector(int endX, int endY) {
        if (selector != null) {
            resizeSelector(endX, endY);
            selector.setState(Selector.State.FINISH);
        }
    }

    public void setColorsTilesetRepresentation(ColorsTilesetRepresentation colorsTilesetRepresentation) {
        this.colorsTilesetRepresentation = colorsTilesetRepresentation;
    }

    public SelectorColor getSelector() {
        return selector;
    }
}
