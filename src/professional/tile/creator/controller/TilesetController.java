package professional.tile.creator.controller;


import professional.tile.creator.Exceptions.InvalidOperationException;
import professional.tile.creator.Exceptions.OutOfBoundsException;
import professional.tile.creator.model.Selector;
import professional.tile.creator.model.Tileset;
import professional.tile.creator.model.TilesetColorManager;
import professional.tile.creator.model.comparison.*;
import professional.tile.creator.view.ColorsRepresentation;
import professional.tile.creator.view.TileRepresentation;

import java.awt.image.BufferedImage;

public enum TilesetController {
    INSTANCE;

    //Model
    private Tileset tileset;
    private Selector selector;
    private TilesetColorManager tilesetColorManager;

    //View
    private TileRepresentation tileRepresentation;
    private ColorsRepresentation colorsRepresentation;

    public void loadTileset(BufferedImage tileset){
        this.selector = null;
        this.tileset = new Tileset(tileset);
        this.tileset.addPropertyChangeListener(tileRepresentation);
        this.tileset.addPropertyChangeListener(colorsRepresentation);
        this.tileset.generateNewScaledImage();

        this.tilesetColorManager = new TilesetColorManager(this.tileset);
        this.tilesetColorManager.addPropertyChangeListener(colorsRepresentation);
        this.colorsRepresentation.drawTilesetColors();
    }

    public Tileset getTileset() {
        return tileset;
    }

    public void setTileRepresentation(TileRepresentation tileRepresentation){
        this.tileRepresentation = tileRepresentation;
    }

    public void setColorsRepresentation(ColorsRepresentation colorsRepresentation) {
        this.colorsRepresentation = colorsRepresentation;
    }


    public void createSelector(int x, int y) throws OutOfBoundsException {
        selector = new Selector(x, y);

        //Forcing redraw on tileRepresentation every time selector changes
        selector.addPropertyChangeListener(tileRepresentation);

        //Force selector to readjust every time tileset is scaled
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
