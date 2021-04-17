package professional.tile.creator.controller;

import professional.tile.creator.Exceptions.OutOfBoundsException;
import professional.tile.creator.model.TilesetColorManager;
import professional.tile.creator.model.selection.SelectorColor;
import professional.tile.creator.view.ColorsTilesetRepresentation;


public enum  ColorsTileController {
    INSTANCE;

    //Model
    private SelectorColor selector;

    //View
    private ColorsTilesetRepresentation colorsTilesetRepresentation;

    public void createSelector(int x, int y) throws OutOfBoundsException {
        int maxX = colorsTilesetRepresentation.getWidth();
        int maxY = colorsTilesetRepresentation.getHeight();

        //Create selector
        selector = new SelectorColor(x, y, maxX, maxY);

        //Force colorsTilesetRepresentation to readjust every time selector is resized
        selector.addPropertyChangeListener(colorsTilesetRepresentation);
        colorsTilesetRepresentation.revalidate();
        colorsTilesetRepresentation.repaint();
    }

    public void setColorsTilesetRepresentation(ColorsTilesetRepresentation colorsTilesetRepresentation) {
        this.colorsTilesetRepresentation = colorsTilesetRepresentation;
    }

    public SelectorColor getSelector() {
        return selector;
    }

    public void setSelector(SelectorColor selector) {
        this.selector = selector;
    }
}
