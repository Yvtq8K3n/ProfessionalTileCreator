package professional.tile.creator.controller;


import professional.tile.creator.exceptions.OutOfBoundsException;
import professional.tile.creator.model.Point;
import professional.tile.creator.model.TilesetManager;
import professional.tile.creator.model.selection.Selector;
import professional.tile.creator.model.selection.SelectorTileset;
import professional.tile.creator.view.ClusterRepresentation;
import professional.tile.creator.view.TileRepresentation;

import java.awt.image.BufferedImage;

public enum TilesetController {
    INSTANCE;

    //Model
    private TilesetManager tilesetManager;
    private SelectorTileset selector;

    //View
    private TileRepresentation tileRepresentation;
    private ClusterRepresentation clusterRepresentation;

    public void loadTileset(BufferedImage tileset){
        this.selector = null;
        this.tilesetManager = new TilesetManager(tileset);
        this.tilesetManager.addPropertyChangeListener(tileRepresentation);
        this.tilesetManager.generateNewScaledImage();

        ColorsTileController.INSTANCE.reloadTilesetColors(this.tilesetManager);
    }

    public TilesetManager getTilesetManager() {
        return tilesetManager;
    }

    public void setTileRepresentation(TileRepresentation tileRepresentation){
        this.tileRepresentation = tileRepresentation;
    }


    public void createSelector(int x, int y) {
        boolean selectorIsFinished = selector != null
                && selector.getState() == Selector.State.FINISH;

        if (tilesetManager != null) {
            if (selector == null || selectorIsFinished) {
                try {
                    selector = new SelectorTileset(tilesetManager, x, y);

                    //Force colorsTilesetRepresentation to readjust every time selector is resized
                    selector.addPropertyChangeListener(tileRepresentation);
                    tilesetManager.addPropertyChangeListener(selector);

                    //Reload View
                    tileRepresentation.revalidate();
                    tileRepresentation.repaint();
                } catch (OutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void resizeSelector(int endX, int endY) {
        boolean selectorIsEditable = selector != null && (selector.getState() == Selector.State.CREATED
                || selector.getState() == Selector.State.RESIZING);

        if (selectorIsEditable){
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
            selector.setState(SelectorTileset.State.FINISH);
        }
    }


    public SelectorTileset getSelector() {
        return selector;
    }

    public void reduceScaleFactor(){
        int scaleFactor = tilesetManager.getScaleFactor();
        if (scaleFactor>1){
            tilesetManager.reduceScaleFactor();
        }
    }

    public void increaseScaleFactor(){
        int scaleFactor = tilesetManager.getScaleFactor();
        if (scaleFactor<3){
            tilesetManager.increaseScaleFactor();
        }
    }

    public void setClusterRepresentation(ClusterRepresentation clusterRepresentation) {
        this.clusterRepresentation = clusterRepresentation;
    }
}
