package professional.tile.creator.controller;


import professional.tile.creator.exceptions.InvalidOperationException;
import professional.tile.creator.exceptions.OutOfBoundsException;
import professional.tile.creator.model.Point;
import professional.tile.creator.model.selection.Selector;
import professional.tile.creator.model.selection.SelectorTileset;
import professional.tile.creator.model.Tileset;
import professional.tile.creator.model.TilesetColorManager;
import professional.tile.creator.model.comparison.*;
import professional.tile.creator.view.ColorsTilesetRepresentation;
import professional.tile.creator.view.TileRepresentation;

import java.awt.image.BufferedImage;

public enum TilesetController {
    INSTANCE;

    //Model
    private Tileset tileset;
    private SelectorTileset selector;
    private TilesetColorManager tilesetColorManager;

    //View
    private TileRepresentation tileRepresentation;
    private ColorsTilesetRepresentation colorsTilesetRepresentation;

    public void loadTileset(BufferedImage tileset){
        this.selector = null;
        this.tileset = new Tileset(tileset);
        this.tileset.addPropertyChangeListener(tileRepresentation);
        this.tileset.addPropertyChangeListener(colorsTilesetRepresentation);
        this.tileset.generateNewScaledImage();

        this.tilesetColorManager = new TilesetColorManager(this.tileset);
        this.tilesetColorManager.addPropertyChangeListener(colorsTilesetRepresentation);
        this.colorsTilesetRepresentation.drawTilesetColors();
    }

    public Tileset getTileset() {
        return tileset;
    }

    public void setTileRepresentation(TileRepresentation tileRepresentation){
        this.tileRepresentation = tileRepresentation;
    }

    public void setColorsTilesetRepresentation(ColorsTilesetRepresentation colorsTilesetRepresentation) {
        this.colorsTilesetRepresentation = colorsTilesetRepresentation;
    }

    public void createSelector(int x, int y) {
        boolean selectorIsFinished = selector != null
                && selector.getState() == Selector.State.FINISH;

        if (tileset != null) {
            if (selector == null || selectorIsFinished) {
                try {
                    selector = new SelectorTileset(tileset, x, y);

                    //Force colorsTilesetRepresentation to readjust every time selector is resized
                    selector.addPropertyChangeListener(tileRepresentation);
                    tileset.addPropertyChangeListener(selector);

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

    public TilesetColorManager getTilesetColorManager() {
        return tilesetColorManager;
    }

    public void setTilesetColorManager(TilesetColorManager tilesetColorManager) {
        this.tilesetColorManager = tilesetColorManager;
    }

    public void setColorsSort(String colorsSort) {
        try {
            switch (colorsSort){
                case "LastSave":
                    throw new InvalidOperationException("Sort Method");
                case "Hue":
                    tilesetColorManager.setSortComparator(CompareColorsByHue.CRITERIA);
                break;
                case "Luminosity":
                    tilesetColorManager.setSortComparator(CompareColorsByLuminosity.CRITERIA);
                break;
                case "Step":
                    tilesetColorManager.setSortComparator(CompareColorsByStepSorting.CRITERIA);
                break;
                case "RGBStep":
                    tilesetColorManager.setSortComparator(CompareColorsByRGBStepSorting.CRITERIA);
                break;
                case "InverseStep":
                    tilesetColorManager.setSortComparator(CompareColorsByStepInvertSorting.CRITERIA);
                break;
                default:
                    throw new InvalidOperationException("Sort Method");
            }
        } catch (InvalidOperationException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
