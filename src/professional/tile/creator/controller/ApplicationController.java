package professional.tile.creator.controller;


import professional.tile.creator.Exceptions.OutOfBoundsException;
import professional.tile.creator.model.Selector;
import professional.tile.creator.model.Tileset;
import professional.tile.creator.view.ApplicationView;

import java.awt.image.BufferedImage;

public enum ApplicationController {
    INSTANCE;

    private Tileset tileset;
    private ApplicationView applicationView;
    private Selector selector;

    public void loadTileset(BufferedImage tileset){
        this.selector = null;
        this.tileset = new Tileset(tileset);
        this.tileset.addPropertyChangeListener(applicationView);
        applicationView.repaint();
    }

    public Tileset getTileset() {
        return tileset;
    }

    public void setApplicationView(ApplicationView applicationView){
        this.applicationView = applicationView;
    }

    public void createSelector(int x, int y) throws OutOfBoundsException {
        selector = new Selector(x, y);
        selector.addPropertyChangeListener(applicationView);
        tileset.addPropertyChangeListener(selector);
        applicationView.repaint();
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
