package professional.tile.creator.controller;


import professional.tile.creator.Exceptions.OutOfBoundsException;
import professional.tile.creator.model.Selector;
import professional.tile.creator.model.Tileset;
import professional.tile.creator.view.ApplicationView;
import professional.tile.creator.view.TileRepresentation;

import java.awt.image.BufferedImage;

public enum TilesetController {
    INSTANCE;

    private Tileset tileset;
    private Selector selector;
    private TileRepresentation tileRepresentation;

    public void loadTileset(BufferedImage tileset){
        this.selector = null;
        this.tileset = new Tileset(tileset);
        this.tileset.addPropertyChangeListener(tileRepresentation);
        tileRepresentation.repaint();
    }

    public Tileset getTileset() {
        return tileset;
    }

    public void setTileRepresentation(TileRepresentation tileRepresentation){
        this.tileRepresentation = tileRepresentation;
    }

    public void createSelector(int x, int y) throws OutOfBoundsException {
        selector = new Selector(x, y);
        selector.addPropertyChangeListener(tileRepresentation);
        tileset.addPropertyChangeListener(selector);
        tileRepresentation.repaint();
    }

    public Selector getSelector() {
        return selector;
    }

    public void reduceScaleFactor(){
        int scaleFactor = tileset.getScaleFactor();
        if (scaleFactor>1){
            tileset.reduceScaleFactor();
        }
    }

    public void increaseScaleFactor(){
        int scaleFactor = tileset.getScaleFactor();
        if (scaleFactor<3){
            tileset.increaseScaleFactor();
        }
    }
}
